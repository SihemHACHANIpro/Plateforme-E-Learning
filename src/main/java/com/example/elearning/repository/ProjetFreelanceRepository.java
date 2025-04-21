package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.elearning.models.ProjetFreelance;

public interface ProjetFreelanceRepository extends JpaRepository<ProjetFreelance,Long> {

	ProjetFreelance getProjetFreelanceById(Long id);
	
	
	// Obtenir uniquement les formations archivées
		List<ProjetFreelance> findByArchiveTrue();
		
		List<ProjetFreelance> findByArchiveIsTrue();

		// Obtenir  les formations  non archivées
		List<ProjetFreelance> findByArchiveIsFalse();


}
