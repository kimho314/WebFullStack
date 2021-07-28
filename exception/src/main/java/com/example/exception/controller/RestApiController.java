package com.example.exception.controller;

import com.example.exception.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@Slf4j
@RequestMapping("/api/user")
@Validated
public class RestApiController {

    @GetMapping("")
    public User get(@Size(min = 2) @RequestParam(required = false) String name,
                    @NotNull @Min(1) @RequestParam(required = false) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;

        return user;
    }

    @PostMapping("")
    public User post(@RequestBody @Valid User user){
        System.out.println("post user");
        return user;
    }

}
