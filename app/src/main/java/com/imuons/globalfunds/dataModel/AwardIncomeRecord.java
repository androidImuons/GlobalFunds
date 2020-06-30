package com.imuons.globalfunds.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwardIncomeRecord {
    @SerializedName("award_id")
    @Expose
    private Integer awardId;
    @SerializedName("downline_needed")
    @Expose
    private Integer downlineNeeded;
    @SerializedName("business_required")
    @Expose
    private Integer businessRequired;
    @SerializedName("reward")
    @Expose
    private Integer reward;
    @SerializedName("direct")
    @Expose
    private Integer direct;
    @SerializedName("qualified_bv")
    @Expose
    private Integer qualifiedBv;
    @SerializedName("award")
    @Expose
    private Integer award;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("time_period")
    @Expose
    private Integer timePeriod;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Integer getDownlineNeeded() {
        return downlineNeeded;
    }

    public void setDownlineNeeded(Integer downlineNeeded) {
        this.downlineNeeded = downlineNeeded;
    }

    public Integer getBusinessRequired() {
        return businessRequired;
    }

    public void setBusinessRequired(Integer businessRequired) {
        this.businessRequired = businessRequired;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
    }

    public Integer getQualifiedBv() {
        return qualifiedBv;
    }

    public void setQualifiedBv(Integer qualifiedBv) {
        this.qualifiedBv = qualifiedBv;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Integer timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
