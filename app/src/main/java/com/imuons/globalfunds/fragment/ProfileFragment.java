package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.responseModel.ProfileData;
import com.imuons.globalfunds.responseModel.ProfileResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    @BindView(R.id.tvUserId)
    TextView tvUserId;
    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvSponserId)
    TextView tvSponserId;
    @BindView(R.id.tvSponserName)
    TextView tvSponserName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvBtcAddress)
    TextView tvBtcAddress;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_activity, container, false);
        ButterKnife.bind(this, view);
        callProfileInfoApi();
        return view;

    }

    private void callProfileInfoApi() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(ProfileFragment.this.getActivity());
            Call call = apiService.profileApi();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ProfileFragment.this.getContext()).clearNonTouchableFlags(ProfileFragment.this.getActivity());
                    dialog.dismiss();
                    ProfileResponse authResponse = (ProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(ProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProfileFragment.this.getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ProfileFragment.this.getContext()).clearNonTouchableFlags(ProfileFragment.this.getActivity());
                    Toast.makeText(ProfileFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(ProfileData data) {
        tvUserId.setText(data.getUserId());
        tvFullName.setText(data.getFullname());
        tvSponserId.setText(data.getSponser());
        tvSponserName.setText(data.getSponserFullname());
        tvEmail.setText(data.getEmail());
        tvPhone.setText("+"+data.getCode()+"-"+data.getMobile());
        if (data.getBtcAddress() == null)
            tvBtcAddress.setText("");
        else
            tvBtcAddress.setText(data.getBtcAddress());
    }
}
