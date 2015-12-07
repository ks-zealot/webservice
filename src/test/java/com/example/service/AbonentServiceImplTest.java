package com.example.service;

import com.example.WebserviceApplication;
import com.example.business.Abonent;
import com.example.exceptions.NoSuchAbonentException;
import com.example.exceptions.WrongPasswordException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebserviceApplication.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AbonentServiceImplTest {
@Autowired
    AbonentService service;
    @Test
    public void test() {
        Abonent abonent = new Abonent();
        abonent.setMsisdn("7111111111");
        abonent.setPassword("23423423");
        abonent.setBalance(0.0);
        assertEquals(0, service.saveAbonent(abonent));
        assertEquals(1, service.saveAbonent(abonent));
    }

    @Test
    public void testGetBalance() {
        Abonent abonent = new Abonent();
        abonent.setMsisdn("7111111112");
        abonent.setPassword("23423423");
        abonent.setBalance(0.0);
         service.saveAbonent(abonent);
      assertEquals(0.0,  service.getBalance(abonent.getMsisdn(), "23423423"), 0.0001);
    }

    @Test(expected = WrongPasswordException.class)
    public void testGetWrongPassword() {
        Abonent abonent = new Abonent();
        abonent.setMsisdn("7111111112");
        abonent.setPassword("23423423");
        abonent.setBalance(0.0);
        service.saveAbonent(abonent);
        assertEquals(0.0,  service.getBalance(abonent.getMsisdn(), "sdfdsfds"), 0.0001);
    }
    @Test(expected = NoSuchAbonentException.class)
    public void testGetNoSuchAbonent() {
        assertEquals(0.0,  service.getBalance("7111111113", "sdfdsfds"), 0.0001);
    }
}