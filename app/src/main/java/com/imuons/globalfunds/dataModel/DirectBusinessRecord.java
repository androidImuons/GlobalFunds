package com.imuons.globalfunds.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectBusinessRecord {
    @SerializedName("total_business")
    @Expose
    private Integer totalBusiness;

    public Integer getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(Integer totalBusiness) {
        this.totalBusiness = totalBusiness;
    }
}
