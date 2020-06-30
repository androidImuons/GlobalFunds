package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferalDataModel {
    @SerializedName("link")
    @Expose
    private String link;
    private final static long serialVersionUID = 5568736098631236863L;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
