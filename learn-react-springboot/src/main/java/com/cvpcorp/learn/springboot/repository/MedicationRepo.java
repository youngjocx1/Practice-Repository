package com.cvpcorp.learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvpcorp.learn.springboot.model.Medication;

public interface MedicationRepo extends JpaRepository<Medication, String> {
}
