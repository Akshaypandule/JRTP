package com.Brainwork.Report_API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Eligibility_Details")
public class EligibilityDetails {

    @Id
    private Integer eligId;
    private String name;
    private String email;
    private Long mobile;
    private Character gender;
    private Long ssn;
    private String planStatus;
    private String planName;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private LocalDate planCreatedDate;
    private LocalDate planUpdateDate;
    private String createdBy;
    private String UpdatedBy;
}
