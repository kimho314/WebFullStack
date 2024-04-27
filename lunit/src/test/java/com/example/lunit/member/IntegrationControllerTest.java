package com.example.lunit.member;

import com.example.lunit.api.controller.MemberController;
import com.example.lunit.api.dto.*;
import com.example.lunit.api.mapper.MemberMapper;
import com.example.lunit.api.service.FileStorageService;
import com.example.lunit.common.component.TokenProvider;
import com.example.lunit.common.dto.CommonResponseDto;
import com.example.lunit.common.enums.Role;
import com.example.lunit.common.enums.TokenType;
import com.example.lunit.common.util.ObjectMapperFactory;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.repository.DicomAnalyzeResultRepository;
import com.example.lunit.domain.repository.MemberRepository;
import com.example.lunit.domain.repository.TokenRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.getInstance();

    @Autowired
    MemberController memberController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    DicomAnalyzeResultRepository dicomAnalyzeResultRepository;


    @MockBean
    private FileStorageService storageService;

    private static String accessToken;
    private static String refreshToken;
    private static final String TEST_USER_NAME = "kimho314test";

    @Order(1)
    @Test
    @DisplayName("회원 가입 테스트")
    void signupTest() throws Exception {
        SignupRequestDto request = new SignupRequestDto(TEST_USER_NAME, "lunit123@", "test@lunit.com", Role.CLIENT);
        String jsonBody = OBJECT_MAPPER.writeValueAsString(request);

        ResultActions perform = mockMvc.perform(
                post("/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();


        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<TokenResponseDto> tokenResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });

        Assertions.assertNotNull(tokenResponseDto.getResult().accessToken());

        accessToken = tokenResponseDto.getResult().accessToken();
    }

    @Order(2)
    @Test
    @DisplayName("로그인 테스트")
    void loginTest() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto(TEST_USER_NAME, "lunit123@");
        String jsonBody = OBJECT_MAPPER.writeValueAsString(loginRequestDto);

        ResultActions perform = mockMvc.perform(
                post("/login")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<TokenResponseDto> tokenResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });

        Assertions.assertNotNull(tokenResponseDto.getResult().accessToken());
        Assertions.assertNotNull(tokenResponseDto.getResult().refreshToken());

        accessToken = tokenResponseDto.getResult().accessToken();
        refreshToken = tokenResponseDto.getResult().refreshToken();
    }

    @Order(3)
    @Test
    @DisplayName("회원 정보 조회 테스트")
    void getMemberInfoTest() throws Exception {
        ResultActions perform = mockMvc.perform(
                get("/member-info")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<MemberInfoResponseDto> memberInfoResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });

        Assertions.assertEquals(TEST_USER_NAME, memberInfoResponseDto.getResult().userName());
        Assertions.assertEquals(MemberMapper.DEFAULT_MAX_ANALYZE_CNT, memberInfoResponseDto.getResult().maxAnalyzeCnt());
        Assertions.assertEquals(MemberMapper.DEFAULT_CUR_ANALYZE_CNT, memberInfoResponseDto.getResult().curAnalyzeCnt());
    }

    @Order(4)
    @Test
    @DisplayName("엑세스 토큰 재발급 테스트")
    void reissueAccessTokenTest() throws Exception {
        ReissueTokenRequestDto reissueTokenRequestDto = new ReissueTokenRequestDto(TEST_USER_NAME, accessToken);
        String jsonBody = OBJECT_MAPPER.writeValueAsString(reissueTokenRequestDto);

        ResultActions perform = mockMvc.perform(
                put("/reissue-token")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<ReissueTokenResponseDto> reissueTokenResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });
        Assertions.assertNotNull(reissueTokenResponseDto.getResult().token());
        Assertions.assertEquals(TokenType.ACCESS, reissueTokenResponseDto.getResult().tokenType());
    }


    @Order(5)
    @Test
    @DisplayName("리프레쉬 토큰 재발급 테스트")
    void reissueRefreshTokenTest() throws Exception {
        ReissueTokenRequestDto reissueTokenRequestDto = new ReissueTokenRequestDto(TEST_USER_NAME, refreshToken);
        String jsonBody = OBJECT_MAPPER.writeValueAsString(reissueTokenRequestDto);

        ResultActions perform = mockMvc.perform(
                put("/reissue-token")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<ReissueTokenResponseDto> reissueTokenResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });
        Assertions.assertNotNull(reissueTokenResponseDto.getResult().token());
        Assertions.assertEquals(TokenType.REFRESH, reissueTokenResponseDto.getResult().tokenType());
    }

    @Order(6)
    @Test
    @DisplayName("로그아웃 테스트")
    void logoutTest() throws Exception {
        ResultActions perform = mockMvc.perform(
                put("/logout")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();
    }

    @Order(7)
    @Test
    @DisplayName("로그아웃 후 엑세스 토큰 재발급 테스트")
    void reissueAccessTokenAfterLogoutTest() throws Exception {
        ReissueTokenRequestDto reissueTokenRequestDto = new ReissueTokenRequestDto(TEST_USER_NAME, accessToken);
        String jsonBody = OBJECT_MAPPER.writeValueAsString(reissueTokenRequestDto);

        ResultActions perform = mockMvc.perform(
                put("/reissue-token")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<ReissueTokenResponseDto> reissueTokenResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });
        Assertions.assertNotNull(reissueTokenResponseDto.getResult().token());
        Assertions.assertEquals(TokenType.ACCESS, reissueTokenResponseDto.getResult().tokenType());

        accessToken = reissueTokenRequestDto.token();
    }

    @Order(8)
    @Test
    @DisplayName("회원 정보 조회 테스트")
    void getMemberInfoTestAfterReissueAccessToken() throws Exception {
        ResultActions perform = mockMvc.perform(
                get("/member-info")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        CommonResponseDto<MemberInfoResponseDto> memberInfoResponseDto = OBJECT_MAPPER.readValue(contentAsString, new TypeReference<>() {
        });

        Assertions.assertEquals(TEST_USER_NAME, memberInfoResponseDto.getResult().userName());
        Assertions.assertEquals(MemberMapper.DEFAULT_MAX_ANALYZE_CNT, memberInfoResponseDto.getResult().maxAnalyzeCnt());
        Assertions.assertEquals(MemberMapper.DEFAULT_CUR_ANALYZE_CNT, memberInfoResponseDto.getResult().curAnalyzeCnt());
    }

    @Order(9)
    @Test
    @DisplayName("영상 분석 실행 테스트")
    void analyzeDicomTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("2_Pneumothorax.dcm");
        MockMultipartFile multipartFile = new MockMultipartFile("frontal", "2_Pneumothorax.dcm", "text/plain", resource.getInputStream());

        given(this.storageService.store(multipartFile))
                .willReturn(Paths.get("2_Pneumothorax.dcm"));

        ResultActions perform = mockMvc.perform(
                multipart("/api/dicom-analysis")
                        .file(multipartFile)
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();
        then(this.storageService).should().store(multipartFile);
    }

    @Order(10)
    @Test
    @DisplayName("최대 영상 분석수 연장 테스트")
    void extendMaxCountTest() throws Exception {
        ExtendMaxAnalysisCountRequestDto request = new ExtendMaxAnalysisCountRequestDto(100);
        String jsonBody = OBJECT_MAPPER.writeValueAsString(request);

        ResultActions perform = mockMvc.perform(
                put("/api/extend-max-analysis-count")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(jsonBody)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();
        CommonResponseDto<ExtendMaxAnalysisCountResponseDto> extendMaxAnalysisCountResponseDto = OBJECT_MAPPER.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals(110, extendMaxAnalysisCountResponseDto.getResult().maxCount());
    }

    @Order(11)
    @Test
    @DisplayName("회원 탈퇴 테스트")
    void signoutTest() throws Exception {
        ResultActions perform = mockMvc.perform(
                delete("/signout")
                        .header(TokenProvider.AUTHORIZATION_HEADER, accessToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
        );

        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andReturn();

        Member member = memberRepository.findByUserName(TEST_USER_NAME).orElseThrow();
        Assertions.assertEquals(false, member.getIsEnabled());

        tearDown();
    }

    private void tearDown() {
        dicomAnalyzeResultRepository.deleteAll();
        tokenRepository.deleteAll();
        memberRepository.deleteAll();
    }
}
