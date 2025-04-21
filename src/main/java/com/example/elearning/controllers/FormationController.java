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

import com.example.elearning.models.Formation;

import com.example.elearning.repository.FormationRepository;

@RestController
@RequestMapping("/formation")
@CrossOrigin("*")
public class FormationController {

	@Autowired
	private FormationRepository formationRepository;

	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Formation formtion) {
	this.formationRepository.save(formtion);
	return "enregistrée avec succès";
	}
	 

    @GetMapping("/aficherall")
    public List<Formation> afficher() {
        return this.formationRepository.findAll();
    }

    @GetMapping("/affichagebyid/{id}")
    public Formation affichagebyid(@PathVariable Long id) {
        return this.formationRepository.getFormationById(id);
    }

    @PutMapping("/modification/{id}")
    public Formation update(@PathVariable Long id, @RequestBody Formation formation) {
        Formation f = this.formationRepository.findById(id).orElse(null);
        if (f != null) {
            formation.setId(id);
            return this.formationRepository.saveAndFlush(formation);
        } else {
            throw new RuntimeException("FAIL");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        this.formationRepository.deleteById(id);
        return "supprimée avec succès";
    }
    
    
    @PutMapping("/archiver")
    public String archiver(Long id) {
    	Formation f = this.formationRepository.findById(id).get();
    	f.setArchive(true);
    	this.formationRepository.saveAndFlush(f);
    	return "true" ;
    	
    }
    
    @PutMapping("/desarchiver")
    public String desarchiver(Long id) {
    	Formation f = this.formationRepository.findById(id).get();
    	f.setArchive(false);
    	this.formationRepository.saveAndFlush(f);
    	return "true";
    }
    

    
    

    @GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Formation> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.formationRepository.findByArchiveIsFalse();
    }
    
    @GetMapping("/listarchivee")
	public List<Formation> listeArchivee() {
	    // On récupère toutes les formations où "archive" est true (archivées)
	    return this.formationRepository.findByArchiveIsTrue();
	}
    
    
    
}
