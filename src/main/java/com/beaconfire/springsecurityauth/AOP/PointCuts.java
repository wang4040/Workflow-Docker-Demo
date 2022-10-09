package com.beaconfire.springsecurityauth.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("within(com.beaconfire.springsecurityauth.controller.*)")
    public void inControllerLayer(){}

    @Pointcut("bean(*Service)")
    public void inService(){}

    @Pointcut("execution(* com.beaconfire.springsecurityauth.dao.DemoDAO.*Demo())")
    public void inDAOLayer(){}

}
