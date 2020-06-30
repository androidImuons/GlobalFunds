package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelIncomeRecordModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("tax_amount")
    @Expose
    private String taxAmount;
    @SerializedName("amt_pin")
    @Expose
    private String amtPin;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("laps_amount")
    @Expose
    private Integer lapsAmount;
    @SerializedName("toUserId")
    @Expose
    private String toUser;
    @SerializedName("fromUserId")
    @Expose
    private String fromUser;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("topup_id")
    @Expose
    private Integer topupId;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("to_user_id")
    @Expose
    private String toUserId;
    @SerializedName("from_user_id")
    @Expose
    private String fromUserId;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;
    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    private final static long serialVersionUID = -120281122603474780L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getAmtPin() {
        return amtPin;
    }

    public void setAmtPin(String amtPin) {
        this.amtPin = amtPin;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLapsAmount() {
        return lapsAmount;
    }

    public void setLapsAmount(Integer lapsAmount) {
        this.lapsAmount = lapsAmount;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTopupId() {
        return topupId;
    }

    public void setTopupId(Integer topupId) {
        this.topupId = topupId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
