package com.BrainWorks.Plans.ServiceImpl;

import com.BrainWorks.Plans.Service.PlanService;
import com.BrainWorks.Plans.entity.Plan;
import com.BrainWorks.Plans.entity.PlanCategory;
import com.BrainWorks.Plans.repo.PlanCategoryRepo;
import com.BrainWorks.Plans.repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanCategoryRepo planCategoryRepo;
    @Autowired
    private PlanRepo planRepo;
    @Override
    public boolean savePlan(Plan plan) {

        Plan saved = planRepo.save(plan);
       /* if(saved.getPlanId()!=null){
            return true;
        }
        else
        {
        return false;
        }*/

        //return saved.getPlanId()!=null ? true:false;

        return  saved.getPlanId()!=null;
    }
    @Override
    public Plan getPlanByID(Integer id) {

        Optional<Plan> byId = planRepo.findById(id);
        if(byId.isPresent()){
           return byId.get();
        }
        return null;
    }
    @Override
    public List<Plan> getAllPlan() {

        return planRepo.findAll();
    }
    @Override
    public boolean updatePlan(Plan plan) {
        planRepo.save(plan);
        if(plan.getPlanId()!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean status=false;

        try
        {
            planRepo.deleteById(id);
            status=true;
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return status;
    }

    @Override
    public Map<Integer, String> getAllPlanCategory()
    {
        List<PlanCategory> allCategory = planCategoryRepo.findAll();
        Map<Integer,String> category=new HashMap<>();
        allCategory.forEach(findCategory->
        {
            category.put(findCategory.getCategoryId(),findCategory.getCategoryName());
        });
        return category;
    }

    @Override
    public boolean planStatusChange(Integer planId, String status)
    {
        Optional<Plan> byId = planRepo.findById(planId);
        if(byId.isPresent()){
            Plan plan = byId.get();
            plan.setActiveSw(status);
            planRepo.save(plan);
            return  true;
        }
        return false;
    }
}
