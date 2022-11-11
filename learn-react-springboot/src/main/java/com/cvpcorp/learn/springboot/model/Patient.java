package com.cvpcorp.learn.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Patient {

    @Id
    @Column(name = "esis_id")
    public String esisId;
    
    @Column(name = "patient_id")
    public String patientId;

    @Basic
    public String birthDate;

    @Basic
    public String deathDate;

    @Column(name = "social_security_number")
    public String ssn;

    @Basic
    public String drivers;

    @Basic
    public String passport;

    @Basic
    public String prefix;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "surname")
    public String lastName;

    @Basic
    public String suffix;

    @Column(name = "maiden_name")
    public String maidenName;

    @Column(name = "marital")
    public String maritalStatus;

    @Basic
    public String race;

    @Basic
    public String ethnicity;

    @Basic
    public String gender;

    @Basic
    public String birthplace;

    @Basic
    public String address;

    @Column(name = "postal_code")
    public String postalCode;

    @Basic
    public String city;

    @Basic
    public String state;

    @Basic
    public String county;

    @Column(name = "lat")
    public String latitude;

    @Column(name = "lon")
    public String longitude;

    @Column(name = "healthcare_coverage")
    public BigDecimal healthcareCoverage;

    @Column(name = "healthcare_expenses")
    public BigDecimal healthcareExpenses;
}
