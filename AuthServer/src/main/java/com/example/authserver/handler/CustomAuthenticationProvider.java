package com.example.authserver.handler;

import com.example.authserver.dto.UserDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService
    ) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // 'AuthenticaionFilter' 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userId = token.getName();
        String userPw = (String) token.getCredentials();

        // Spring Security - UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername(userId);

        if (!(userDetailsDto.getPassword().equalsIgnoreCase(userPw) && userDetailsDto.getUserId().equalsIgnoreCase(userId))) {
            throw new BadCredentialsException(userDetailsDto.getPassword() + "Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
