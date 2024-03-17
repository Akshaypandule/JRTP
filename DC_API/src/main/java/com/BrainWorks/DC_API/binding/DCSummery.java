package com.BrainWorks.DC_API.binding;

import lombok.Data;

import java.util.List;

@Data
public class DCSummery {

    private IncomeBinding income;
    private EducationBinding education;
    private List<ChildBinding> child;
    private String planName;
}
