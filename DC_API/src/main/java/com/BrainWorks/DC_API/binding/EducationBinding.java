package com.BrainWorks.DC_API.binding;

import lombok.Data;

@Data
public class EducationBinding {
    private Long caseNum;
    private String highestQualification;
    private String universityName;
    private Integer graduationYear;
}
