package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.elearning.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Utilisateur getUtilisateurById(Long id);
	
    List<Utilisateur> findByArchiveTrue();
	List<Utilisateur> findByArchiveIsTrue();
	List<Utilisateur> findByArchiveIsFalse();


}
