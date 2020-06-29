package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnGoingPaymentRecord {
    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price_in_usd")
    @Expose
    private Integer priceInUsd;
    @SerializedName("currency_price")
    @Expose
    private Double currencyPrice;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("product_url")
    @Expose
    private String productUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("plan_id")
    @Expose
    private Integer planId;
    @SerializedName("in_status")
    @Expose
    private Integer inStatus;
    @SerializedName("remark")
    @Expose
    private Object remark;
    @SerializedName("trans_hash")
    @Expose
    private Object transHash;
    @SerializedName("rec_amt")
    @Expose
    private Double recAmt;
    @SerializedName("top_up_status")
    @Expose
    private Integer topUpStatus;
    @SerializedName("top_up_date")
    @Expose
    private Object topUpDate;
    @SerializedName("plan_name")
    @Expose
    private String planName;
    @SerializedName("rupee_plan_name")
    @Expose
    private String rupeePlanName;
    private final static long serialVersionUID = 6508341821363500956L;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriceInUsd() {
        return priceInUsd;
    }

    public void setPriceInUsd(Integer priceInUsd) {
        this.priceInUsd = priceInUsd;
    }

    public Double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(Double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getInStatus() {
        return inStatus;
    }

    public void setInStatus(Integer inStatus) {
        this.inStatus = inStatus;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getTransHash() {
        return transHash;
    }

    public void setTransHash(Object transHash) {
        this.transHash = transHash;
    }

    public Double getRecAmt() {
        return recAmt;
    }

    public void setRecAmt(Double recAmt) {
        this.recAmt = recAmt;
    }

    public Integer getTopUpStatus() {
        return topUpStatus;
    }

    public void setTopUpStatus(Integer topUpStatus) {
        this.topUpStatus = topUpStatus;
    }

    public Object getTopUpDate() {
        return topUpDate;
    }

    public void setTopUpDate(Object topUpDate) {
        this.topUpDate = topUpDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getRupeePlanName() {
        return rupeePlanName;
    }

    public void setRupeePlanName(String rupeePlanName) {
        this.rupeePlanName = rupeePlanName;
    }

}
