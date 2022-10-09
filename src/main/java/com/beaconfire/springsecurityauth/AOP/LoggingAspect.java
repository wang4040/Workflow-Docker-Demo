package com.beaconfire.springsecurityauth.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//    @Before("com.beaconfire.springaop.AOPDemo.AOP.PointCuts.inControllerLayer()")
//    public void logStartTime(){
//        logger.info("From LoggingAspect.logStartTime in controller: " + System.currentTimeMillis()); // advice
//    }
//
//    @After("com.beaconfire.springaop.AOPDemo.AOP.PointCuts.inService()")
//    public void logEndTime(JoinPoint joinPoint){
//        logger.info("From LoggingAspect.logEndTime in service: " + System.currentTimeMillis()  + ": " + joinPoint.getSignature());
//    }
//
//    @AfterReturning(value = "com.beaconfire.springaop.AOPDemo.AOP.PointCuts.inDAOLayer()", returning = "res")
//    public void logReturnObject(JoinPoint joinPoint, Object res){
//        logger.info("From LoggingAspect.logReturnObject in DAO: " + res + ": " + joinPoint.getSignature());
//    }
//
//    @AfterThrowing(value = "com.beaconfire.springaop.AOPDemo.AOP.PointCuts.inControllerLayer()", throwing = "ex")
//    public void logThrownException(JoinPoint joinPoint, Throwable ex){
//        logger.error("From LoggingAspect.logThrownException in controller: " + ex.getMessage() + ": " + joinPoint.getSignature());
//    }

    @Around("com.beaconfire.springsecurityauth.AOP.PointCuts.inControllerLayer()")
    public Object logStartAndEndTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        // before
        logger.info("From LoggingAspect.logStartAndEndTime: " + proceedingJoinPoint.getSignature());
        logger.info("Start time: " + System.currentTimeMillis());

        //Invoke the actual object
        Object o = proceedingJoinPoint.proceed();

        // after
        logger.info("End time: " + System.currentTimeMillis());
        return o;
    }
}
