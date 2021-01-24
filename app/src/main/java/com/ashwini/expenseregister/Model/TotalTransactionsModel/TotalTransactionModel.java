package com.ashwini.expenseregister.Model.TotalTransactionsModel;

import java.util.ArrayList;

public class TotalTransactionModel {
    ArrayList<TotalModel> tm;

    public TotalTransactionModel(ArrayList<TotalModel> tm) {
        this.tm = tm;
    }

    public ArrayList<TotalModel> getTm() {
        return tm;
    }

    public void setTm(ArrayList<TotalModel> tm) {
        this.tm = tm;
    }
}
