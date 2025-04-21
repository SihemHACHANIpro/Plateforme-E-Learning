package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.elearning.models.Planification;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

	Planification getPlanificationById(Long id);
	
	
List<Planification> findByArchiveTrue();
	
	List<Planification> findByArchiveIsTrue();

	// Obtenir  les formations  non archivées
	List<Planification> findByArchiveIsFalse();

}
