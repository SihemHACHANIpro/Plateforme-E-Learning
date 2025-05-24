package com.example.elearning.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor @Data
@Entity
public class ProjetFreelance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String planification;
    private String statut;
    private String dateDebut;
    private String dateFin;
    private boolean archive ;
    
    
 
    
    @OneToMany(mappedBy = "projetFreelance")
    @JsonIgnore // Pour éviter les boucles infinies quand tu fais du JSON
    private Collection<Rapport> rapports;
    
    //______________________
    
    @OneToMany(mappedBy = "projetFreelance")
    @JsonIgnore 
    private Collection<Planification>Planifications ;
	
    //-----------------------
    @OneToMany(mappedBy = "projet")
    @JsonIgnore
    private Collection<Postuler> candidatures;
    
    
    //____________________
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private ChefDeProjet chefDeProjet;

}
