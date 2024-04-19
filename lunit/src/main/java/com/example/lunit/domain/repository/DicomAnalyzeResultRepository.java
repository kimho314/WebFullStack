package com.example.lunit.domain.repository;

import com.example.lunit.domain.entity.DicomAnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DicomAnalyzeResultRepository extends JpaRepository<DicomAnalyzeResult, Long> {
}
