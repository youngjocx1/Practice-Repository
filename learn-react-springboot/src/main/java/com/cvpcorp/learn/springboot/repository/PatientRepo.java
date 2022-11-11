package com.cvpcorp.learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvpcorp.learn.springboot.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, String> {
	Patient findByPatientId(String patientId);
}
