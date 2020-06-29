package com.imuons.globalfunds.retrofit;

import com.imuons.globalfunds.dataModel.CheckUser;
import com.imuons.globalfunds.dataModel.ForgotPasswordResponse;
import com.imuons.globalfunds.entity.LoginEntity;
import com.imuons.globalfunds.entity.RegisterEntity;
import com.imuons.globalfunds.responseModel.DashBoardResponseModel;
import com.imuons.globalfunds.responseModel.GetAddressResponse;
import com.imuons.globalfunds.responseModel.LoginResponse;
import com.imuons.globalfunds.responseModel.OngoingPaymentResponseModel;
import com.imuons.globalfunds.responseModel.PckageResponseModel;
import com.imuons.globalfunds.responseModel.ProfileResponse;
import com.imuons.globalfunds.responseModel.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppService {
    @POST("login")
    Call<LoginResponse> LoginApi(@Body LoginEntity loginEntity);//user_id,password,

    @POST("checkuserexist")
    Call<CheckUser> checkUser(@Body Map<String, Object> param);//user_id

    @POST("reset-passwordlink")
    Call<ForgotPasswordResponse> forgotParssword(@Body Map<String, Object> map);//user_id

    @POST("register")
    Call<RegisterResponse> RegisterApi(@Body RegisterEntity loginEntity);

    @POST("get-profile-info")
    Call<ProfileResponse> profileApi();

    @GET("get-user-dashboard")
    Call<DashBoardResponseModel> GetDashBoard();

    @GET("get-packages")
    Call<PckageResponseModel> GetPackage();

    @POST("getaddress")
    Call<GetAddressResponse> GetAddress(@Body Map<String, Object> map);

    @POST("pending-deposit")
    Call<OngoingPaymentResponseModel> GetPendingDeposit(@Body Map<String, Object> map);

}
