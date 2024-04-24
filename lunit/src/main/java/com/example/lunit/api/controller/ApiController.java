package com.example.lunit.api.controller;

import com.example.lunit.api.dto.AnalyzeDicomResponseDto;
import com.example.lunit.api.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<AnalyzeDicomResponseDto> analyzeDicom(
            @RequestParam("file") MultipartFile file,
            Principal principal
    ) {
        AnalyzeDicomResponseDto result = analysisService.analyze(file, principal.getName());
        return ResponseEntity.ok().body(result);
    }
}
