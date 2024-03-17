package com.BrainWorks.CO_API.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CoTriggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trgId;
    private Long caseNum;
    @Lob
    private byte[] pdf;
    private String trigStatus;
}
