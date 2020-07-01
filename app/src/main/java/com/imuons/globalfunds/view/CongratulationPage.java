package com.imuons.globalfunds.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.utils.AppCommon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CongratulationPage extends Activity {

    @BindView(R.id.userId)
    TextView userId;
    @BindView(R.id.txtPassword)
    TextView txtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cong_reg_activity);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            userId.setText("UserId : " + getIntent().getStringExtra("userId"));
            txtPassword.setText("Password : "+ AppCommon.getInstance(CongratulationPage.this).getPassword());

        }
    }

    @OnClick(R.id.loginBtn)
    void loginBtn() {
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }
}
