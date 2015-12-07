package com.example.service;

import com.example.dao.AbonentDao;
import com.example.business.Abonent;
import com.example.entity.AbonentEntity;
import com.example.exceptions.NoSuchAbonentException;
import com.example.exceptions.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by zealot on 05.12.2015.
 */
@Service
public class AbonentServiceImpl implements AbonentService {
    private Logger logger = LoggerFactory.getLogger(AbonentServiceImpl.class);
    @Autowired
    AbonentDao dao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public int saveAbonent(Abonent abonent) {
        AbonentEntity entity = new AbonentEntity();
        entity.setMsisdn(abonent.getMsisdn());
        if (dao.exists(abonent.getMsisdn())) {
            logger.debug("abonent {} already exist", abonent.getMsisdn());
            return 1;
        }
        entity.setPassword(bCryptPasswordEncoder.encode(abonent.getPassword()));
        logger.debug("save abonent {}" , abonent.getMsisdn());
        dao.save(entity);

        return  0;
    }
    @Cacheable("balance")
    public double getBalance(String msisdn, String password)  {
        logger.debug("get abonent with msisdn {}", msisdn);
        AbonentEntity entity = dao.findOne(msisdn);
        if (entity == null)
        {

            throw new NoSuchAbonentException("Abonent with msisdn " + msisdn +  " not registered yet");
        }
        String encodePassword = entity.getPassword();
        if (!bCryptPasswordEncoder.matches(password, encodePassword)) {
            throw new WrongPasswordException("Password " + password + " doesnot match abonent " + msisdn);
        }

        return entity.getBalance();
    }

}
