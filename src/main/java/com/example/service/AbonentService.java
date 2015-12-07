package com.example.service;

import com.example.business.Abonent;

/**
 * Created by zealot on 05.12.2015.
 */

public interface AbonentService {
    public int saveAbonent(Abonent abonent);

    public double getBalance(String msisdn, String password);
}
