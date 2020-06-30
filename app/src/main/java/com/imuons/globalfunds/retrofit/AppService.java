package com.imuons.globalfunds.retrofit;

import com.imuons.globalfunds.dataModel.CheckUser;
import com.imuons.globalfunds.dataModel.ForgotPasswordResponse;
import com.imuons.globalfunds.entity.ChangePasswordEnity;
import com.imuons.globalfunds.entity.LoginEntity;
import com.imuons.globalfunds.entity.RegisterEntity;
import com.imuons.globalfunds.entity.UpdateProfileEnitity;
import com.imuons.globalfunds.entity.WithDrawalEntity;
import com.imuons.globalfunds.responseModel.AwardIncomeReportResponse;
import com.imuons.globalfunds.responseModel.CommonResponse;
import com.imuons.globalfunds.responseModel.ConfirmPaymentReportResponse;
import com.imuons.globalfunds.responseModel.ConfirmWithdralReportResponse;
import com.imuons.globalfunds.responseModel.DashBoardResponseModel;
import com.imuons.globalfunds.responseModel.DirectBusinessReportResponse;
import com.imuons.globalfunds.responseModel.DirectUserResponseModel;
import com.imuons.globalfunds.responseModel.GetAddressResponse;
import com.imuons.globalfunds.responseModel.GetLevelResponse;
import com.imuons.globalfunds.responseModel.LevelIncomeReportResponse;
import com.imuons.globalfunds.responseModel.LevelROIIncomeReportResponse;
import com.imuons.globalfunds.responseModel.LevelViewResponseModel;
import com.imuons.globalfunds.responseModel.LoginResponse;
import com.imuons.globalfunds.responseModel.OngoingPaymentResponseModel;
import com.imuons.globalfunds.responseModel.PckageResponseModel;
import com.imuons.globalfunds.responseModel.PenddingWithdrwalResponse;
import com.imuons.globalfunds.responseModel.ProfileResponse;
import com.imuons.globalfunds.responseModel.ReferalLinkResponseModel;
import com.imuons.globalfunds.responseModel.RegisterResponse;
import com.imuons.globalfunds.responseModel.RoiIncomeReportResponse;

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
    @POST("topup-report")
    Call<ConfirmPaymentReportResponse> GetTopUPReport(@Body Map<String, Object> map);

    @POST("change-password")


    Call<CommonResponse>changePassword(
            @Body ChangePasswordEnity changeEntiy
    );

    @POST("sendOtp-update-user-profile")
    Call<CommonResponse>updateOtp(
    );
    @POST("checkotp1")
    Call<CommonResponse>updateProfile(
            @Body UpdateProfileEnitity upadteProfile
    );
    @GET("get-reference-id")
    Call<ReferalLinkResponseModel>GetReferal();

    //Income Report:- ROI Income Report
    @POST("roi-income")
    Call<RoiIncomeReportResponse> roiIncome(
            @Body Map<String, Object> map
    );
    @POST("level-income")
    Call<LevelIncomeReportResponse> level_income(
            @Body Map<String, Object> map
    );
    @POST("level-income-roi")
    Call<LevelROIIncomeReportResponse> level_income_roi(
            @Body Map<String, Object> map
    );

    //Income Report:- AwardIncome
    @POST("userWinnerList")
    Call<AwardIncomeReportResponse> awardIncome(
            @Body Map<String, Object> map
    );

    //Income Report:- Direct Business report
    @POST("direct-business")
    Call<DirectBusinessReportResponse> directBusinessReport(
            @Body Map<String, Object> map
    );

    @POST("direct_list")
    Call<DirectUserResponseModel> DirectUserApit(@Body Map<String, Object> map);
    @POST("level-view")
    Call<LevelViewResponseModel> levelViewApi(@Body Map<String, Object> map);
    @POST("get-level")
    Call<GetLevelResponse> GetlevelViewApi();

    @POST("withdraw-income")
    Call<CommonResponse>withDrawWorkingApi(
            @Body WithDrawalEntity withDrawalEntity
    );
    @POST("all-withdraw-pending-reports")
    Call<PenddingWithdrwalResponse>withDrawPendingApi(
            @Body Map<String, Object> map
    );

    @POST("all-withdraw-confirm-reports")

    Call<ConfirmWithdralReportResponse>cofirmWithdralApi(
            @Body Map<String, Object> map
    );


}
