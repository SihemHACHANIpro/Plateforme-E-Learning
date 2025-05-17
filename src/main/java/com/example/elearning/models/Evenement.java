package com.example.elearning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Evenement {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String titre;
	private String dateEvenement;
	private String lieu;
	private String description;
	private boolean archive ;

}
