package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {
    @SerializedName("sponser")
    @Expose
    private String sponser;
    @SerializedName("sponser_fullname")
    @Expose
    private String sponserFullname;
    @SerializedName("sponser_country")
    @Expose
    private String sponserCountry;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("btc_address")
    @Expose
    private Object btcAddress;
    @SerializedName("ethereum")
    @Expose
    private Object ethereum;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("ref_user_id")
    @Expose
    private String refUserId;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("code")
    @Expose
    private String code;

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getSponserFullname() {
        return sponserFullname;
    }

    public void setSponserFullname(String sponserFullname) {
        this.sponserFullname = sponserFullname;
    }

    public String getSponserCountry() {
        return sponserCountry;
    }

    public void setSponserCountry(String sponserCountry) {
        this.sponserCountry = sponserCountry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(Object btcAddress) {
        this.btcAddress = btcAddress;
    }

    public Object getEthereum() {
        return ethereum;
    }

    public void setEthereum(Object ethereum) {
        this.ethereum = ethereum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
