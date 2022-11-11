package com.cvpcorp.learn.springboot.maps;

import com.cvpcorp.learn.springboot.dto.PatientDto;
import com.cvpcorp.learn.springboot.model.Patient;

public class PatientMap {

    private PatientMap() {}

	public static PatientDto map(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setEsisId(patient.getEsisId());
        dto.setPatientId(patient.getPatientId());
        dto.setPrefix(patient.getPrefix());
        dto.setFirstName(patient.getFirstName());
        dto.setMaidenName(patient.getMaidenName());
        dto.setLastName(patient.getLastName());
        dto.setSuffix(patient.getSuffix());
        dto.setGender(patient.getGender());
        dto.setEthnicity(patient.getEthnicity());
        dto.setRace(patient.getRace());
        dto.setMaritalStatus(patient.getMaritalStatus());
        dto.setSsn(patient.getSsn());
        dto.setDrivers(patient.getDrivers());
        dto.setPassport(patient.getPassport());
        dto.setBirthDate(patient.getBirthDate());
        dto.setBirthplace(patient.getBirthplace());
        dto.setDeathDate(patient.getDeathDate());
        dto.setAddress(patient.getAddress());
        dto.setCity(patient.getCity());
        dto.setState(patient.getState());
        dto.setPostalCode(patient.getPostalCode());
        dto.setCounty(patient.getCounty());
        dto.setLatitude(patient.getLatitude());
        dto.setLongitude(patient.getLongitude());
        dto.setHealthcareCoverage(patient.getHealthcareCoverage());
        dto.setHealthcareExpenses(patient.getHealthcareExpenses());

        return dto;
    }

    public static Patient map(PatientDto dto) {
        Patient patient = new Patient();
        patient.setEsisId(dto.getEsisId());
        patient.setPatientId(dto.getPatientId());
        patient.setPrefix(dto.getPrefix());
        patient.setFirstName(dto.getFirstName());
        patient.setMaidenName(dto.getMaidenName());
        patient.setLastName(dto.getLastName());
        patient.setSuffix(dto.getSuffix());
        patient.setGender(dto.getGender());
        patient.setEthnicity(dto.getEthnicity());
        patient.setRace(dto.getRace());
        patient.setMaritalStatus(dto.getMaritalStatus());
        patient.setSsn(dto.getSsn());
        patient.setDrivers(dto.getDrivers());
        patient.setPassport(dto.getPassport());
        patient.setBirthDate(dto.getBirthDate());
        patient.setBirthplace(dto.getBirthplace());
        patient.setDeathDate(dto.getDeathDate());
        patient.setAddress(dto.getAddress());
        patient.setCity(dto.getCity());
        patient.setState(dto.getState());
        patient.setPostalCode(dto.getPostalCode());
        patient.setCounty(dto.getCounty());
        patient.setLatitude(dto.getLatitude());
        patient.setLongitude(dto.getLongitude());
        patient.setHealthcareCoverage(dto.getHealthcareCoverage());
        patient.setHealthcareExpenses(dto.getHealthcareExpenses());

        return patient;
    }
}
