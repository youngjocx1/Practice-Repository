package com.cvpcorp.learn.springboot.dto;

import java.math.BigInteger;
import java.util.List;

import com.cvpcorp.learn.springboot.model.Patient;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientResponseDtoPaginated {
	private List<Patient> patients;
	private BigInteger totalCount;
}
