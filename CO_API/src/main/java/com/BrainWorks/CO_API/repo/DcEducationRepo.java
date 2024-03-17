package com.BrainWorks.CO_API.repo;
import com.BrainWorks.CO_API.entity.DcEducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcEducationRepo extends JpaRepository<DcEducationEntity, Serializable> {

    public DcEducationEntity findByCaseNum(Long caseNum);
}
