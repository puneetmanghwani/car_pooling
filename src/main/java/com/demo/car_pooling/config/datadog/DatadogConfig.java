package com.demo.car_pooling.config.datadog;



import com.timgroup.statsd.NonBlockingStatsDClientBuilder;

import com.timgroup.statsd.StatsDClient;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



@Configuration

public class DatadogConfig {





    @Value("${metricPort:8125}")

    private Integer metricPort;



    @Value("${spring.profiles.active}")

    String profile;





    @Bean(name = "dataDogClient")

    public StatsDClient getStatsd() {

        return new NonBlockingStatsDClientBuilder()

                .prefix(profile+".car-pooling")

                .hostname("localhost")

                .port(metricPort)

                .build();

    }



}



