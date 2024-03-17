package com.BrainWorks.Plans.ServiceTest;

import com.BrainWorks.Plans.Service.PlanService;
import com.BrainWorks.Plans.ServiceImpl.PlanServiceImpl;
import com.BrainWorks.Plans.entity.Plan;
import com.BrainWorks.Plans.repo.PlanCategoryRepo;
import com.BrainWorks.Plans.repo.PlanRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@WebMvcTest(PlanServiceImpl.class)
public class DemoTest {

    @MockBean
    private PlanRepo planRepo;

    @MockBean
    private PlanCategoryRepo planCategoryRepo;
    
    @Autowired
    private PlanService planService;


    @Test
    public void testSavePlan(){
        Plan plan=new Plan();

        plan.setPlanId(1);
        plan.setPlanName("SSN");
        plan.setCategoryId(5);
        plan.setActiveSw("Y");

        when(planRepo.save(any(Plan.class))).thenReturn(plan);

        boolean b = planService.savePlan(plan);
        assertTrue(true);
        verify(planRepo, times(1)).save(any(Plan.class));
    }
}
