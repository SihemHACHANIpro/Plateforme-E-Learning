package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

	Candidat getCandidatById(Long id);
	
	// Obtenir uniquement les formations archivées
			List<Candidat> findByArchiveTrue();
			
			List<Candidat> findByArchiveIsTrue();

			// Obtenir  les formations  non archivées
			List<Candidat> findByArchiveIsFalse();
			
			
			

	}


