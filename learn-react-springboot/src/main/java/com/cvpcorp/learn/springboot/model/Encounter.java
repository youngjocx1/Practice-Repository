package com.cvpcorp.learn.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Encounter {

    @Id
    @Column(name = "esis_id")
    public String esisId;
    
    @Column(name = "encounter_id")
    public String encounterId;

    @Basic
    public String start;

    @Basic
    public String stop;

    @Column(name = "encounter_class")
    public String encounterClass;

    @Column(name = "code")
    public String encounterCode;
    
    @Column(name = "patient_id")
    public String patientId;

    @Basic
    public String description;

    @Column(name = "base_encounter_cost")
    public BigDecimal baseEncounterCost;

    @Column(name = "total_claim_cost")
    public BigDecimal totalClaimCost;

    @Column(name = "payer_coverage")
    public BigDecimal payerCoverage;

    @Column(name = "reason_code")
    public String reasonCode;

    @Column(name = "reason_description")
    public String reasonDescription;
    
}
