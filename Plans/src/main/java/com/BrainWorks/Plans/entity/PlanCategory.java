package com.BrainWorks.Plans.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Plan_Category")
public class PlanCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    @Column(name = "category_Name")
    private String categoryName;

    private String activeSw;
    @Column(name = "Created_By")
    private String CreatedBy;
    @Column(name="Updated-By")
    private String updatedBy;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    @Column( insertable = false)
    private LocalDate updateDate;
}
