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


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Postuler {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	private Long id;
	private String contenu;
	private String inscrireFreelance;
	private boolean archive;
	
	//@ManyToOne
	//@JoinColumn(name = "candidat_id")
	//private Candidat candidat;
	@ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private ProjetFreelance projet;

}
