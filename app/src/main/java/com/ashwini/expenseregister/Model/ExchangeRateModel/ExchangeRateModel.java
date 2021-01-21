package com.ashwini.expenseregister.Model.ExchangeRateModel;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ExchangeRateModel {
    @SerializedName("success")
    boolean success;
    @SerializedName("timestamp")
    long timestamp;
    @SerializedName("base")
    String base;
    @SerializedName("date")
    String date;
    @SerializedName("rates")
    HashMap<String, Double> rates;

    public ExchangeRateModel(boolean success, long timestamp, String base, String date, HashMap<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
