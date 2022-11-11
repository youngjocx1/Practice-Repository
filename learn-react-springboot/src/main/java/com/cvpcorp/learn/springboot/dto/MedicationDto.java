package com.cvpcorp.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicationDto {

    private String id;
    private String startDate;
    private String stopDate;
    private Integer numericCode;
    private String description;
    private String encounterId;

}
