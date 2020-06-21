package com.emor.dbfinal.service.impl;

import com.emor.dbfinal.service.HelloService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class HelloServiceImpl implements HelloService {

    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

//    @Scheduled(cron = "0-5,30 * * * * *")
    public void hello2(){
        System.out.println("hello" + new Date());
    }
}
