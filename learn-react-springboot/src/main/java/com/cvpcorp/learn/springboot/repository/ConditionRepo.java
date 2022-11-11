package com.cvpcorp.learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cvpcorp.learn.springboot.model.Condition;

public interface ConditionRepo extends JpaRepository<Condition, String> {
}
