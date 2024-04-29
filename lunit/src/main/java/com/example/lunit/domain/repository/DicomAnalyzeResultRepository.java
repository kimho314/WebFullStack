package com.example.lunit.domain.repository;

import com.example.lunit.domain.entity.DicomAnalyzeResult;
import com.example.lunit.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DicomAnalyzeResultRepository extends JpaRepository<DicomAnalyzeResult, Long> {
    List<DicomAnalyzeResult> findAllByMemberOrderByCreatedAtDesc(Member member);
}
