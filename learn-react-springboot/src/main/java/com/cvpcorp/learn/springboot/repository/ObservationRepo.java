package com.cvpcorp.learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvpcorp.learn.springboot.model.Observation;

public interface ObservationRepo extends JpaRepository<Observation, String> {
}
