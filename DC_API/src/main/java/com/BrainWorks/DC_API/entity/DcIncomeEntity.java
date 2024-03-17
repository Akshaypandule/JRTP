package com.BrainWorks.DC_API.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DC_Income")
public class DcIncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeId;
    private Long caseNum;
    private Double empIncome;
    private Double propertyIncome;
}
