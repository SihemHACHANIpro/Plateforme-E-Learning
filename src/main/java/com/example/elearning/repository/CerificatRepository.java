package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Certificat;
import com.example.elearning.models.ChefDeProjet;

public interface CerificatRepository extends JpaRepository<Certificat, Long>{

	Certificat getCertificatById(Long id);
	
	// Obtenir uniquement les formations archivées
		List<Certificat> findByArchiveTrue();
		
		List<Certificat> findByArchiveIsTrue();

		// Obtenir  les formations  non archivées
		List<Certificat> findByArchiveIsFalse();


}
