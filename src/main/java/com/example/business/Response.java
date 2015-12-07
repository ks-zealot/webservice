package com.example.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zealot on 05.12.2015.
 */

@XmlRootElement( name = "response")
@JacksonXmlRootElement(localName = "response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int resultCode;
    private Double bal;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @XmlElement(name = "result-code")
    @JacksonXmlProperty(localName =  "result-code")
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Double getBal() {
        return bal;
    }

    public void setBal(Double bal) {
        this.bal = bal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response{");
        sb.append("resultCode=").append(resultCode);
        sb.append(", bal=").append(bal);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
