package com.BrainWorks.Plans.repo;

import com.BrainWorks.Plans.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan,Integer> {
}
