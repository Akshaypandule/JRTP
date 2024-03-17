package com.BrainWorks.CO_API.repo;


import com.BrainWorks.CO_API.entity.CoTriggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CoTriggerRepo extends JpaRepository<CoTriggerEntity, Serializable> {

    public List<CoTriggerEntity> findByTrigStatus(String status);

    public CoTriggerEntity findByCaseNum(Long caseNum);
}
