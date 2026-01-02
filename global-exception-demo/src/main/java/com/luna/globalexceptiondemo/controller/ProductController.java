package com.luna.globalexceptiondemo.controller;

import com.luna.globalexceptiondemo.dto.GetProductRespDto;
import com.luna.globalexceptiondemo.exception.ResourceNotFoundException;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    @GetMapping("/{id}")
    public ResponseEntity<GetProductRespDto> getProductById(@PathVariable @Min(1) Long id) {
        if (id != 1) {
            throw new ResourceNotFoundException(id);
        }
        return ResponseEntity.ok(new GetProductRespDto(id));
    }
}
