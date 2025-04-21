package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Rapport;

public interface RapportRepository extends JpaRepository<Rapport, Long> {

	Rapport getRapportById(Long id);
	
List<Rapport> findByArchiveTrue();
	
	List<Rapport> findByArchiveIsTrue();

	// Obtenir  les formations  non archivées
	List<Rapport> findByArchiveIsFalse();


}
