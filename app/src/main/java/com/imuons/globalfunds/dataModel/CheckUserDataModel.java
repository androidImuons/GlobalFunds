package com.imuons.globalfunds.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckUserDataModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;
    private final static long serialVersionUID = -3730762104237390363L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }
}
