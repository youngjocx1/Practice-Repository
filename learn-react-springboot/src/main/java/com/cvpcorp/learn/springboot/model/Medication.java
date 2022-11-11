package com.cvpcorp.learn.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Medication {

    @Id
    @Column(name = "esis_id")
    public String id;

    @Column(name = "start_date")
    public String startDate;

    @Column(name = "stop_date")
    public String stopDate;

    @Column(name = "numeric_code")
    public Integer numericCode;

    @Basic
    public String description;
    
    @Column(name = "encounter_id")
    public String encounterId;
    
    @Column(name = "reason_code")
    public String reasonCode;
    
    @Column(name = "reason_description")
    public String reasonDescription;
    
    @Column(name = "base_cost")
    public String baseCost;
    
    @Column(name = "payer_coverage")
    public String payerCoverage;
    
    @Basic
    public String dispenses;
    
    @Column(name = "total_cost")
    public String totalCost;
}
