package com.example.elearning.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {

	Formateur getFormateurById(Long id);

	List<Formateur> findByArchiveIsFalse();
	
	
}
