package com.luna.apiversioningdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping(path = "/{version}/accounts/{id}", version = "1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountsV1(@PathVariable(value = "id") Long id) {
        return "account: " + id;
    }
}
