package com.beaconfire.springsecurityauth.controller;


import com.beaconfire.springsecurityauth.domain.response.DemoResponse;
import com.beaconfire.springsecurityauth.exception.DemoNotFoundException;
import com.beaconfire.springsecurityauth.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private DemoService demoService;

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }


    @GetMapping("/demo")
    public ResponseEntity<DemoResponse> demoEndPoint(){

        DemoResponse response = DemoResponse.builder().demo(demoService.getADemo()).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/demoException")
    public ResponseEntity<DemoResponse> errorDemoEndPoint() throws DemoNotFoundException {
        DemoResponse response = DemoResponse.builder().demo(demoService.getAErrorDemo()).build();
        return ResponseEntity.ok(response);
    }
}
