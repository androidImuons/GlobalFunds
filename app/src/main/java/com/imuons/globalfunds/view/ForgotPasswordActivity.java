package com.imuons.globalfunds.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.CheckUser;
import com.imuons.globalfunds.dataModel.ForgotPasswordResponse;
import com.imuons.globalfunds.responseModel.LoginResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.et_user_id)
    EditText et_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_submit)
    public void checkUser() {
        if (et_user_id.getText().toString().trim().isEmpty()) {
            et_user_id.requestFocus();
            et_user_id.setError("Enter User ID");
        } else {
            checkUserExit();
        }
    }

    private void checkUserExit() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            final Dialog dialog = ViewUtils.getProgressBar(ForgotPasswordActivity.this);
            Map<String, Object> paparam = new HashMap<>();
            paparam.put("user_id", et_user_id.getText().toString());
            Call call = apiService.checkUser(paparam);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgotPasswordActivity.this).clearNonTouchableFlags(ForgotPasswordActivity.this);
                    dialog.dismiss();
                    CheckUser authResponse = (CheckUser) response.body();
                    Log.i("checkuser::", new Gson().toJson(response.body().toString()));
                    if (authResponse != null) {
                        Log.i("checkuser::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            callForgotApi();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //   AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Server Error");
                        Toast.makeText(ForgotPasswordActivity.this, "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ForgotPasswordActivity.this).clearNonTouchableFlags(ForgotPasswordActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgotPasswordActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void callForgotApi() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            final Dialog dialog = ViewUtils.getProgressBar(ForgotPasswordActivity.this);
            Map<String, Object> paparam = new HashMap<>();
            paparam.put("user_id", et_user_id.getText().toString());
            Call call = apiService.forgotParssword(paparam);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgotPasswordActivity.this).clearNonTouchableFlags(ForgotPasswordActivity.this);
                    dialog.dismiss();
                    ForgotPasswordResponse authResponse = (ForgotPasswordResponse) response.body();
                    Log.i("checkuser::", new Gson().toJson(response.body().toString()));
                    if (authResponse != null) {
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ForgotPasswordActivity.this).showDialog(ForgotPasswordActivity.this, "Server " + "Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ForgotPasswordActivity.this).clearNonTouchableFlags(ForgotPasswordActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgotPasswordActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.txt_login)
    public void callloginintent() {
        finish();
    }
}
