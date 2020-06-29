package com.imuons.globalfunds.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.globalfunds.R;
import com.imuons.globalfunds.dataModel.LoginDataModel;
import com.imuons.globalfunds.entity.UpdateProfileEnitity;
import com.imuons.globalfunds.responseModel.CommonResponse;
import com.imuons.globalfunds.responseModel.ProfileData;
import com.imuons.globalfunds.responseModel.ProfileResponse;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    @BindView(R.id.country_category)
    Spinner country_category;

    @BindView(R.id.et_fullName)
    EditText et_fullName;

    @BindView(R.id.et_emailId)
    EditText et_emailId;

    @BindView(R.id.et_phoneNum)
    EditText et_phoneNum;

    @BindView(R.id.et_BTCAddress)
    EditText et_BTCAddress;
    @BindView(R.id.otpLayout)
    RelativeLayout otpLayout;
    @BindView(R.id.etOtp)
    EditText etOtp;
    @BindView(R.id.sentOtpText)
    TextView sentOtpText;

    List<String> countries ;

    HashMap<String, String> countryMap = new HashMap<>();

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        String[] locales = Locale.getISOCountries();
      countries = new ArrayList<>();
        callProfileInfoApi();
        countries.add("**Select Country**");

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            countries.add(obj.getDisplayCountry());
            countryMap.put( obj.getCountry() , obj.getDisplayCountry());

        }
        Collections.sort(countries);
        /*country_category.se
         country_category.setItems(countries);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfileFragment.this.getContext(),
                android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_category.setAdapter(adapter);
        country_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void callProfileInfoApi() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            final Dialog dialog = ViewUtils.getProgressBar(EditProfileFragment.this.getActivity());
            Call call = apiService.profileApi();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                    dialog.dismiss();
                    ProfileResponse authResponse = (ProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(EditProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditProfileFragment.this.getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                    Toast.makeText(EditProfileFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(ProfileData data) {
      //  LoginDataModel loginDataModel = new Gson().fromJson(AppCommon.getInstance(EditEditProfileFragment.this.getContext()).getUserObject() , LoginDataModel.class);
        et_fullName.setText(data.getFullname());
        et_emailId.setText(data.getEmail());
        et_phoneNum.setText(data.getMobile());
        et_BTCAddress.setText(data.getBtcAddress());
        /*int i = 0;
        for (Map.Entry<String, String> entry : countryMap.entrySet()) {

            if (entry.getValue().equals(data.getCountry())) {
                System.out.println(entry.getKey());
                country_category.setSelection(i);
            }
            i++;
        }*/
       /* if(data.getCountry() != null && !data.getCountry().isEmpty()){
            for(int i = 0 ; i < countryMap.size() ; i++ ){
                if()
            }
        }*/
        if(data.getCountry() != null && !data.getCountry().isEmpty()) {
            country_category.setSelection(countries.indexOf(countryMap.get(data.getCountry())));
        }

    }

    @OnClick(R.id.btnUpdate)
    void update(){
        callupdate();
    }
    @OnClick(R.id.submitBtn)
    void submit(){
        String otpStr = etOtp.getText().toString().trim();
        if (otpStr.isEmpty() && otpStr.length() != 6){
            etOtp.setError("Please enter valid OTP");
        }else {
            etOtp.setText("");
            otpLayout.setVisibility(View.GONE);
            String name = et_fullName.getText().toString().trim();
            String mobile = et_phoneNum.getText().toString().trim();
            String email = et_emailId.getText().toString().trim();
            String btcAddress = et_BTCAddress.getText().toString().trim();
            String country = "";
            if (country_category.getSelectedItemPosition() != 0) {
                for (Map.Entry<String, String> entry : countryMap.entrySet()) {

                    if (entry.getValue().equals(countries.get(country_category.getSelectedItemPosition()))) {
                        System.out.println(entry.getKey());
                        country = entry.getKey();
                    }
                }
            }
            if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
                AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
                AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
                final Dialog dialog = ViewUtils.getProgressBar(EditProfileFragment.this.getActivity());
                Call call = apiService.updateProfile(new UpdateProfileEnitity(btcAddress, country, email, name, mobile, otpStr));
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                        dialog.dismiss();
                        CommonResponse authResponse = (CommonResponse) response.body();
                        if (authResponse != null) {
                            Log.i("LoginResponse::", new Gson().toJson(authResponse));
                            if (authResponse.getCode() == 200) {
                                Toast.makeText(EditProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditProfileFragment.this.getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dialog.dismiss();
                        AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                        Toast.makeText(EditProfileFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                // no internet
                Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.cancelBtn)
    void cancel(){
        etOtp.setText("");
        otpLayout.setVisibility(View.GONE);
    }

    private void callupdate() {
        String name = et_fullName.getText().toString().trim();
        String mobile = et_phoneNum.getText().toString().trim();
        String email = et_emailId.getText().toString().trim();
        String btcAddress = et_BTCAddress.getText().toString().trim();
        String country = "";
        if(country_category.getSelectedItemPosition() != 0){
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
        }  else if (mobile.isEmpty()) {
            et_phoneNum.setError("please enter your mobile number");
        } else {

            if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
                AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
                AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
                final Dialog dialog = ViewUtils.getProgressBar(EditProfileFragment.this.getActivity());
                Call call = apiService.updateOtp();
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                        dialog.dismiss();
                        CommonResponse authResponse = (CommonResponse) response.body();
                        if (authResponse != null) {
                            Log.i("LoginResponse::", new Gson().toJson(authResponse));
                            if (authResponse.getCode() == 200) {
                                otpLayout.setVisibility(View.VISIBLE);
                                sentOtpText.setText(authResponse.getMessage());
                                Toast.makeText(EditProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditProfileFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditProfileFragment.this.getContext(), "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dialog.dismiss();
                        AppCommon.getInstance(EditProfileFragment.this.getContext()).clearNonTouchableFlags(EditProfileFragment.this.getActivity());
                        Toast.makeText(EditProfileFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                // no internet
                Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
