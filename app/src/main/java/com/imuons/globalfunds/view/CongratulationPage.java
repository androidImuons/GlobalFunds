package com.imuons.globalfunds.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.imuons.globalfunds.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CongratulationPage extends Activity {
     @BindView(R.id.userId)
    TextView userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cong_reg_activity);
        ButterKnife.bind(this);
        if(getIntent()!= null){
            userId.setText("UserId : "+getIntent().getStringExtra("userId"));
        }
    }

    @OnClick(R.id.loginBtn)
    void  loginBtn(){
        startActivity(new Intent(this , LoginActivity.class));
        finishAffinity();
    }
}
