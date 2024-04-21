package com.example.lunit.common.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    public static final String AUTHORIZATION_HEADER = "X-API-TOKEN";
    public static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret
    ) {
        this.secret = secret;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String resolveToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest request = requestAttributes.getRequest();
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(header)) {
            header = request.getHeader(AUTHORIZATION_HEADER.toLowerCase());
        }

        if (StringUtils.hasText(header)) {
            return header;
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(header)) {
            header = request.getHeader(AUTHORIZATION_HEADER.toLowerCase());
        }

        if (StringUtils.hasText(header)) {
            return header;
        }
        return null;
    }

    public String createToken(String userName, String role, long expireDurationInSeconds) {

        LocalDateTime expireAt = LocalDateTime.now().plusSeconds(expireDurationInSeconds);
        Date expiration = Timestamp.valueOf(expireAt);

        return Jwts.builder()
                .setSubject(userName)
                .claim(AUTHORITIES_KEY, role)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiration)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Set<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (SecurityException | MalformedJwtException e) {
            log.error("malformed jwt : {}", e.getMessage());
        }
        catch (ExpiredJwtException e) {
            log.error("expired jwt : {}", e.getMessage());
        }
        catch (UnsupportedJwtException e) {
            log.error("unsupported jwt : {}", e.getMessage());
        }
        catch (IllegalArgumentException e) {
            log.error("invalid jwt : {}", e.getMessage());
        }

        return false;
    }
}
