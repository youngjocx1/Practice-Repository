package com.cvpcorp.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

import com.cvpcorp.learn.springboot.model.Condition;
import com.cvpcorp.learn.springboot.model.Medication;
import com.cvpcorp.learn.springboot.model.Observation;

@Getter @Setter
public class EncounterDto {

    private String esisId;
    private String encounterId;
    private String start;
    private String stop;
    private String encounterClass;
    private String encounterCode;
    private String patientId;
    private String description;
    private BigDecimal baseEncounterCost;
    private BigDecimal totalClaimCost;
    private BigDecimal payerCoverage;
    private String reasonCode;
    private String reasonDescription;
    private Set<Condition> conditions;
    private Set<Medication> medications;
    private Set<Observation> observations;

}
