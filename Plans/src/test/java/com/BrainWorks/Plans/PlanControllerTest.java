package com.BrainWorks.Plans;
import com.BrainWorks.Plans.Constant.AppConstant;
import com.BrainWorks.Plans.Service.PlanService;
import com.BrainWorks.Plans.entity.Plan;
import com.BrainWorks.Plans.props.AppProperties;
import com.BrainWorks.Plans.rest.PlanRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(PlanRestController.class)
public class PlanControllerTest
{
    @MockBean
    private PlanService planService;

    @MockBean
    private AppProperties appProperties;

    @Autowired
    private MockMvc mockMvc;

    @Test public void SavePlanTest() throws Exception
    {

        Plan plan=new Plan();
        plan.setPlanId(1);
        plan.setPlanName("SSN");
        plan.setCategoryId(5);
        plan.setActiveSw("Y");
        when(planService.savePlan(plan)).thenReturn(true);
        ObjectMapper objectMapper=new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(plan);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/plan")
                .contentType("application/json")
                .content(userJson);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201,status);
//        String result = mvcResult.getResponse().getContentAsString();
//        System.out.println(result);
//        assertEquals(AppConstant.PLAN_SAVE_SUCC,result);
    }

    public void getPlan(){
        
    }
}
