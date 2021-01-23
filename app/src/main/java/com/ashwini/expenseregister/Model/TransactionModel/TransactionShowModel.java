package com.ashwini.expenseregister.Model.TransactionModel;

public class TransactionShowModel {

    String transaction_name;
    String transaction_desc;
    double transaction_amt;
    String transaction_type;

    public TransactionShowModel(String transaction_name, String transaction_desc, double transaction_amt, String transaction_type) {
        this.transaction_name = transaction_name;
        this.transaction_desc = transaction_desc;
        this.transaction_amt = transaction_amt;
        this.transaction_type = transaction_type;
    }

    public String getTransaction_name() {
        return transaction_name;
    }

    public void setTransaction_name(String transaction_name) {
        this.transaction_name = transaction_name;
    }

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    public double getTransaction_amt() {
        return transaction_amt;
    }

    public void setTransaction_amt(double transaction_amt) {
        this.transaction_amt = transaction_amt;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
