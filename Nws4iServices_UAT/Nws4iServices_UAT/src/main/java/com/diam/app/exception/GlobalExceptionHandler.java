package com.diam.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diam.app.esb.IConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        String errorMessage = String.format("Required request parameter '%s' is missing", paramName);
		String failresponse = String.format(IConstants.ESB_FAILURE_RESPONSE_XML_V2, paramName, errorMessage);
        return new ResponseEntity<>(failresponse, HttpStatus.BAD_REQUEST);
    }
}

