package com.cvpcorp.learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvpcorp.learn.springboot.model.Encounter;

public interface EncounterRepo extends JpaRepository<Encounter, String> {
}
