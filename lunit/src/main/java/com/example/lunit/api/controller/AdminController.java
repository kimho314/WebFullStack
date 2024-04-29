package com.example.lunit.api.controller;

import com.example.lunit.api.dto.GetAnalyzeResultResponseDto;
import com.example.lunit.api.dto.GetMembersResponseDto;
import com.example.lunit.api.service.AdminService;
import com.example.lunit.common.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * @return
     * @title 츨라이언트 리스트 조회
     */
    @GetMapping("/members")
    public ResponseEntity<CommonResponseDto<List<GetMembersResponseDto>>> getMembers() {
        List<GetMembersResponseDto> members = adminService.getMembers();
        return ResponseEntity.ok(new CommonResponseDto<>(members));
    }

    /**
     * @param userName
     * @return
     * @title 분석 결과 리스트 조회
     */
    @GetMapping("/member/{userName}/analyze-results")
    public ResponseEntity<CommonResponseDto<List<GetAnalyzeResultResponseDto>>> getAnalyzeResults(@PathVariable("userName") String userName) {
        List<GetAnalyzeResultResponseDto> result = adminService.getAnalyzeResult(userName);
        return ResponseEntity.ok(new CommonResponseDto<>(result));
    }
}
