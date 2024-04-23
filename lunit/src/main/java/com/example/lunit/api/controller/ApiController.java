package com.example.lunit.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ApiController {

    @PostMapping("/dicom-analysis")
    public ResponseEntity<Void> analyzeDicom() {
        return ResponseEntity.ok().build();
    }
}
