package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zealot on 06.12.2015.
 */
@Entity
@Table(name = "abonent")
public class AbonentEntity {
    @Id
    @Column(name = "msisdn")
    private String msisdn;// некоторые номера могут начинаться с нуля. так что на всякий случай возьмем
    // стринг, хотя это и не производительно с точки зрения использоваиня места
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
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
        if (!(o instanceof AbonentEntity)) return false;

        AbonentEntity abonent = (AbonentEntity) o;

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
}
