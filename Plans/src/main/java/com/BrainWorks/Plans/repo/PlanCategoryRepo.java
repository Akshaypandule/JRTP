package com.BrainWorks.Plans.repo;

import com.BrainWorks.Plans.entity.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory,Integer> {
}
