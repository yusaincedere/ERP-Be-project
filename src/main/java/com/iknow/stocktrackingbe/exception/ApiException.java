package com.iknow.stocktrackingbe.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
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


}
