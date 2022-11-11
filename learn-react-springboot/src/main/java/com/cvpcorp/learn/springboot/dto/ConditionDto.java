package com.cvpcorp.learn.springboot.dto;

import com.cvpcorp.learn.springboot.model.Patient;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConditionDto {

    private String id;
    private String beginDate;
    private String endDate;
    private Patient patient;
    private String encounterId;
    private String conditionCode;
    private String description;

}
