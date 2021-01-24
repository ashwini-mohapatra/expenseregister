package com.ashwini.expenseregister.Model.TotalTransactionsModel;

public class TotalModel {
    double balance;
    double saving;
    double expenditure;

    public TotalModel(double balance, double saving, double expenditure) {
        this.balance = balance;
        this.saving = saving;
        this.expenditure = expenditure;
    }

    public double getIncome() {
        return balance;
    }

    public void setIncome(double balance) {
        this.balance = balance;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }
}
