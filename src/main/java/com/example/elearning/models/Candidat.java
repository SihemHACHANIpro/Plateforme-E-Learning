package com.example.elearning.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
//@Table(name = "candidats") // ✅ nom explicite et au pluriel (optionnel mais conseillé)
public class Candidat extends Utilisateur {

    private int cin;         
        
}
