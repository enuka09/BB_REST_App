package com.example.bb_rest_app;

public class Purchases {
    private int purchase_id;
    private String cus_username;
    private String product_id;
    private String product_name;
    private double product_price;
    private double loan_balance;
    private double installment_1;
    private double installment_2;
    private double installment_3;

    public Purchases(int purchase_id, String cus_username, String product_id, String product_name, double product_price, double loan_balance, double installment_1, double installment_2, double installment_3) {
        this.purchase_id = purchase_id;
        this.cus_username = cus_username;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.loan_balance = loan_balance;
        this.installment_1 = installment_1;
        this.installment_2 = installment_2;
        this.installment_3 = installment_3;
    }

    public Purchases() {

    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getCus_username() {
        return cus_username;
    }

    public void setCus_username(String cus_username) {
        this.cus_username = cus_username;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public double getLoan_balance() {
        return loan_balance;
    }

    public void setLoan_balance(double loan_balance) {
        this.loan_balance = loan_balance;
    }

    public double getInstallment_1() {
        return installment_1;
    }

    public void setInstallment_1(double installment_1) {
        this.installment_1 = installment_1;
    }

    public double getInstallment_2() {
        return installment_2;
    }

    public void setInstallment_2(double installment_2) {
        this.installment_2 = installment_2;
    }

    public double getInstallment_3() {
        return installment_3;
    }

    public void setInstallment_3(double installment_3) {
        this.installment_3 = installment_3;
    }
}
