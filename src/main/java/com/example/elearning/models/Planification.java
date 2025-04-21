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
public class Planification {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   private String date;
	   private String description;
	   private boolean archive ;
	   
	   @ManyToOne
	    @JoinColumn(name = "projet_id")
	   @JsonIgnoreProperties("Planifications")
	   private ProjetFreelance projetFreelance;
}
