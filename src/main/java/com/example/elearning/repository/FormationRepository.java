package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Formation;


public interface FormationRepository extends JpaRepository<Formation, Long> {

	Formation getFormationById(Long id);
	
	// Obtenir uniquement les formations archivées
	List<Formation> findByArchiveTrue();
	
	List<Formation> findByArchiveIsTrue();

	// Obtenir  les formations  non archivées
	List<Formation> findByArchiveIsFalse();



	

	

	

}
