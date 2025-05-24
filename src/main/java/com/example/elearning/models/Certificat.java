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

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateObention;
    private String statut;
    private boolean archive;
    
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    @JsonIgnoreProperties("certificats")  // Ignore la propriété certificats lors de la sérialisation du candidat

    private Candidat candidat;

}
