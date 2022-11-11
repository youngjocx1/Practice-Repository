package com.cvpcorp.learn.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Observation {

    @Id
    @Column(name = "esis_id")
    public String id;

    @Basic
    public String date;

    @Basic
    public String description;

    @Basic
    public String code;

    @Basic
    public String value;

    @Basic
    public String units;

    @Column(name = "unit_type")
    public String unitType;
    
    @Column(name = "encounter_id")
    public String encounterId;
}
