package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.entity.WithDrawalEntity;
import com.imuons.globalfunds.responseModel.CommonResponse;
import com.imuons.globalfunds.responseModel.DashBoardDataModel;
import com.imuons.globalfunds.responseModel.DashBoardResponseModel;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.MyPreference;
import com.imuons.globalfunds.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeWorkingWithdrawalFragment extends Fragment {
    @BindView(R.id.txt_working_income)
    TextView txt_working_income;
    @BindView(R.id.txt_working_withdrwa)
    TextView txt_working_withdrwa;
    @BindView(R.id.txt_working_balance)
    TextView txt_working_balance;
    @BindView(R.id.txt_withdrwa_mode)
    TextView txt_withdrwa_mode;
    @BindView(R.id.txt_address)
    TextView txt_address;
    @BindView(R.id.et_amount)
    EditText et_amount;
    @BindView(R.id.btn_withdrwal)
    TextView btn_withdrwal;
    private View view;
    private DashBoardDataModel data;


    public MakeWorkingWithdrawalFragment() {
        // Required empty public constructor
    }

    public static MakeWorkingWithdrawalFragment newInstance() {
        MakeWorkingWithdrawalFragment fragment = new MakeWorkingWithdrawalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_working_withdrawal, container, false);
        ButterKnife.bind(this, view);
        CallDashBoardApi();
        return view;

    }

    private void CallDashBoardApi() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Call call = apiService.GetDashBoard();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    DashBoardResponseModel authResponse = (DashBoardResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            //                            setData(authResponse.getData());
                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(DashBoardDataModel data) {
        this.data=data;
        txt_working_income.setText(MyPreference.currency_symbol + data.getWorkingWallet());
        txt_working_balance.setText(MyPreference.currency_symbol + data.getWorkingWalletWithdraw());
        txt_working_withdrwa.setText(MyPreference.currency_symbol + data.getWorkingWalletBalance());
        txt_address.setText(data.getBtcAddress());

    }

    @OnClick(R.id.btn_withdrwal)
    public void withdrwa() {
        if (et_amount.getText().toString().isEmpty()) {
            et_amount.setError("Enter Amount");
            et_amount.requestFocus();
        } else {
            callWorkingWithdrawl(new WithDrawalEntity(String.valueOf(data.getBinaryIncomeBalance()), String.valueOf(data.getDirectIncomeBalance()), String.valueOf(data.getLevelIncomeBalance()), String.valueOf(data.getRoiIncomeBalance()), String.valueOf(data.getTopUpWallet()), String.valueOf(data.getTransferWallet()),
                    et_amount.getText().toString()));
        }


    }

    public void callWorkingWithdrawl(WithDrawalEntity withDrawalEntity) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(getActivity());
            Call call = apiService.withDrawWorkingApi(withDrawalEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    dialog.dismiss();
                    CommonResponse authResponse = (CommonResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            et_amount.setText("");
                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            CallDashBoardApi();
                        } else {
                            //                            setData(authResponse.getData());
                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(getActivity()).clearNonTouchableFlags(getActivity());
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}
