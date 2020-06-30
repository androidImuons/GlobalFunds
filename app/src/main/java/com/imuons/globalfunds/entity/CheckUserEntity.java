package com.imuons.globalfunds.entity;

import java.io.Serializable;

public class CheckUserEntity implements Serializable {
    String address ,network_type;

    public CheckUserEntity(String address) {
        this.address = address;
        this.network_type = "BTC";
    }
}
