package com.example.springbootexample;

import com.example.springbootexample.boot.MySpringApplication;
import com.example.springbootexample.boot.MySpringBootApplication;

@MySpringBootApplication
public class MySpringBootMain {

    public static void main(String[] args) {
        System.out.println("Hello Spring Boot Application");
        MySpringApplication.run(MySpringBootMain.class, args);
    }
}
