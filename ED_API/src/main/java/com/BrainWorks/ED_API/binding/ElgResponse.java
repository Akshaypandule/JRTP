package com.BrainWorks.ED_API.binding;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ElgResponse {

    private String planName;
    private String planStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double BenefitAmount;
    private String denialStatus;
}
