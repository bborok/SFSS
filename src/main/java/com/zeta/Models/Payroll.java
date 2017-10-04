package com.zeta.Models;

import java.util.ArrayList;
import java.util.List;

/*
 * com.zeta.Models.Payroll class
 */
public class Payroll {

    private int accountCode;

    // Not sure if we want payroll to display 1 user or a list of them
    private List<User> users = new ArrayList<User>();

    public Payroll(int accountCode) {
        this.accountCode = accountCode;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }
}