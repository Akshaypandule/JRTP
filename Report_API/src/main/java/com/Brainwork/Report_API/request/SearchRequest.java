package com.Brainwork.Report_API.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequest {

    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
}
