package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmPaymentRecordModel {

    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("usd_rate")
    @Expose
    private Integer usdRate;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("withdraw")
    @Expose
    private Integer withdraw;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("date_diff")
    @Expose
    private Integer dateDiff;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("top_amount")
    @Expose
    private Integer topAmount;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("deposit_type")
    @Expose
    private String depositType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("p_withdraw")
    @Expose
    private Integer pWithdraw;
    @SerializedName("showwithdraw")
    @Expose
    private Integer showwithdraw;
    private final static long serialVersionUID = 3460985825515872967L;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public Integer getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(Integer usdRate) {
        this.usdRate = usdRate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Integer withdraw) {
        this.withdraw = withdraw;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(Integer dateDiff) {
        this.dateDiff = dateDiff;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(Integer topAmount) {
        this.topAmount = topAmount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPWithdraw() {
        return pWithdraw;
    }

    public void setPWithdraw(Integer pWithdraw) {
        this.pWithdraw = pWithdraw;
    }

    public Integer getShowwithdraw() {
        return showwithdraw;
    }

    public void setShowwithdraw(Integer showwithdraw) {
        this.showwithdraw = showwithdraw;
    }

}
