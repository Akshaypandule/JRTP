package com.BrainWorks.DC_API.repo;

import com.BrainWorks.DC_API.entity.CitizenAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Serializable> {
}
