package com.example.elearning.models;

import java.util.Collection;


import javax.persistence.Entity;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ChefDeProjet extends Utilisateur {
	
	
    private String descriptionProfile;
    
 // mappedBy = "chefDeProjet" car relation inverse
    
    @OneToMany(mappedBy = "chefDeProjet")
    private Collection<ProjetFreelance> projets;
   
    

    
}
