package com.example.lunit.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "DICOM_ANALYZE_RESULT")
@NoArgsConstructor
@AllArgsConstructor
public class DicomAnalyzeResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DICOM_FILE_NAME", length = 1000)
    private String dicomFileName;

    @Column(name = "DICOM_FILE_PATH", length = 1000)
    private String dicomFilePath;

    @Column(name = "PREDICTION_TIME")
    private Double predictionTime;

    @Column(name = "FRONTAL_SCORE")
    private Double frontalScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}