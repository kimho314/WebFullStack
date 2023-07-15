package com.example.authserver;

import com.example.authserver.controller.MemberController;
import com.example.authserver.dto.CreateMemberRequestDto;
import com.example.authserver.entity.Member;
import com.example.authserver.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AuthServerApplicationTests {
    @Autowired
    MemberController memberController;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(memberController);
    }

    @Test
    @DisplayName("create member test")
    @Transactional
    void createMemberTest() throws Exception {
        //given
        CreateMemberRequestDto request = new CreateMemberRequestDto(
                "khs",
                "12345!",
                "hoseop.kim",
                "01022223333",
                "hoseop.kim@gmail.com"
        );

        //when
        mockMvc.perform(post("/member"))
                .andExpect(status().isOk());

        //then
        Member member = memberRepository.findByUserId("khs").orElseThrow();
        Assertions.assertEquals("khs", member.getUserId());
    }

}
