package com.cvpcorp.learn.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientQueryDto {
	
	private String firstName;
	private String lastName;
	private String ssn;
	private String drivers;
	private String dob;
	private String address;
	private String city;
	private String state;
	private String zipCode;
}
