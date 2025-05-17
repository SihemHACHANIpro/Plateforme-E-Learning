package com.example.elearning.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity

@Inheritance(strategy =InheritanceType.TABLE_PER_CLASS )
public class Utilisateur {
   
    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePass;
    private String role;
    private boolean archive;
	
	}
    
	
		
	
    
    
	
