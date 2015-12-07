package com.example.business;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zealot on 05.12.2015.
 */

public class Abonent {
    private String msisdn;
    private String password;
    private double balance;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abonent)) return false;

        Abonent abonent = (Abonent) o;

        if (Double.compare(abonent.balance, balance) != 0) return false;
        if (!msisdn.equals(abonent.msisdn)) return false;
        if (!password.equals(abonent.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = msisdn.hashCode();
        result = 31 * result + password.hashCode();
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Abonent{");
        sb.append("msisdn='").append(msisdn).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
