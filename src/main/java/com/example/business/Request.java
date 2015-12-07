package com.example.business;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zealot on 05.12.2015.
 */
@XmlRootElement( name = "request")
public class Request {

    private String requestType;
    @Length(min = 7, max = 15, message = "минимальная длина номера 7 символов, максимальная пятнадцать")
    private String login;
    @Length(min = 6, message = "минимальная длина пароля должны быть 6 символов")
    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z]).+", message = "пароль должен включать цифры и буквы")
    private String password;
    @XmlElement(name = "request-type")
    @JacksonXmlProperty(localName =  "request-type")
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
