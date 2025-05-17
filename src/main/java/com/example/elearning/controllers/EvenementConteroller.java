package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elearning.models.Evenement;
import com.example.elearning.models.Programme;
import com.example.elearning.repository.EvenementRepository;

@RestController
@RequestMapping("/evenement")
public class EvenementConteroller {
	@Autowired
	private EvenementRepository evenementRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Evenement evenement) {
		this.evenementRepository.save(evenement);
		return "enregistrée avec succès";
	}
	
	//__________________
	
	@GetMapping("/aficherall")
    public List<Evenement> afficher() {
        return this.evenementRepository.findAll();
    }
	
	//______________________
	
	@GetMapping("/affichagebyid/{id}")
    public Evenement affichagebyid(@PathVariable Long id) {
        return this.evenementRepository.getEvenementById(id);
    }
	
	//____________________
	
	@PutMapping("/modification/{id}")
	public Evenement update(@PathVariable Long id, @RequestBody Evenement evenement) {
	    Evenement e = this.evenementRepository.findById(id).orElse(null);
	    if (e != null) {
	        evenement.setId(id);
	        return this.evenementRepository.saveAndFlush(evenement);
	    } else {
	        throw new RuntimeException("FAIL");
	    }
	    
	    
	    
	}
	
	//____
	@DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        this.evenementRepository.deleteById(id);
        return "supprimée avec succès";
    }
	
	//___________
	
	@PutMapping("/archiver")
    public String archiver(Long id) {
    	Evenement e = this.evenementRepository.findById(id).get();
    	e.setArchive(true);
    	this.evenementRepository.saveAndFlush(e);
    	return "true" ;
    	
    }
	
	//_______________
	
	@PutMapping("/desarchiver")
	public String desarchiver(Long id) {
		Evenement e =this.evenementRepository.findById(id).get();
		e.setArchive(false);
		this.evenementRepository.saveAndFlush(e);
		return "true";
	}
	///____________________
	
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Evenement> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.evenementRepository.findByArchiveIsFalse();
    }
	
}
