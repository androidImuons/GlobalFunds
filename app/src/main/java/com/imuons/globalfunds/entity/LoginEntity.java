package com.imuons.globalfunds.entity;

public class LoginEntity {
    String user_id , password , device_type , device_token,admin ;


    public LoginEntity(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
//        this.device_type = "A";
//        this.admin = admin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
