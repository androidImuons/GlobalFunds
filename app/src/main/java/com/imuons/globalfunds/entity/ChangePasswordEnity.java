package com.imuons.globalfunds.entity;

import java.io.Serializable;

public class ChangePasswordEnity implements Serializable {
    /*ChangePasswordFragment*/
    String current_pwd , new_pwd , conf_pwd;

    public ChangePasswordEnity(String current_pwd, String new_pwd, String conf_pwd) {
        this.current_pwd = current_pwd;
        this.new_pwd = new_pwd;
        this.conf_pwd = conf_pwd;
    }
}
