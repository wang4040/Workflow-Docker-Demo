package com.beaconfire.springsecurityauth.service;


import com.beaconfire.springsecurityauth.dao.DemoDAO;
import com.beaconfire.springsecurityauth.domain.Demo;
import com.beaconfire.springsecurityauth.exception.DemoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private DemoDAO demoDAO;

    @Autowired
    public void setDemoDAO(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }

    public Demo getADemo(){
        return demoDAO.getADemo();
    }

    public Demo getAErrorDemo() throws DemoNotFoundException {
        return demoDAO.getAErrorDemo();
    }
}
