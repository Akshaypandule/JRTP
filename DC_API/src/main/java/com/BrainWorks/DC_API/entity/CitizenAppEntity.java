package com.BrainWorks.DC_API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Data
@Entity
public class CitizenAppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer App_Id;
    private String fullName;
    private String email;
    private Long phNo;
    private String gender;
    private String stateName;
    private Long Ssn;
    private LocalDate dob;
    @CreationTimestamp
    private LocalDate created_Date;
    @UpdateTimestamp
    private LocalDate updated_Date;
    private String created_By;
    private String updated_By;
}
