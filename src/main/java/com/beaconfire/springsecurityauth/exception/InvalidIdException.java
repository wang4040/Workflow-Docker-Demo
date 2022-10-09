package com.beaconfire.springsecurityauth.exception;

public class InvalidIdException extends Exception{
    public InvalidIdException(Integer id) {
        super("Id is taken: " + id);
    }
}
