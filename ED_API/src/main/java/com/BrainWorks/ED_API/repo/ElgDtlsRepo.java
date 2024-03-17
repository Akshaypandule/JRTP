package com.BrainWorks.ED_API.repo;

import com.BrainWorks.ED_API.entity.ElgDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ElgDtlsRepo extends JpaRepository<ElgDtlsEntity, Serializable> {
}
