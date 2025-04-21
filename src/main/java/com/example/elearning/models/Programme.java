package com.example.elearning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Programme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String titre;
	private String contenu;
	private boolean archive ;
	
	
	@ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("programmes") // Évite les boucles JSON quand Formation a la liste de Programmes
    private Formation formation;

	
		
	}
		
	

