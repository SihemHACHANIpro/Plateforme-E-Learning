package com.example.elearning.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
 @Data
@Entity
public class Inscription {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String date;
	
	
	
	
   @ManyToOne
   @JoinColumn(name = "candidat_id")
   private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;
    
    
    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Administrateur admin;

}
