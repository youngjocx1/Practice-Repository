package com.cvpcorp.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class PatientDto {

    private String esisId;
    private String patientId;
    private String birthDate;
    private String deathDate;
    private String ssn;
    private String drivers;
    private String passport;
    private String prefix;
    private String firstName;
    private String lastName;
    private String suffix;
    private String maidenName;
    private String maritalStatus;
    private String race;
    private String ethnicity;
    private String gender;
    private String birthplace;
    private String address;
    private String postalCode;
    private String city;
    private String state;
    private String county;
    private String latitude;
    private String longitude;
    private BigDecimal healthcareCoverage;
    private BigDecimal healthcareExpenses;
}
