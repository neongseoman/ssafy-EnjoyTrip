package com.ssafy.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class JsonResponseEntityWrapper extends ResponseEntity {

    public JsonResponseEntityWrapper(HttpStatus status) {
        super(status);
    }

    public JsonResponseEntityWrapper(Object body, HttpStatus status) {
        super(body, status);
    }

    public JsonResponseEntityWrapper(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public JsonResponseEntityWrapper(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }

    public JsonResponseEntityWrapper(Object body, MultiValueMap headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
