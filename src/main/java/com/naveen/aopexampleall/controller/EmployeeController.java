package com.naveen.aopexampleall.controller;

import com.naveen.aopexampleall.aspect.LogExecutionTime;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
public class EmployeeController {

    @SneakyThrows
    @LogExecutionTime
    public void empAround ( ) {
        Thread.sleep(5000);
        log.info("EmployeeController");

    }

}
