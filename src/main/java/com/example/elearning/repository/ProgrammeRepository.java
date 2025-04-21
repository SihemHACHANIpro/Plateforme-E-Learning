package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Programme;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {

	Programme getProgrammeById(Long id);
	
	// Obtenir uniquement les formations archivées
		List<Programme> findByArchiveTrue();
		
		
		List<Programme> findByArchiveIsTrue();


		// Obtenir  les formations  non archivées
		List<Programme> findByArchiveIsFalse();


}
