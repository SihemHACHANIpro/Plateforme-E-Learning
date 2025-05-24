package com.example.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

}
