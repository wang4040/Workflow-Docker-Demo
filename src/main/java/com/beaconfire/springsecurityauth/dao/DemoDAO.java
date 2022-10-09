package com.beaconfire.springsecurityauth.dao;

import com.beaconfire.springsecurityauth.domain.Demo;
import com.beaconfire.springsecurityauth.exception.DemoNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDAO {
    public Demo getADemo(){
        return Demo.builder()
                .id(1)
                .type("Java")
                .description("AOP")
                .build();
    }

    public Demo getAErrorDemo() throws DemoNotFoundException {
        throw new DemoNotFoundException("Demo not found.");
    }
}
