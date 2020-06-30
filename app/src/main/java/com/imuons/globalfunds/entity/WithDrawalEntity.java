package com.imuons.globalfunds.entity;

import java.io.Serializable;

public class WithDrawalEntity implements Serializable {
    /*binary_income_balance: 0
direct_income_balance: 0
level_income_balance: 0
roi_balance: 0
topup_wallet: 0
transfer_wallet: 0
working_wallet: "100"*/
    String binary_income_balance , direct_income_balance , level_income_balance , roi_balance , topup_wallet , transfer_wallet , working_wallet;

    public WithDrawalEntity(String binary_income_balance, String direct_income_balance, String level_income_balance, String roi_balance, String topup_wallet, String transfer_wallet, String working_wallet) {
        this.binary_income_balance = binary_income_balance;
        this.direct_income_balance = direct_income_balance;
        this.level_income_balance = level_income_balance;
        this.roi_balance = roi_balance;
        this.topup_wallet = topup_wallet;
        this.transfer_wallet = transfer_wallet;
        this.working_wallet = working_wallet;
    }
}
