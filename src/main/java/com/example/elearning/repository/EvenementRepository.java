package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Evenement;


public interface EvenementRepository extends JpaRepository<Evenement, Long> {

	Evenement getEvenementById(Long id);
	
	List<Evenement> findByArchiveTrue();
	
	
	List<Evenement> findByArchiveIsTrue();


	// Obtenir  les formations  non archivées
	List<Evenement> findByArchiveIsFalse();

}
