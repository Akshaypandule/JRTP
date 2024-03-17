package com.BrainWorks.CO_API.repo;
import com.BrainWorks.CO_API.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PlanRepo extends JpaRepository<PlanEntity, Serializable> {
}
