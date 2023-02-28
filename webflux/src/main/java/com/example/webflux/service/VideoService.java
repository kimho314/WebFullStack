package com.example.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class VideoService {
    private final ResourceLoader resourceLoader;
    private final String format = "classpath:videos/%s.mp4";

    public VideoService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Mono<Resource> getVideo(String videoFile) {
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(format, videoFile)));
    }
}
