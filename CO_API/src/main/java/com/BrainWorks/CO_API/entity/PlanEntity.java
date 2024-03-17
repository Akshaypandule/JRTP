package com.BrainWorks.CO_API.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Plan_Master")
public class PlanEntity {
    @Id
    @GeneratedValue
    @Column(name = "Plan_ID")
    private Integer planId;
    private String planName;
    @Column(name = "planStart_Date")
    private LocalDate planStartDate;
    @Column(name="planEnd_Date")
    private LocalDate planEndDate;
    @Column(name="active_SW")
    private String activeSw;
    @Column(name = "category_Id")
    private Integer categoryId;
    @CreationTimestamp
    @Column(updatable = false,name = "Created_Date")
    private LocalDate createdDate;
    @UpdateTimestamp
    @Column(insertable = false ,name = "Updated_date")
    private String updateDate;
    private String createdBy;
    private String updatedBy;
}
