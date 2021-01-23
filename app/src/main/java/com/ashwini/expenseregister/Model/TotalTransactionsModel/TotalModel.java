package com.ashwini.expenseregister.Model.TotalTransactionsModel;

public class TotalModel {
    double income;
    double saving;
    double expenditure;

    public TotalModel(double income, double saving, double expenditure) {
        this.income = income;
        this.saving = saving;
        this.expenditure = expenditure;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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
