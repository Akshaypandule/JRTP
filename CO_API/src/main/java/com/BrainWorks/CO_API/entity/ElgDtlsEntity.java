package com.BrainWorks.CO_API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ElgDtlsEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer elgTraceId;
    private Long caseNum;
    private String holderName;
    private Long holderSsn;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmount;
    private String denialReason;
}
