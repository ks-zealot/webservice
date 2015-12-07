package com.example.controller;

import com.example.business.Request;
import com.example.business.Response;
import com.example.business.Abonent;
import com.example.exceptions.InvalidRequestException;
import com.example.service.AbonentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * Created by zealot on 05.12.2015.
 */
@Controller
public class EndpointController {
    private Logger logger = LoggerFactory.getLogger(EndpointController.class);
    @Autowired
    AbonentService abonentService;
    @RequestMapping(value = "/test")
    public String test() {
        return "hello there";
    }
    @RequestMapping(value = "/testXml",
            method = RequestMethod.POST,
            produces =   MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody Response testXml( @RequestBody  Request request) {

        Response response = new Response();
        response.setResultCode(0);
        return response;
    }

    @RequestMapping(value = "/registerAbonent",
    method = RequestMethod.POST,
    produces =   MediaType.APPLICATION_XML_VALUE )
    public @ResponseBody Response register(@RequestBody @Validated Request request) {
        String resType = request.getRequestType();
        logger.info("get request with type {}", resType);
        if (!resType.equals("new-agt"))
        {
            throw new InvalidRequestException();
        }
        Abonent abonent = new Abonent();
        abonent.setBalance(0.0);
        abonent.setMsisdn(request.getLogin());
        abonent.setPassword(request.getPassword());
        Response response = new Response();
        logger.info("create new abonent {}", abonent);

        response.setResultCode(abonentService.saveAbonent(abonent));
        return response;
    }

    @RequestMapping(value = "/getBalance",
    method = RequestMethod.GET,
    produces =  MediaType.APPLICATION_XML_VALUE,
    consumes = MediaType.APPLICATION_XML_VALUE
    )
    public @ResponseBody Response getBalance(@RequestBody Request request) {
        String resType = request.getRequestType();
        logger.info("get request with type {}", resType);
        if (!resType.equals("agt-bal"))
        {
            throw new InvalidRequestException();
        }
        Response response = new Response();
        logger.info("about to get balance");
        response.setBal(abonentService.getBalance(request.getLogin(), request.getPassword()));
        logger.debug("return response {}", response);
        return response;
    }

}
