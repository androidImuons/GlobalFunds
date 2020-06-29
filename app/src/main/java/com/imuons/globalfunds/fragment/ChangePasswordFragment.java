package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.entity.ChangePasswordEnity;
import com.imuons.globalfunds.responseModel.CommonResponse;
import com.imuons.globalfunds.responseModel.ProfileResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    @BindView(R.id.et_OldPassword)
    EditText et_OldPassword;
    @BindView(R.id.et_newPassword)
    EditText et_newPassword;
    @BindView(R.id.et_reTypePassword)
    EditText et_reTypePassword;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password = et_newPassword.getText().toString().trim();
                String cmf_password = et_reTypePassword.getText().toString().trim();
                String old_password = et_OldPassword.getText().toString().trim();

                if (old_password.isEmpty()) {
                    et_OldPassword.setError("Please enter your old password");
                } else if (cmf_password.isEmpty()) {
                    et_reTypePassword.setError("Please enter confirm password");
                } else if (new_password.isEmpty()) {
                    et_newPassword.setError("Please enter new password");
                } else if (!cmf_password.matches(new_password)) {
                    et_reTypePassword.setError("password not match");
                }else {
                    callChangePasswordApi(old_password , new_password , cmf_password);
                }
            }
        });


        return view;
    }

    private void callChangePasswordApi(String old_password, String new_password, String cmf_password) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(ChangePasswordFragment.this.getActivity());
            Call call = apiService.changePassword(new ChangePasswordEnity(old_password , new_password , cmf_password));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePasswordFragment.this.getContext()).clearNonTouchableFlags(ChangePasswordFragment.this.getActivity());
                    dialog.dismiss();
                    CommonResponse authResponse = (CommonResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ChangePdResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ChangePasswordFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            et_newPassword.setText("");
                            et_reTypePassword.setText("");
                            et_OldPassword.setText("");
                        } else {
                            Toast.makeText(ChangePasswordFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePasswordFragment.this.getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ChangePasswordFragment.this.getContext()).clearNonTouchableFlags(ChangePasswordFragment.this.getActivity());
                    Toast.makeText(ChangePasswordFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}
