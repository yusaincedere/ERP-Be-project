package com.iknow.stocktrackingbe.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {
    int status;
    String message;
    Long timeStamp;
    String path;

    Map<String,String> validationExceptions;

    public ApiException(int status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.path = path;
        this.timeStamp = new Date().getTime();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getValidationExceptions() {
        return validationExceptions;
    }

    public void setValidationExceptions(Map<String, String> validationExceptions) {
        this.validationExceptions = validationExceptions;
    }
}
