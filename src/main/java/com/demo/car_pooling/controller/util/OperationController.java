package com.demo.car_pooling.controller.util;





import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.LoggerContext;

import org.apache.logging.log4j.core.config.Configuration;

import org.apache.logging.log4j.core.config.Configurator;

import org.apache.logging.log4j.core.config.LoggerConfig;

import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;

import org.springframework.context.ApplicationContextAware;

import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;







import javax.servlet.http.HttpServletRequest;

import java.time.Instant;



@Slf4j

@RestController

public class OperationController implements ApplicationContextAware {



    private ApplicationContext context;



    





    @PostMapping("/shutdownContext")

    public ResponseEntity<String> shutdown(HttpServletRequest request) throws InterruptedException {

        log.info("Request:{}, remoreAddr:{},header:{}",request,request.getRemoteAddr(),request.getHeader("X-FORWARDED-FOR"));



        String  forwarded=request.getHeader("X-FORWARDED-FOR");

        String remoteAddr=request.getRemoteAddr();

        if(!(forwarded==null && "127.0.0.1".equals(remoteAddr)))

        {

            log.info("Unauthorized access: Request:{}, remoreAddr:{},header:{}",request,remoteAddr,request.getHeader("X-FORWARDED-FOR"));

            return new ResponseEntity<>("Request should be from localhost",HttpStatus.UNAUTHORIZED);

        }

       



        new Thread(() -> ((ConfigurableApplicationContext) context).close()).start();

        return new ResponseEntity<>("Shutdown initiated",HttpStatus.OK);

    }



    @Override

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {

        this.context = ctx;



    }



    @PostMapping("/changeLogLevelForClass")

    public ResponseEntity<String> change(HttpServletRequest request,  @RequestParam("className") String fqcn,  @RequestParam("level")String level) {



        Configurator.setLevel(fqcn, Level.valueOf(level));

        return new ResponseEntity<>("LogLevel updated",HttpStatus.OK);

    }



    @PostMapping("/changeRootLogger")

    public ResponseEntity<String> change(HttpServletRequest request,String level){

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);

        Configuration config = ctx.getConfiguration();

        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

        loggerConfig.setLevel(Level.valueOf(level));

        ctx.updateLoggers();

        return new ResponseEntity<>("LogLevel updated",HttpStatus.OK);

    }









}

