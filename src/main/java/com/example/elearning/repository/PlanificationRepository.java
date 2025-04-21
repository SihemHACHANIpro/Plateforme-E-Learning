package com.example.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Planification;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

}
