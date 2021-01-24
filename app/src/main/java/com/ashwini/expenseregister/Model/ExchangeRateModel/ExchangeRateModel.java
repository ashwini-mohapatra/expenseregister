package com.ashwini.expenseregister.Model.ExchangeRateModel;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ExchangeRateModel {
    @SerializedName("base")
    String base;
    @SerializedName("date")
    String date;
//    @SerializedName("success")
//    boolean success;
//    @SerializedName("timestamp")
//    long timestamp;
    @SerializedName("rates")
    HashMap<String, Double> rates;

    public ExchangeRateModel(String base, String date, HashMap<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}
