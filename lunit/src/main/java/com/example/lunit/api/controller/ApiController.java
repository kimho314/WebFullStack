package com.example.lunit.api.controller;

import com.example.lunit.api.dto.AnalyzeDicomResponseDto;
import com.example.lunit.api.dto.ExtendMaxAnalysisCountRequestDto;
import com.example.lunit.api.dto.ExtendMaxAnalysisCountResponseDto;
import com.example.lunit.api.service.AnalysisService;
import com.example.lunit.common.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ApiController {
    private final AnalysisService analysisService;

    /**
     * @param file
     * @param principal
     * @return
     * @title 영상 분석 실행
     */
    @PostMapping("/dicom-analysis")
    public ResponseEntity<CommonResponseDto<AnalyzeDicomResponseDto>> analyzeDicom(
            @RequestParam("frontal") MultipartFile file,
            Principal principal
    ) {
        AnalyzeDicomResponseDto result = analysisService.startAnalysis(file, principal.getName());
        return ResponseEntity.ok(new CommonResponseDto<>(result));
    }

    /**
     * @param request
     * @param principal
     * @return
     * @title 최대 분석수 연장
     */
    @PutMapping("/extend-max-analysis-count")
    public ResponseEntity<CommonResponseDto<ExtendMaxAnalysisCountResponseDto>> extendMaxAnalysisCount(
            @Valid @RequestBody ExtendMaxAnalysisCountRequestDto request,
            Principal principal
    ) {
        ExtendMaxAnalysisCountResponseDto result = analysisService.extendMaxCount(request, principal.getName());
        return ResponseEntity.ok(new CommonResponseDto<>(result));
    }
}
