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
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
    private  String dateSession;

    private  String lieu;

    private  String statut;
    
    private boolean archive ;
    
    
    //@ManyToOne
    //@JoinColumn(name="formation_id")
    //private Formation formation;
    
    
    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("sessions") // Pour éviter les erreurs de sérialisation
    private Formation formation;


}
