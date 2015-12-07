package com.example.controller;

import com.example.WebserviceApplication;
import com.example.business.Request;
import com.example.business.Response;
import com.example.exceptions.InvalidRequestException;
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
public class EndpointControllerTest {
    @Autowired
    EndpointController endpointController;

    @Test
    public void testController() {
        Request request = new Request();
        request.setRequestType("new-agt");
        request.setLogin("79161111111");
        request.setPassword("qwerty");
        Response response = endpointController.register(request);
        assertEquals(response.getResultCode(), 0);
        request.setRequestType("agt-bal");
        response = endpointController.getBalance(request);
        assertEquals(response.getBal(), 0.0, 0.0001);
    }

    @Test(expected = InvalidRequestException.class)
    public void testController1() {
        Request request = new Request();
        request.setRequestType("illegalrequest");
        request.setLogin("79161111111");
        request.setPassword("qwerty");
        endpointController.register(request);

    }

}