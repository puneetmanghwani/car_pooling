package com.demo.car_pooling.service.datadog;





import com.timgroup.statsd.StatsDClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;



@Service

@Slf4j

public class DatadogService {



    @Autowired

    StatsDClient dataDogClient;



    @Value("${instance.id:1}")

    Integer instanceId;





    @Value("${isLocal:false}")

    private Boolean localInstance;







    private long prevNotificationsRead =0;



    /*@Scheduled(fixedDelay = 5000)

    public void functionName() {

        if(!localInstance) {

            dataDogClient.time("your_parameter_name", your_parameter_variable, "instanceId:" + instanceId);

        }

    }*/





}



