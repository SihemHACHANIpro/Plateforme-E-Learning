package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elearning.models.Administrateur;
import com.example.elearning.models.ChefDeProjet;
import com.example.elearning.models.Formation;
import com.example.elearning.repository.AdministrateurRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdministrateurController {
	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Administrateur administrateur) {
		this.administrateurRepository.save(administrateur);
		return "enregistrée avec succès";
		}
	
	 @GetMapping("/afficherall")
	    public List<Administrateur> afficher() {
	        return this.administrateurRepository.findAll();
	    }

	//__________
	  @GetMapping("/affichagebyid/{id}")
	    public Administrateur affichagebyid(@PathVariable Long id) {
	        return this.administrateurRepository.getAdministrateurById(id);
	    }
	  
	  
	//__________________

	    @PutMapping("/modification/{id}")
	    public Administrateur update(@PathVariable Long id, @RequestBody Administrateur administrateur) {
	    	Administrateur a = this.administrateurRepository.findById(id).orElse(null);
	        if (a != null) {
	            administrateur.setId(id);
	            return this.administrateurRepository.saveAndFlush(administrateur);
	        } else {
	            throw new RuntimeException("FAIL");
	        }
	    }
	    
	    //___________
	    @DeleteMapping("/supprimer/{id}")
	    public List<Administrateur> supprimer(@PathVariable Long id) {
	        this.administrateurRepository.deleteById(id);
	        return this.administrateurRepository.findAll();
	    }
	    //_______________
	    
	    @PutMapping("/archiver/{id}")
	    public List<Administrateur> archiver(@PathVariable Long id) {
	    	Administrateur a = this.administrateurRepository.findById(id).get();
	    	a.setArchive(true);
	    	this.administrateurRepository.saveAndFlush(a);
	    	return this.administrateurRepository.findAll();
	    	
	    }
	    
	 //_________________  

	    @PutMapping("/desarchiver/{id}")
	    public List<Administrateur> desarchiver(@PathVariable Long id) {
	    	Administrateur a = this.administrateurRepository.findById(id).get();
	        a.setArchive(false);
	        this.administrateurRepository.saveAndFlush(a);
	        return this.administrateurRepository.findAll();
	    }

}
