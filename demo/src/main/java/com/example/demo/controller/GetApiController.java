package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")    // http://localhost:9090/api/get/hello
    public String hello(){
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)   // get http://localhost:9090/api/hi
    public String hi(){
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String name){
        System.out.println("PathVariable : " + name);
        return name;
    }

    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/query-param2")
    public String queryParam2(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam int age){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + "&" + email + "&" + age;
    }

    @GetMapping("/query-param3")
    public String queryParam3(UserRequestDTO userRequestDTO){
        System.out.println(userRequestDTO.toString());
        return userRequestDTO.toString();
    }
}
