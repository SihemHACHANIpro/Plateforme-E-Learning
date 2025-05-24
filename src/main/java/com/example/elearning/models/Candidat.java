package com.example.elearning.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Candidat extends Utilisateur {

    private int cin;

    // Relation OneToMany avec les candidatures
    @OneToMany(mappedBy = "candidat")
    @JsonIgnore
    private List<Postuler> candidatures;

    // Relation OneToMany avec les certificats
    @OneToMany(mappedBy = "candidat")
    @JsonIgnore
    private Collection<Certificat> certificats;

    // ✅ Relation ManyToMany avec les formations
    @ManyToMany
    @JoinTable(
        name = "candidat_formation",
        joinColumns = @JoinColumn(name = "candidat_id"),
        inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    @JsonIgnore
    private Collection<Formation> formations;
}
