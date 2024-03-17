package com.Brainwork.Report_API.runner;

import com.Brainwork.Report_API.entity.EligibilityDetails;
import com.Brainwork.Report_API.repo.EligibilityDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private EligibilityDetailsRepo repo;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        EligibilityDetails e1=new EligibilityDetails();
        e1.setEligId(1);
        e1.setName("Mangesh");
        e1.setEmail("mangesh@gmail.com");
        e1.setMobile(9284763654L);
        e1.setGender('M');
        e1.setPlanName("SNAP");
        e1.setPlanStatus("Approved");
        e1.setSsn(223262665565L);

        repo.save(e1);


        EligibilityDetails e2=new EligibilityDetails();
        e2.setEligId(2);
        e2.setName("Siddh");
        e2.setEmail("siddh@gmail.com");
        e2.setMobile(545663654L);
        e2.setGender('M');
        e2.setPlanName("CCPA");
        e2.setPlanStatus("Denied");
        e1.setSsn(223262665565L);
        repo.save(e2);




    }
}
