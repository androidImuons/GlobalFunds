package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardDataModel {
    @SerializedName("btc_address")
    @Expose
    private String btcAddress;
    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("btc")
    @Expose
    private Integer btc;
    @SerializedName("usd")
    @Expose
    private Double usd;
    @SerializedName("total_investment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("active_investment")
    @Expose
    private Integer activeInvestment;
    @SerializedName("total_withdraw")
    @Expose
    private Integer totalWithdraw;
    @SerializedName("total_profit")
    @Expose
    private Double totalProfit;
    @SerializedName("direct_income")
    @Expose
    private Integer directIncome;
    @SerializedName("direct_income_withdraw")
    @Expose
    private Integer directIncomeWithdraw;
    @SerializedName("direct_wallet_balance")
    @Expose
    private Integer directWalletBalance;
    @SerializedName("level_income")
    @Expose
    private Double levelIncome;
    @SerializedName("level_income_withdraw")
    @Expose
    private Double levelIncomeWithdraw;
    @SerializedName("level_income_balance")
    @Expose
    private Integer levelIncomeBalance;
    @SerializedName("roi_income")
    @Expose
    private double roiIncome;
    @SerializedName("roi_income_withdraw")
    @Expose
    private double roiIncomeWithdraw;
    @SerializedName("roi_income_balance")
    @Expose
    private Integer roiIncomeBalance;
    @SerializedName("binary_income")
    @Expose
    private Integer binaryIncome;
    @SerializedName("binary_income_withdraw")
    @Expose
    private Integer binaryIncomeWithdraw;
    @SerializedName("binary_income_balance")
    @Expose
    private Integer binaryIncomeBalance;
    @SerializedName("direct_income_balance")
    @Expose
    private Integer directIncomeBalance;
    @SerializedName("top_up_wallet")
    @Expose
    private Integer topUpWallet;
    @SerializedName("top_up_wallet_withdraw")
    @Expose
    private Integer topUpWalletWithdraw;
    @SerializedName("top_up_Wallet_balance")
    @Expose
    private Integer topUpWalletBalance;
    @SerializedName("transfer_wallet")
    @Expose
    private Integer transferWallet;
    @SerializedName("transfer_wallet_withdraw")
    @Expose
    private Integer transferWalletWithdraw;
    @SerializedName("transfer_Wallet_balance")
    @Expose
    private Integer transferWalletBalance;
    @SerializedName("working_wallet")
    @Expose
    private Double workingWallet;
    @SerializedName("working_wallet_withdraw")
    @Expose
    private Integer workingWalletWithdraw;
    @SerializedName("working_Wallet_balance")
    @Expose
    private Double workingWalletBalance;
    @SerializedName("leadership_income")
    @Expose
    private Integer leadershipIncome;
    @SerializedName("leadership_income_withdraw")
    @Expose
    private Integer leadershipIncomeWithdraw;
    @SerializedName("leadership_Wallet_balance")
    @Expose
    private Integer leadershipWalletBalance;
    @SerializedName("level_income_roi")
    @Expose
    private double levelIncomeRoi;
    @SerializedName("level_income_roi_withdraw")
    @Expose
    private Integer levelIncomeRoiWithdraw;
    @SerializedName("level_income_roi_balance")
    @Expose
    private Integer levelIncomeRoiBalance;
    @SerializedName("upline_income")
    @Expose
    private Integer uplineIncome;
    @SerializedName("upline_income_withdraw")
    @Expose
    private Integer uplineIncomeWithdraw;
    @SerializedName("upline_balance")
    @Expose
    private Integer uplineBalance;
    @SerializedName("award_income")
    @Expose
    private Integer awardIncome;
    @SerializedName("award_income_withdraw")
    @Expose
    private Integer awardIncomeWithdraw;
    @SerializedName("award_balance")
    @Expose
    private Integer awardBalance;
    @SerializedName("promotional_income")
    @Expose
    private Integer promotionalIncome;
    @SerializedName("server_time")
    @Expose
    private ServerTimeZone serverTime;
    @SerializedName("joining_date")
    @Expose
    private String joiningDate;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("current_time")
    @Expose
    private Boolean currentTime;
    @SerializedName("total_income")
    @Expose
    private Double totalIncome;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("login_time")
    @Expose
    private String loginTime;

  public double getTotal_business() {
        return total_business;
    }

    void setTotal_business(double total_business) {
        this.total_business = total_business;
    }

    @SerializedName("total_business")
    @Expose
    double total_business;

    private final static long serialVersionUID = -9043078288929960655L;

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getBtc() {
        return btc;
    }

    public void setBtc(Integer btc) {
        this.btc = btc;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public Integer getActiveInvestment() {
        return activeInvestment;
    }

    public void setActiveInvestment(Integer activeInvestment) {
        this.activeInvestment = activeInvestment;
    }

    public Integer getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(Integer totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getDirectIncomeWithdraw() {
        return directIncomeWithdraw;
    }

    public void setDirectIncomeWithdraw(Integer directIncomeWithdraw) {
        this.directIncomeWithdraw = directIncomeWithdraw;
    }

    public Integer getDirectWalletBalance() {
        return directWalletBalance;
    }

    public void setDirectWalletBalance(Integer directWalletBalance) {
        this.directWalletBalance = directWalletBalance;
    }

    public Double getLevelIncome() {
        return levelIncome;
    }

    public void setLevelIncome(Double levelIncome) {
        this.levelIncome = levelIncome;
    }

    public Double getLevelIncomeWithdraw() {
        return levelIncomeWithdraw;
    }

    public void setLevelIncomeWithdraw(Double levelIncomeWithdraw) {
        this.levelIncomeWithdraw = levelIncomeWithdraw;
    }

    public Integer getLevelIncomeBalance() {
        return levelIncomeBalance;
    }

    public void setLevelIncomeBalance(Integer levelIncomeBalance) {
        this.levelIncomeBalance = levelIncomeBalance;
    }

    public double getRoiIncome() {
        return roiIncome;
    }

    public void setRoiIncome(double roiIncome) {
        this.roiIncome = roiIncome;
    }

    public double getRoiIncomeWithdraw() {
        return roiIncomeWithdraw;
    }

    public void setRoiIncomeWithdraw(double roiIncomeWithdraw) {
        this.roiIncomeWithdraw = roiIncomeWithdraw;
    }

    public Integer getRoiIncomeBalance() {
        return roiIncomeBalance;
    }

    public void setRoiIncomeBalance(Integer roiIncomeBalance) {
        this.roiIncomeBalance = roiIncomeBalance;
    }

    public Integer getBinaryIncome() {
        return binaryIncome;
    }

    public void setBinaryIncome(Integer binaryIncome) {
        this.binaryIncome = binaryIncome;
    }

    public Integer getBinaryIncomeWithdraw() {
        return binaryIncomeWithdraw;
    }

    public void setBinaryIncomeWithdraw(Integer binaryIncomeWithdraw) {
        this.binaryIncomeWithdraw = binaryIncomeWithdraw;
    }

    public Integer getBinaryIncomeBalance() {
        return binaryIncomeBalance;
    }

    public void setBinaryIncomeBalance(Integer binaryIncomeBalance) {
        this.binaryIncomeBalance = binaryIncomeBalance;
    }

    public Integer getDirectIncomeBalance() {
        return directIncomeBalance;
    }

    public void setDirectIncomeBalance(Integer directIncomeBalance) {
        this.directIncomeBalance = directIncomeBalance;
    }

    public Integer getTopUpWallet() {
        return topUpWallet;
    }

    public void setTopUpWallet(Integer topUpWallet) {
        this.topUpWallet = topUpWallet;
    }

    public Integer getTopUpWalletWithdraw() {
        return topUpWalletWithdraw;
    }

    public void setTopUpWalletWithdraw(Integer topUpWalletWithdraw) {
        this.topUpWalletWithdraw = topUpWalletWithdraw;
    }

    public Integer getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(Integer topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }

    public Integer getTransferWallet() {
        return transferWallet;
    }

    public void setTransferWallet(Integer transferWallet) {
        this.transferWallet = transferWallet;
    }

    public Integer getTransferWalletWithdraw() {
        return transferWalletWithdraw;
    }

    public void setTransferWalletWithdraw(Integer transferWalletWithdraw) {
        this.transferWalletWithdraw = transferWalletWithdraw;
    }

    public Integer getTransferWalletBalance() {
        return transferWalletBalance;
    }

    public void setTransferWalletBalance(Integer transferWalletBalance) {
        this.transferWalletBalance = transferWalletBalance;
    }

    public Double getWorkingWallet() {
        return workingWallet;
    }

    public void setWorkingWallet(Double workingWallet) {
        this.workingWallet = workingWallet;
    }

    public Integer getWorkingWalletWithdraw() {
        return workingWalletWithdraw;
    }

    public void setWorkingWalletWithdraw(Integer workingWalletWithdraw) {
        this.workingWalletWithdraw = workingWalletWithdraw;
    }

    public Double getWorkingWalletBalance() {
        return workingWalletBalance;
    }

    public void setWorkingWalletBalance(Double workingWalletBalance) {
        this.workingWalletBalance = workingWalletBalance;
    }

    public Integer getLeadershipIncome() {
        return leadershipIncome;
    }

    public void setLeadershipIncome(Integer leadershipIncome) {
        this.leadershipIncome = leadershipIncome;
    }

    public Integer getLeadershipIncomeWithdraw() {
        return leadershipIncomeWithdraw;
    }

    public void setLeadershipIncomeWithdraw(Integer leadershipIncomeWithdraw) {
        this.leadershipIncomeWithdraw = leadershipIncomeWithdraw;
    }

    public Integer getLeadershipWalletBalance() {
        return leadershipWalletBalance;
    }

    public void setLeadershipWalletBalance(Integer leadershipWalletBalance) {
        this.leadershipWalletBalance = leadershipWalletBalance;
    }

    public double getLevelIncomeRoi() {
        return levelIncomeRoi;
    }

    public void setLevelIncomeRoi(double levelIncomeRoi) {
        this.levelIncomeRoi = levelIncomeRoi;
    }

    public Integer getLevelIncomeRoiWithdraw() {
        return levelIncomeRoiWithdraw;
    }

    public void setLevelIncomeRoiWithdraw(Integer levelIncomeRoiWithdraw) {
        this.levelIncomeRoiWithdraw = levelIncomeRoiWithdraw;
    }

    public Integer getLevelIncomeRoiBalance() {
        return levelIncomeRoiBalance;
    }

    public void setLevelIncomeRoiBalance(Integer levelIncomeRoiBalance) {
        this.levelIncomeRoiBalance = levelIncomeRoiBalance;
    }

    public Integer getUplineIncome() {
        return uplineIncome;
    }

    public void setUplineIncome(Integer uplineIncome) {
        this.uplineIncome = uplineIncome;
    }

    public Integer getUplineIncomeWithdraw() {
        return uplineIncomeWithdraw;
    }

    public void setUplineIncomeWithdraw(Integer uplineIncomeWithdraw) {
        this.uplineIncomeWithdraw = uplineIncomeWithdraw;
    }

    public Integer getUplineBalance() {
        return uplineBalance;
    }

    public void setUplineBalance(Integer uplineBalance) {
        this.uplineBalance = uplineBalance;
    }

    public Integer getAwardIncome() {
        return awardIncome;
    }

    public void setAwardIncome(Integer awardIncome) {
        this.awardIncome = awardIncome;
    }

    public Integer getAwardIncomeWithdraw() {
        return awardIncomeWithdraw;
    }

    public void setAwardIncomeWithdraw(Integer awardIncomeWithdraw) {
        this.awardIncomeWithdraw = awardIncomeWithdraw;
    }

    public Integer getAwardBalance() {
        return awardBalance;
    }

    public void setAwardBalance(Integer awardBalance) {
        this.awardBalance = awardBalance;
    }

    public Integer getPromotionalIncome() {
        return promotionalIncome;
    }

    public void setPromotionalIncome(Integer promotionalIncome) {
        this.promotionalIncome = promotionalIncome;
    }

    public ServerTimeZone getServerTime() {
        return serverTime;
    }

    public void setServerTime(ServerTimeZone serverTime) {
        this.serverTime = serverTime;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Boolean currentTime) {
        this.currentTime = currentTime;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}
