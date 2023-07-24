package com.example.authserver.filter;

import com.example.authserver.dto.UserDetailsDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 지정된 URL로 form 전송을 하였을 경우 파라미터 정보를 가져온다.
     *
     * @param request  from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     *                 redirect as part of a multi-stage authentication process (such as OpenID).
     * @return Authentication {}
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;
        try {
            authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        catch (Exception e) {
            throw new RuntimeException();
        }

    }


    /**
     * Request로 받은 ID와 패스워드 기반으로 토큰을 발급한다.
     *
     * @param request HttpServletRequest
     * @return UsernamePasswordAuthenticationToken
     * @throws Exception e
     */
    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            UserDetailsDto user = objectMapper.readValue(request.getInputStream(), UserDetailsDto.class);

            // ID와 패스워드를 기반으로 토큰 발급
            return new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
        }
        catch (UsernameNotFoundException ae) {
            throw new UsernameNotFoundException(ae.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
