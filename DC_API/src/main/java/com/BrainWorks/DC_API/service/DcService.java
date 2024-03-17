package com.BrainWorks.DC_API.service;

import com.BrainWorks.DC_API.binding.*;

import java.util.List;
import java.util.Map;

public interface DcService {

    public Long loadCaseNum(Integer appId);
    public Map<Integer,String> getAllPlan();
    public Long savePlanSelection(PlanSelectionBinding planSelectionBinding);
    public Long saveIncome(IncomeBinding income);
    public Long saveEducation(EducationBinding education);
    public Long saveChildren(ChildRequest child);
    public DCSummery getSummery(Long caseNum);

}
