package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Administrateur;

public interface AdministrateurRepository  extends JpaRepository<Administrateur, Long>{

	// Obtenir uniquement les formations archivées
		List<Administrateur> findByArchiveTrue();
		
		List<Administrateur> findByArchiveIsTrue();

		// Obtenir  les formations  non archivées
		List<Administrateur> findByArchiveIsFalse();

		Administrateur getAdministrateurById(Long id);

	
}
