package com.luna.directchat.auth;

import com.luna.directchat.entity.MessageUserEntity;
import com.luna.directchat.repository.MessageUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MessageUserDetailsService implements UserDetailsService {


    private static final Logger log = LoggerFactory.getLogger(MessageUserDetailsService.class);
    private final MessageUserRepository messageUserRepository;

    public MessageUserDetailsService(MessageUserRepository messageUserRepository) {
        this.messageUserRepository = messageUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MessageUserEntity messageUserEntity = messageUserRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.info("User not found: {}", username);
                    return new UsernameNotFoundException("");
                });

        return new MessageUserDetails(
                messageUserEntity.getUserId(),
                messageUserEntity.getUsername(),
                messageUserEntity.getPassword()
        );
    }
}
