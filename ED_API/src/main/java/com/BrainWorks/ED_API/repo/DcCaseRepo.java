package com.BrainWorks.ED_API.repo;

import com.BrainWorks.ED_API.entity.DcCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Serializable> {
}
