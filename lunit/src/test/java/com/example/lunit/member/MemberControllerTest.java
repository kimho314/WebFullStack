package com.example.lunit.member;

import com.example.lunit.api.controller.MemberController;
import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.common.enums.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class MemberControllerTest {
    @Autowired
    MemberController memberController;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("회원 가입 테스트")
    @Transactional
    void signupTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SignupRequestDto request = new SignupRequestDto("kimho314", "ghtjq2959@", "kimho314@gmail.com", Role.CLIENT);
        String jsonBody = objectMapper.writeValueAsString(request);

        ResultActions perform = mockMvc.perform(
                post("/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();
    }
}
