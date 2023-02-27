package com.example.webflux.handler;

import com.example.webflux.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class VideoHandler {
    private final VideoService videoService;

    public VideoHandler(VideoService videoService) {
        this.videoService = videoService;
    }

    public Mono<ServerResponse> watchVideo(ServerRequest request) {
        String range = request.headers().firstHeader("Range");
        log.info("# range : {}", range);
        return ServerResponse.ok()
                .body(videoService.getVideo(), Resource.class);
    }
}
