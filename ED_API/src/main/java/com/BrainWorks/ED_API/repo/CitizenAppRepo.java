package com.BrainWorks.ED_API.repo;

import com.BrainWorks.ED_API.entity.CitizenAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Serializable> {
}
