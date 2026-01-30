package com.luna.securityloginlogout.auth;

import com.luna.securityloginlogout.session.HttpSessionRepository;
import jakarta.servlet.http.HttpSession;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class CustomHttpSessionHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(CustomHttpSessionHandshakeInterceptor.class);
    private final HttpSessionRepository sessionRepository;

    public CustomHttpSessionHandshakeInterceptor(HttpSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) throws Exception {
        List<String> cookies = request.getHeaders().get("Cookie");
        if(cookies != null){
            for(String cookie : cookies){
                if(cookie.contains("JSESSIONID")){
                    String sessionId = cookie.split("=")[1];
                    HttpSession httpSession = sessionRepository.findById(sessionId);
                    if(httpSession != null){
                        log.info("Connected sessionId : {}", sessionId);
                        return true;
                    }
                }
            }
        }

        log.info("Unauthorized access attempt : CLientIP={}", request.getRemoteAddress());
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }
}
