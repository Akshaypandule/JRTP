package com.BrainWorks.Plans.Service;

import com.BrainWorks.Plans.entity.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {

    public boolean savePlan(Plan plan);
    public Plan getPlanByID(Integer id);
    public List<Plan> getAllPlan();
    public boolean updatePlan(Plan plan);
    public boolean deleteById(Integer id);
    public Map<Integer,String> getAllPlanCategory();
    public boolean planStatusChange(Integer planId,String status);
}
