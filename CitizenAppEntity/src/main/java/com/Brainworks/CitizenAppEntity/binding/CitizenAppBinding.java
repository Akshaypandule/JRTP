package com.Brainworks.CitizenAppEntity.binding;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenAppBinding {
    private String fullName;
    private String email;
    private Long phNo;
    private String gender;
    private String stateName;
    private Long Ssn;
    private LocalDate dob;


}
