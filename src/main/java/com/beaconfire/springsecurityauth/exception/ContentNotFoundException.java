package com.beaconfire.springsecurityauth.exception;

public class ContentNotFoundException extends Exception{
    public ContentNotFoundException() {
        super("Content not found");
    }
}