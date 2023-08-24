package com.naveen.aopexampleall.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDate;

@Log4j2
@Aspect
@Component
public class EmployeeAspect {

    // @Around("execution(* com.naveen.aopexampleall.service..*(..)))")
    @Around("@annotation(LogExecutionTime)")
    public void profileAllMethods (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Signature signature = proceedingJoinPoint.getSignature();

        final String toLongString = proceedingJoinPoint.toLongString();
        final String toString = proceedingJoinPoint.toString();
        final String kind = proceedingJoinPoint.getKind();

        //Measure method execution time
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution dateAndTime of " + signature +
                " date: " + LocalDate.now() + "  time: " + stopWatch.getTotalTimeMillis() + " ms");
    }

    @Before("execution(* com.naveen.aopexampleall.service..beforeAdvice()))")
    public void before ( ) {
        log.info("before()");
    }

    @After("execution(* com.naveen.aopexampleall.service..beforeAdvice()))")
    public void after ( ) {
        log.info("after()");
    }

    @AfterThrowing("execution(* com.naveen.aopexampleall.service..getEpmThrowException())")
    public void logExceptions (JoinPoint joinPoint) {
        log.info("Exception thrown in Employee Method=" + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.naveen.aopexampleall.service..getEpmName())", returning = "returnString")
    public void getNameReturningAdvice (String returnString) {
        log.error("getNameReturningAdvice executed. Returned String = " + returnString);
    }

}
