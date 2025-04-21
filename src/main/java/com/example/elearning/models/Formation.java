package com.example.elearning.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Formation {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	 private Long id;

	    private String title;
	    private String description;
	    private String categorie;
	    private String duree;
	    private String dateDebut;
	    private String dateFin;
	    private String statut;
		private boolean archive ;
		
		
		//@OneToMany(mappedBy="formation")
		//private Collection<Programme> programmes;
		
		@OneToMany(mappedBy = "formation")
	    @JsonIgnore // Pour éviter les boucles infinies quand tu fais du JSON
	    private Collection<Session> sessions;
		
		@OneToMany(mappedBy = "formation")
	    @JsonIgnore // Pour éviter les boucles infinies quand tu fais du JSON
	    private Collection<Programme> programmes;
		
		@OneToMany(mappedBy = "formation")
	    @JsonIgnore // Pour éviter les boucles infinies quand tu fais du JSON
	    private Collection<Specialite> specialites ;
	}

