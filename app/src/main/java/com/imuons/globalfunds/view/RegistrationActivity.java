package com.imuons.globalfunds.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.entity.RegisterEntity;
import com.imuons.globalfunds.fragment.EditProfileFragment;
import com.imuons.globalfunds.responseModel.RegisterResponse;
import com.imuons.globalfunds.retrofit.AppService;
import com.imuons.globalfunds.retrofit.ServiceGenerator;
import com.imuons.globalfunds.utils.AppCommon;
import com.imuons.globalfunds.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.et_fullName)
    EditText et_fullName;
    @BindView(R.id.et_emailId)
    EditText et_emailId;
    @BindView(R.id.et_referralId)
    EditText et_referralId;
    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_confirmPassword)
    EditText et_confirmPassword;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.txtLogin)
    TextView txtLogin;
    @BindView(R.id.country_category)
    Spinner country_category;
    List<String> countries;

    HashMap<String, String> countryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);


        String[] locales = Locale.getISOCountries();
        countries = new ArrayList<>();
        countries.add("**Select Country**");

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            countries.add(obj.getDisplayCountry());
            countryMap.put(obj.getCountry(), obj.getDisplayCountry());

        }
        Collections.sort(countries);
        /*country_category.se
         country_category.setItems(countries);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_category.setAdapter(adapter);
    }

    public void createAccount(View view) {
        String name = et_fullName.getText().toString().trim();
        String referralCode = et_referralId.getText().toString().trim();
        String mobile = et_phoneNum.getText().toString().trim();
        String email = et_emailId.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String cmf_password = et_confirmPassword.getText().toString().trim();
        String country = "";
        if (country_category.getSelectedItemPosition() != 0) {
            for (Map.Entry<String, String> entry : countryMap.entrySet()) {

                if (entry.getValue().equals(countries.get(country_category.getSelectedItemPosition()))) {
                    System.out.println(entry.getKey());
                    country = entry.getKey();
                }
            }
        }
        if (name.isEmpty()) {
            et_fullName.setError("Please enter your full name");
        } else if (email.isEmpty()) {
            et_emailId.setError("please enter your email id");
        } else if (referralCode.isEmpty()) {
            et_referralId.setError("Please enter your referral code");
        } else if (mobile.isEmpty()) {
            et_phoneNum.setError("please enter your mobile number");
        }  else if (country_category.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select the country", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            et_password.setError("Please enter your password");
        } else if (cmf_password.isEmpty()) {
            et_confirmPassword.setError("Please enter confirm password");
        } else if (!cmf_password.matches(password)) {
            et_confirmPassword.setError("password not match");
        } else if (!checkbox.isChecked()) {
            Toast.makeText(this, "Please click on check term & condition", Toast.LENGTH_SHORT).show();
        } else {
            if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
                AppCommon.getInstance(this).setNonTouchableFlags(this);
                AppService apiService = ServiceGenerator.createService(AppService.class);
                final Dialog dialog = ViewUtils.getProgressBar(RegistrationActivity.this);
                Call call = apiService.RegisterApi(new RegisterEntity(name, email, mobile, password, cmf_password, referralCode , country));
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        AppCommon.getInstance(RegistrationActivity.this).clearNonTouchableFlags(RegistrationActivity.this);
                        dialog.dismiss();
                        RegisterResponse authResponse = (RegisterResponse) response.body();
                        if (authResponse != null) {
                            Log.i("LoginResponse::", new Gson().toJson(authResponse));
                            if (authResponse.getCode() == 200) {
                                AppCommon.getInstance(RegistrationActivity.this).setPassword(et_password.getText().toString().trim());
                                AppCommon.getInstance(RegistrationActivity.this).setName(et_fullName.getText().toString().trim());

                                startActivity(new Intent(RegistrationActivity.this, CongratulationPage.class).putExtra("userId", authResponse.getData().getUserid()));
                                // AppCommon.getInstance(RegistrationActivity.this).showDialog(RegistrationActivity.this, "Hello " + name + "\nYour user UserId is: " + authResponse.getData().getUserid() + "\n and Password is: " + password + "\nPlease login with userId and password");
                                Toast.makeText(RegistrationActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistrationActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //   AppCommon.getInstance(RegistrationActivity.this).showDialog(RegistrationActivity.this, "Server Error");
                            Toast.makeText(RegistrationActivity.this, "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dialog.dismiss();
                        AppCommon.getInstance(RegistrationActivity.this).clearNonTouchableFlags(RegistrationActivity.this);
                        // loaderView.setVisibility(View.GONE);
                        Toast.makeText(RegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                // no internet
                Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gotoLogin(View view) {

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

    }
}
