package com.Brainwork.Report_API.repo;

import com.Brainwork.Report_API.entity.EligibilityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails,Integer> {


    @Query("select distinct (planName) from EligibilityDetails")
    public List<String> findPlanName();

    @Query("select distinct(planStatus) from EligibilityDetails")
    public List<String> findPlanStatus();
}
