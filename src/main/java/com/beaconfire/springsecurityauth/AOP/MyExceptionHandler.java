package com.beaconfire.springsecurityauth.AOP;

import com.beaconfire.springsecurityauth.domain.response.ErrorResponse;
import com.beaconfire.springsecurityauth.exception.DemoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception e){
        return new ResponseEntity(ErrorResponse.builder().message("Generic custom exception: " + e.getMessage()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {DemoNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleDemoNotFoundException(DemoNotFoundException e){
        return new ResponseEntity(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity handleException(BadCredentialsException e) {
        return new ResponseEntity(ErrorResponse.builder().message("Caught custom invalid credentials in ExceptionHandler").build(), HttpStatus.OK);
    }

}
