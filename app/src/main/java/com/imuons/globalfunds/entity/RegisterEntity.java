package com.imuons.globalfunds.entity;

import java.io.Serializable;

public class RegisterEntity implements Serializable {
    String fullname , email,mobile , password , password_confirmation , ref_user_id , address , country;

    public RegisterEntity(String fullname, String email, String mobile, String password, String password_confirmation, String ref_user_id) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.ref_user_id = ref_user_id;
        this.address = "";
        this.country = "";
    }
}
