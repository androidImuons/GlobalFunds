package com.imuons.globalfunds.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OngoingDataModel {
    @SerializedName("recordsTotal")
    @Expose
    private Integer recordsTotal;
    @SerializedName("recordsFiltered")
    @Expose
    private Integer recordsFiltered;
    @SerializedName("records")
    @Expose
    private List<OnGoingPaymentRecord> records = null;
    private final static long serialVersionUID = 6073853067413872352L;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<OnGoingPaymentRecord> getRecords() {
        return records;
    }

    public void setRecords(List<OnGoingPaymentRecord> records) {
        this.records = records;
    }
}
