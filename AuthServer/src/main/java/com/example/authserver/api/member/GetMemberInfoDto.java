package com.example.authserver.api.member;

import com.example.authserver.domain.member.enums.Role;
import lombok.Builder;

public class GetMemberInfoDto {

    @Builder
    public record Response(
            String userId,
            String name,
            String phoneNumber,
            String email,
            Role role
    ) {

    }
}
