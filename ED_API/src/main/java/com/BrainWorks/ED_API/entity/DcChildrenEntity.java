package com.BrainWorks.ED_API.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="DC_Children")
public class DcChildrenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer childrenId;
    private Long caseNum;
    private String childrenName;
    private Integer age;
    private Long childrenSsn;

}
