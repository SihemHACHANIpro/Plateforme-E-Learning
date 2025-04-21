package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Session;

public interface SessionRepository extends JpaRepository<Session,Long> {

	Session getSessionById(Long id);
	
	// Obtenir uniquement les formations archivées
		List<Session> findByArchiveTrue();
		
		List<Session> findByArchiveIsTrue();

		// Obtenir  les formations  non archivées
		List<Session> findByArchiveIsFalse();


}
