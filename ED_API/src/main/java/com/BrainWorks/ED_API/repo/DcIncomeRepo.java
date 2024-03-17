package com.BrainWorks.ED_API.repo;

import com.BrainWorks.ED_API.entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Serializable> {
    public DcIncomeEntity findByCaseNum(Long caseNum);
}
