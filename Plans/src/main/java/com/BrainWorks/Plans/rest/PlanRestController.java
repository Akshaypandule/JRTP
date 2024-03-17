package com.BrainWorks.Plans.rest;

import com.BrainWorks.Plans.Constant.AppConstant;
import com.BrainWorks.Plans.Service.PlanService;
import com.BrainWorks.Plans.entity.Plan;
import com.BrainWorks.Plans.props.AppProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlanRestController {

    private PlanService planService;
    private AppProperties appProperties;

    Map<String,String> messages;

    String msg=AppConstant.EMPTY_STR;
    String response=AppConstant.EMPTY_STR;
    public PlanRestController(PlanService planService,AppProperties appProperties){
        this.planService=planService;
        this.messages=appProperties.getMessages();
        System.out.println(this.messages);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer,String>> getAllCategory(){
        Map<Integer, String> allPlanCategory = planService.getAllPlanCategory();
        return new ResponseEntity<>(allPlanCategory, HttpStatus.OK);
    }

    @PostMapping("/plan")
    public ResponseEntity<String> savePlans(@RequestBody Plan plan){
        //Map<String,String> message=appProperties.getMessage();
        boolean isSaved = planService.savePlan(plan);
        if(isSaved)
        {
            response=messages.get(AppConstant.PLAN_SAVE_SUCC);
//            response = "PlanSaveSucc";
        }
        else {
            response=messages.get(AppConstant.PLAN_SAVE_FAIL);
        }


        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping("/plan")
    public ResponseEntity<List<Plan>> getAllPlans()
    {
        List<Plan> allPlan = planService.getAllPlan();
        return new ResponseEntity<>(allPlan,HttpStatus.OK);
    }
    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> editPlan(@PathVariable Integer planId)
    {
        Plan planByID = planService.getPlanByID(planId);
        return new ResponseEntity<>(planByID,HttpStatus.OK);
    }

    @PutMapping("/plan")
    public ResponseEntity<String> updatePlans(@RequestBody Plan plan){

      //  String msg=AppConstant.EMPTY_STR;
        boolean isUpdate = planService.updatePlan(plan);
        if(isUpdate)
        {
            msg=messages.get(AppConstant.PLAN_UPDATE_SUCC);
        }
        else
        {
            msg=messages.get(AppConstant.PLAN_UPDATE_FAIL);
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
        boolean isDelete = planService.deleteById(planId);
        if(isDelete)
        {
            msg=messages.get(AppConstant.PLAN_DELETE_SUCC);
        }
        else
        {
            msg=messages.get(AppConstant.PLAN_DELETE_FAIL);
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    @PutMapping("/status-change/{planId}/{status}")
    public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){

        boolean isStatusChange = planService.planStatusChange(planId, status);
        if(isStatusChange)
        {
            msg=messages.get(AppConstant.PLAN_STATUS_CHANGE_SUCC);
        }
        else
        {
            msg=messages.get(AppConstant.PLAN_STATUS_CHANGE_FAIL);
        }
        return new ResponseEntity<>(msg,HttpStatus.OK);

    }



}

