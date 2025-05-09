package com.example.hellospringmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class MappingClassController {

    @GetMapping("/")
    public String getUsers() {
        log.info("getUsers");
        return "get users";
    }

    @PostMapping("/")
    public String saveUser() {
        log.info("saveUser");
        return "post user";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId) {
        log.info("getUser");
        return "get userId= " + userId;
    }

    @PatchMapping("/{userId}")
    public String patchUser(@PathVariable("userId") String userId) {
        log.info("patchUser");
        return "update userId= " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        log.info("deleteUser");
        return "delete userId= " + userId;
    }
}
