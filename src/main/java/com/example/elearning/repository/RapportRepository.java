package com.example.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Rapport;

public interface RapportRepository extends JpaRepository<Rapport, Long> {

}
