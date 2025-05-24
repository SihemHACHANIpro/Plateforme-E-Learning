package com.example.elearning.models;

import java.util.Collection;

import javax.persistence.Entity;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Administrateur  extends Utilisateur {
	
	
	private String type;
	private boolean archive ;
	
	
	
	
	
	@OneToMany(mappedBy = "administrateur") // Il faut faire correspondre exactement le nom du champ dans Evenement
	@JsonManagedReference
	private Collection<Evenement> evenements;


	
	@OneToMany(mappedBy = "admin")
	 @JsonIgnore 
    private Collection<Inscription> inscriptions;


	
}
