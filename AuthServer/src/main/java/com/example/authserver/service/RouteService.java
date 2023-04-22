package com.example.authserver.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class RouteService {

    @Async
    public CompletableFuture<Double> getRandomShortestDistance() {
        double distance = Math.random() * 1000;
        log.info("distance : {}", distance);
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            log.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }
        return CompletableFuture.completedFuture(distance);
    }
}
