package com.BrainWorks.CO_API.repo;

import com.BrainWorks.CO_API.entity.ElgDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ElgDtlsRepo extends JpaRepository<ElgDtlsEntity, Serializable> {

    public ElgDtlsEntity findByCaseNum(Long caseNum);
}
