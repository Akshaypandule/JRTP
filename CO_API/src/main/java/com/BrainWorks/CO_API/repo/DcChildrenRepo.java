package com.BrainWorks.CO_API.repo;

import com.BrainWorks.CO_API.entity.DcChildrenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface DcChildrenRepo extends JpaRepository<DcChildrenEntity, Serializable> {

    public List<DcChildrenEntity> findByCaseNum(Long caseNum);
}
