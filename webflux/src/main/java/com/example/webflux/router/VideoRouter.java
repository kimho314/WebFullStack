package com.example.webflux.router;

import com.example.webflux.handler.VideoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class VideoRouter {
    private final VideoHandler videoHandler;

    public VideoRouter(VideoHandler videoHandler) {
        this.videoHandler = videoHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> videoRoutes() {
        return RouterFunctions.route(RequestPredicates.GET("/video"),
                videoHandler::watchVideo);
    }
}
