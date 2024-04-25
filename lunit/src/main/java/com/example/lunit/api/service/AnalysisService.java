package com.example.lunit.api.service;

import com.example.lunit.api.dto.*;
import com.example.lunit.common.enums.ResultStatus;
import com.example.lunit.common.exception.MemberNotFoundException;
import com.example.lunit.common.exception.ServiceException;
import com.example.lunit.domain.entity.DicomAnalyzeResult;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.repository.DicomAnalyzeResultRepository;
import com.example.lunit.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final FileStorageService fileStorageService;
    private final MemberRepository memberRepository;
    private final DicomAnalyzeResultRepository dicomAnalyzeResultRepository;

    @Transactional
    public AnalyzeDicomResponseDto analyze(MultipartFile file, String name) {
        Member member = memberRepository.findByUserName(name)
                .orElseThrow(MemberNotFoundException::new);
        if (!member.isAnalysisAvailable()) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE.value(), "analysis unavailable");
        }

        AnalysisResultDto result = analyze();

        member.setCurAnalyzeCnt(member.getCurAnalyzeCnt() + 1);

        Path path = fileStorageService.store(file);

        saveAnalysisResult(member, path, result);

        return new AnalyzeDicomResponseDto(result.predictionTime(), ResultStatus.SUCCESS, HttpStatus.OK.value(), new PredictionDto(new FrontalDto(result.score())));
    }

    private void saveAnalysisResult(Member member, Path path, AnalysisResultDto result) {
        DicomAnalyzeResult analyzeResult = DicomAnalyzeResult.builder()
                .member(member)
                .dicomFileName(path.getFileName().toFile().getName())
                .dicomFilePath(path.toFile().getAbsolutePath())
                .predictionTime(result.predictionTime())
                .frontalScore(result.score())
                .build();
        dicomAnalyzeResultRepository.save(analyzeResult);
    }

    private static AnalysisResultDto analyze() {
        try {
            log.info("analyzing...");
            long time = new Random().nextLong(10000);
            Thread.sleep(time);
            return new AnalysisResultDto(time / 1000.0, getScore());
        }
        catch (InterruptedException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    private static double getScore() {
        return new Random().nextDouble();
    }

    @Transactional
    public ExtendMaxAnalysisCountResponseDto extendMaxCount(ExtendMaxAnalysisCountRequestDto request, String name) {
        Member member = memberRepository.findByUserName(name)
                .orElseThrow(MemberNotFoundException::new);

        member.setMaxAnalyzeCnt(member.getMaxAnalyzeCnt() + request.extendCount());
        return new ExtendMaxAnalysisCountResponseDto(member.getCurAnalyzeCnt(), member.getMaxAnalyzeCnt());
    }
}
