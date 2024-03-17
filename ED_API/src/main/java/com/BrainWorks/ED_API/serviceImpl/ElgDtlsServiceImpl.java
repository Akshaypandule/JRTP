package com.BrainWorks.ED_API.serviceImpl;

import com.BrainWorks.ED_API.binding.ElgResponse;
import com.BrainWorks.ED_API.entity.*;
import com.BrainWorks.ED_API.repo.*;
import com.BrainWorks.ED_API.service.ElgDtlsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ElgDtlsServiceImpl implements ElgDtlsService {

    @Autowired
   private DcCaseRepo dcCaseRepo;
    @Autowired
    private PlanRepo planRepo;
    @Autowired
    private DcIncomeRepo incomeRepo;

    @Autowired
    private DcChildrenRepo childrenRepo;
    @Autowired
    private CitizenAppRepo citizenAppRepo;
    @Autowired
    private DcEducationRepo educationRepo;
    @Autowired
    private ElgDtlsRepo elgDtlsRepo;

    @Autowired
    private CoTriggerRepo coTriggerRepo;
    @Override
    public ElgResponse determineEligibility(Long caseNum)
    {
        Optional<DcCaseEntity> findById = dcCaseRepo.findById(caseNum);
        Integer planId=null;
        String planName=null;
        Integer appId=null;
        
        if(findById.isPresent()){
            DcCaseEntity dcCaseEntity = findById.get();
             planId = dcCaseEntity.getPlanId();
            appId = dcCaseEntity.getAppId();
        }
        Optional<PlanEntity> planEntity = planRepo.findById(planId);

        if(planEntity.isPresent()){
            PlanEntity planEntity1 = planEntity.get();
            planName=planEntity1.getPlanName();
        }
        Optional<CitizenAppEntity> app = citizenAppRepo.findById(appId);
        int age=0;
        CitizenAppEntity citizenAppEntity=null;
        if(app.isPresent()){
            citizenAppEntity = app.get();
            LocalDate dob = citizenAppEntity.getDob();
            age = Period.between(dob, LocalDate.now()).getYears();
        }
        ElgResponse response = executePlanCondition(caseNum, planName, age);
        ElgDtlsEntity elgDtlsEntity = new ElgDtlsEntity();
        BeanUtils.copyProperties(response,elgDtlsEntity);
        elgDtlsEntity.setCaseNum(caseNum);
        elgDtlsEntity.setHolderName(citizenAppEntity.getFullName());
        elgDtlsEntity.setHolderSsn(citizenAppEntity.getSsn());
        elgDtlsRepo.save(elgDtlsEntity);


        CoTriggerEntity coTriggerEntity=new CoTriggerEntity();
        coTriggerEntity.setCaseNum(caseNum);
        coTriggerEntity.setTrigStatus("Pending");
        coTriggerRepo.save(coTriggerEntity);

        return response;
    }
    private ElgResponse executePlanCondition(Long caseNum,String planName,Integer age){
        ElgResponse response=new ElgResponse();
        response.setPlanName(planName);
        DcIncomeEntity income = incomeRepo.findByCaseNum(caseNum);
        Double empIncome = income.getEmpIncome();
        if ("SNAP".equals(planName)) {
            if(empIncome <= 300){
                response.setPlanStatus("AP");
            }
            else {
                response.setPlanStatus("DN");
                response.setDenialStatus("Heigh Income ");
            }
        } else if ("CCAP".equals(planName)) {

            boolean ageCondition=true;
            boolean kidCount=false;
            List<DcChildrenEntity> childs = childrenRepo.findByCaseNum(caseNum);
            if(!childs.isEmpty())
            {
                kidCount=true;
                for(DcChildrenEntity entity:childs){
                     age = entity.getAge();
                        if(age<=16){
                            ageCondition=false;
                            break;}
                }
            }
            if(empIncome<=300 && kidCount && ageCondition){
                response.setPlanStatus("AP");
            }
            else
            {
                response.setPlanStatus("DN");
                response.setDenialStatus("Not Satisfied");
            }
        }
        else if ("Medicaid".equals(planName)) {
            if(empIncome<=300 && income.getPropertyIncome()==0) {
                response.setPlanStatus("AP");
            } else {
                response.setPlanStatus("DN");
                response.setDenialStatus("Not Satisfied");
            }
        }
        else if ("Medicare".equals(planName)) {


                if(age>65){
                    response.setPlanStatus("AP");
                }
                else {
                    response.setPlanStatus("DN");
                    response.setDenialStatus("Not Satisfied");
                }
            }
         else if ("NJW".equals(planName)) {

            DcEducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
            Integer graduationYear = educationEntity.getGraduationYear();
            Integer year = LocalDate.now().getYear();

            if(empIncome <=0 && graduationYear< year){
                response.setPlanStatus("AP");
            }
            else {
                response.setPlanStatus("DN");
                response.setDenialStatus("Not Satisfied");
            }

        }
        if(response.getPlanStatus().equals("AP")) {
            LocalDate now = LocalDate.now();
            response.setStartDate(now);
            LocalDate localDate = LocalDate.now().plusMonths(6);
            response.setEndDate(localDate);
            response.setBenefitAmount(500.00);
        }


        return response;
    }
}
