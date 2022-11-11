package com.cvpcorp.learn.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Condition {

    @Id
    @Column(name = "esis_id")
    String id;

    @Column(name = "begin_date")
    public String beginDate;

    @Column(name = "end_date")
    public String endDate;

    @Column(name = "encounter_id")
    public String encounterId;

    @Column(name = "condition_code")
    public String conditionCode;

    @Basic
    public String description;
}
