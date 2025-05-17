package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.ChefDeProjet;

public interface ChefDeProjetRepository extends JpaRepository<ChefDeProjet, Long> {
	ChefDeProjet getChefDeProjetById(Long id);
	
	// Obtenir uniquement les formations archivées
	List<ChefDeProjet> findByArchiveTrue();
	
	List<ChefDeProjet> findByArchiveIsTrue();

	// Obtenir  les formations  non archivées
	List<ChefDeProjet> findByArchiveIsFalse();

	

}
