package com.Brainworks.CitizenAppEntity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Struct;
import java.time.LocalDate;

@Entity
@Data
public class Citizen_Apps {

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
