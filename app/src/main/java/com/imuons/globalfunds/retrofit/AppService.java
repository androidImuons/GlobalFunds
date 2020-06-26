package com.imuons.globalfunds.retrofit;

import com.imuons.globalfunds.entity.LoginEntity;
import com.imuons.globalfunds.responseModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppService {
    @POST("login")
    Call<LoginResponse> LoginApi(@Body LoginEntity loginEntity);

}
