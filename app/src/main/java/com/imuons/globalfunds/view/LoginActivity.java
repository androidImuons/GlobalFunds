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
import com.imuons.globalfunds.entity.LoginEntity;
import com.imuons.globalfunds.responseModel.LoginResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_user_id)
    EditText et_user_id;
    @BindView(R.id.et_password)
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_forgot_pwd)
    public void forgot_pwd() {
        startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
    }

    @OnClick(R.id.ch_remember_me)
    public void remember() {

    }

    @OnClick(R.id.txt_login)
    public void login() {
        if (et_user_id.getText().toString().trim().isEmpty()) {
            et_user_id.requestFocus();
            et_user_id.setError("Enter User ID");
        } else if (et_password.getText().toString().trim().isEmpty()) {
            et_password.requestFocus();
            et_password.setError("Enter Password");
        } else {
            LoginEntity loginEntity = new LoginEntity(et_user_id.getText().toString(), et_password.getText().toString());
            callLogin(loginEntity);
        }
    }

    private void callLogin(LoginEntity loginEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            final Dialog dialog = ViewUtils.getProgressBar(LoginActivity.this);
            Call call = apiService.LoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    dialog.dismiss();
                    LoginResponse authResponse = (LoginResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Log.i("token:::", authResponse.getData().getAccessToken());
                            AppCommon.getInstance(LoginActivity.this).setUserObject(new Gson().toJson(authResponse.getData()));
                            AppCommon.getInstance(LoginActivity.this).setToken(authResponse.getData().getAccessToken());
                            AppCommon.getInstance(LoginActivity.this).setUserLogin(et_user_id.getText().toString().trim(), true);
                            AppCommon.getInstance(LoginActivity.this).setPassword(et_password.getText().toString().trim());
                            AppCommon.getInstance(LoginActivity.this).setSesstionId(authResponse.getData().getSession_id());
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //   AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Server Error");
                        Toast.makeText(LoginActivity.this, "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.txt_sign_up)
    public void sign_up() {

    }
}
