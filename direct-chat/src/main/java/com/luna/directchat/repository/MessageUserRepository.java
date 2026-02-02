package com.luna.directchat.repository;

import com.luna.directchat.entity.MessageUserEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageUserRepository extends JpaRepository<MessageUserEntity, Long> {
    Optional<MessageUserEntity> findByUsername(@NonNull String username);
}
