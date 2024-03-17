package com.Brainworks.CitizenAppEntity.repo;

import com.Brainworks.CitizenAppEntity.entity.Citizen_Apps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitizenAppRepo extends JpaRepository<Citizen_Apps, Serializable>
{

}
