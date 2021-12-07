package com.example.validation.controller;

import com.example.validation.dto.Terms;
import com.example.validation.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public Object user(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println(message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb);
        }

        System.out.println(user);

        return user;
    }

    @PostMapping("/terms")
    public Terms.Response getTerms(@RequestBody @Valid Terms.Request request){
        log.info("terms = {}", request.getTerms());
        return Terms.Response.builder()
                .responseMessage("SUCCESS")
                .build();
    }
}
