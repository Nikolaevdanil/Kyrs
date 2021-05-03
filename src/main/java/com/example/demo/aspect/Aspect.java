package com.example.demo.aspect;

import com.example.demo.DemoApplication;
import com.example.demo.service.ManufactureService;
import com.example.demo.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    private final Logger log = LoggerFactory.getLogger(Aspect.class);

    @Before("allServiceMethods()")
    public void logParameters(JoinPoint joinPoint)
    {
        log.info("Parameters: {}", joinPoint.getArgs());
    }
    @Pointcut("within(com.example.demo.service.*)")
    public void allServiceMethods(){}
}
