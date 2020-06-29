package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAddressDataModel {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("network_type")
    @Expose
    private String networkType;
    @SerializedName("price_in_usd")
    @Expose
    private Integer priceInUsd;
    @SerializedName("price_in_currency")
    @Expose
    private Double priceInCurrency;
    @SerializedName("received_amount")
    @Expose
    private Double receivedAmount;
    private final static long serialVersionUID = 4766626420786548605L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public Integer getPriceInUsd() {
        return priceInUsd;
    }

    public void setPriceInUsd(Integer priceInUsd) {
        this.priceInUsd = priceInUsd;
    }

    public Double getPriceInCurrency() {
        return priceInCurrency;
    }

    public void setPriceInCurrency(Double priceInCurrency) {
        this.priceInCurrency = priceInCurrency;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }
}
