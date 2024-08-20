package com.example.authserver.api.config;

import com.example.authserver.api.auth.JwtAuthenticationFilter;
import com.example.authserver.api.auth.JwtAuthenticationProvider;
import com.example.authserver.domain.member.enums.Role;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(JwtAuthenticationProvider jwtAuthenticationProvider) {
        return new ProviderManager(jwtAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // invoke csrfFilter to protect against CSRF attacks
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signup", "/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/member/**").hasRole(Role.ROLE_USER.toSecurityRole())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                    response.getWriter().write(accessDeniedException.getMessage());
                                    response.flushBuffer();
                                }
                        )
                        .authenticationEntryPoint(
                                (request, response, authException) -> {
                                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                    response.getWriter().write(authException.getMessage());
                                    response.flushBuffer();
                                }
                        )
                )
                .addFilterAfter(new JwtAuthenticationFilter(authenticationManager), LogoutFilter.class)
        ;

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
