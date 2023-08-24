package com.naveen.aopexampleall.service;

import com.naveen.aopexampleall.aspect.LogExecutionTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmployeeService {

  @LogExecutionTime
  public void empAround() {
    log.info("EmpDetails");
  }
  public void beforeAdvice(){
    log.info("beforeAdvice");
  }

  public String getEpmName(){
    return "naveen";
  }
  public void getEpmThrowException(){
    throw new RuntimeException("Exception..");
  }


}
