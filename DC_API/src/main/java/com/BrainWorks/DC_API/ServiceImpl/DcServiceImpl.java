package com.BrainWorks.DC_API.ServiceImpl;

import com.BrainWorks.DC_API.binding.*;
import com.BrainWorks.DC_API.entity.*;
import com.BrainWorks.DC_API.repo.*;
import com.BrainWorks.DC_API.service.DcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DcServiceImpl implements DcService {

    @Autowired
    private DcCaseRepo caseRepo;
    @Autowired
    private PlanRepo planRepo;
    @Autowired
    private DcIncomeRepo incomeRepo;
    @Autowired
    private DcEducationRepo educationRepo;
    @Autowired
    private DcChildrenRepo childRepo;
    @Autowired
    private CitizenAppRepo citizenAppRepo;
    @Override
    public Long loadCaseNum(Integer appId) {

        Optional<CitizenAppEntity> findByappId = citizenAppRepo.findById(appId);

        if(findByappId.isPresent()){

            DcCaseEntity caseEntity=new DcCaseEntity();
            caseEntity.setAppId(appId);
            DcCaseEntity save = caseRepo.save(caseEntity);
            return save.getCaseNum();
        }
        return 0l;
    }

    @Override
    public Map<Integer,String> getAllPlan()
    {
        List<PlanEntity> findAll = planRepo.findAll();
       Map<Integer,String> plansMap=new HashMap<>();
        for(PlanEntity p:findAll)
        {
            plansMap.put(p.getPlanId(),p.getPlanName());
        }
        return plansMap;
    }

    @Override
    public Long savePlanSelection(PlanSelectionBinding planSelectionBinding)
    {
        Optional<DcCaseEntity> byId = caseRepo.findById(planSelectionBinding.getCaseNum());

        if(byId.isPresent()){
//            DcCaseEntity entity=new DcCaseEntity();
//            entity.setPlanId(planSelectionBinding.getPlanId());
            DcCaseEntity entity = byId.get();
            entity.setPlanId(planSelectionBinding.getPlanId());
            //entity.setCaseNum(planSelectionBinding.getCaseNum());
            caseRepo.save(entity);
            return planSelectionBinding.getCaseNum();
        }

        return null;
    }

    @Override
    public Long saveIncome(IncomeBinding income)
    {
        DcIncomeEntity entity=new DcIncomeEntity();
        BeanUtils.copyProperties(income,entity);
        incomeRepo.save(entity);
        return income.getCaseNum();
    }

    @Override
    public Long saveEducation(EducationBinding education) {

        DcEducationEntity entity=new DcEducationEntity();
        BeanUtils.copyProperties(education,entity);
        DcEducationEntity save = educationRepo.save(entity);
        return education.getCaseNum();
    }

    @Override
    public Long saveChildren(ChildRequest child) {

        List<ChildBinding> child1 = child.getChild();
        for(ChildBinding c: child1){
            DcChildrenEntity entity=new DcChildrenEntity();
            BeanUtils.copyProperties(c,entity);
            childRepo.save(entity);
        }


        return child.getCaseNum();
    }

    @Override
    public DCSummery getSummery(Long caseNum)
    {
        String planName="";
        DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
        DcEducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
        List<DcChildrenEntity> childCaseEntity = childRepo.findByCaseNum(caseNum);

        // get PlanName

        Optional<DcCaseEntity> dcCase = caseRepo.findById(caseNum);
        if(dcCase.isPresent()){
            Integer planId = dcCase.get().getPlanId();
            Optional<PlanEntity> plan = planRepo.findById(planId);
            if(plan.isPresent()){
                planName=plan.get().getPlanName();
            }
        }
        // Add all data into summery
        DCSummery dcSummery=new DCSummery();
        dcSummery.setPlanName(planName);

        IncomeBinding income=new IncomeBinding();
        BeanUtils.copyProperties(incomeEntity,income);
        dcSummery.setIncome(income);

        EducationBinding education=new EducationBinding();
        BeanUtils.copyProperties(educationEntity,education);
        dcSummery.setEducation(education);

        List<ChildBinding> child=new ArrayList<>();
        for(DcChildrenEntity entity:childCaseEntity) {
            ChildBinding ch = new ChildBinding();
            BeanUtils.copyProperties(entity,ch);

            child.add(ch);
            dcSummery.setChild(child);

        }

        return dcSummery;
    }
}
