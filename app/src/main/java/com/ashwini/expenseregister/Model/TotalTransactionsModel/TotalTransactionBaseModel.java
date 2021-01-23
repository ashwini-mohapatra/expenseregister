package com.ashwini.expenseregister.Model.TotalTransactionsModel;

import java.util.ArrayList;

public class TotalTransactionBaseModel {
    ArrayList<TotalTransactionModel> ttm;

    public TotalTransactionBaseModel(ArrayList<TotalTransactionModel> ttm) {
        this.ttm = ttm;
    }

    public ArrayList<TotalTransactionModel> getTtm() {
        return ttm;
    }

    public void setTtm(ArrayList<TotalTransactionModel> ttm) {
        this.ttm = ttm;
    }
}
