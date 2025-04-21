package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

	Specialite getSpecialiteById(Long id);
	
	// Obtenir uniquement les formations archivées
		List<Specialite> findByArchiveTrue();
		
		List<Specialite> findByArchiveIsTrue();

		// Obtenir  les formations  non archivées
		List<Specialite> findByArchiveIsFalse();

}
