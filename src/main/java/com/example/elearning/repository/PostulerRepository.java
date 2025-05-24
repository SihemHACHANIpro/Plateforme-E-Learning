package com.example.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearning.models.Evenement;
import com.example.elearning.models.Postuler;

public interface PostulerRepository extends JpaRepository<Postuler, Long> {
	
	
			List<Postuler> findByArchiveTrue();
			
			List<Postuler> findByArchiveIsTrue();

			List<Postuler> findByArchiveIsFalse();

			Postuler getPostulerById(Long id);


}
