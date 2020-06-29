package com.imuons.globalfunds.entity;

import java.io.Serializable;

public class UpdateProfileEnitity implements Serializable {
    String account_no , bank_name , branch_name, btc,country,email , fullname , holder_name , ifsc_code , mobile , otp , pan_no;

    public UpdateProfileEnitity(String btc, String country, String email, String fullname, String mobile, String otp) {
        this.account_no = "";
        this.bank_name = "";
        this.branch_name = "";
        this.holder_name = "";
        this.ifsc_code = "";
        this.pan_no = "";
        this.btc = btc;
        this.country = country;
        this.email = email;
        this.fullname = fullname;
        this.mobile = mobile;
        this.otp = otp;
    }
}
