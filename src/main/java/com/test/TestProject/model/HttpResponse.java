package com.test.TestProject.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class HttpResponse<T> {
    private Date date;
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    private T data;
}
