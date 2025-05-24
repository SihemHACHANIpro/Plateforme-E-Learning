package com.example.elearning.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private boolean archive;

    // Relation OneToMany avec les sessions
    @OneToMany(mappedBy = "formation")
    @JsonIgnore
    private Collection<Session> sessions;

    // Relation OneToMany avec les programmes
    @OneToMany(mappedBy = "formation")
    @JsonIgnore
    private Collection<Programme> programmes;

    // Relation OneToMany avec les spécialités
    @OneToMany(mappedBy = "formation")
    @JsonIgnore
    private Collection<Specialite> specialites;

    // ✅ Relation ManyToMany avec les candidats
    @ManyToMany(mappedBy = "formations")  // <-- corrigé ici
    @JsonIgnore
    private Collection<Candidat> candidats;
}
