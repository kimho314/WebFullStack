package com.example.responsebodyadvice.controller;

import com.example.responsebodyadvice.HomeDto;
import com.example.responsebodyadvice.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public String home() {
        return homeService.getHomeString(Boolean.FALSE);
    }

    @GetMapping("/home/response-entity")
    public ResponseEntity<HomeDto> homeWithResponseEntity() {
        var resp = HomeDto.builder()
                .message("home with response entity")
                .build();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/home/option")
    public Optional<HomeDto> homeWithOptional() {
        return Optional.ofNullable(
                HomeDto.builder()
                        .message("optional home")
                        .build()
        );
    }
}
