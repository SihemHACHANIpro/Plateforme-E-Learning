package com.example.elearning.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.elearning.models.Candidat;
import com.example.elearning.models.Certificat;
import com.example.elearning.models.ChefDeProjet;
import com.example.elearning.models.Evenement;
import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.models.Rapport;
import com.example.elearning.repository.AdministrateurRepository;
import com.example.elearning.repository.EvenementRepository;

@RestController
@RequestMapping("/evenement")
@CrossOrigin("*")
public class EvenementConteroller {
	@Autowired
	private EvenementRepository evenementRepository;
	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	
	
	
	@PostMapping("/ajouter/{idadmin}")
	public Evenement ajouter(@RequestBody Evenement evenement, @PathVariable Long idadmin) {
	    Administrateur administrateur = administrateurRepository.findById(idadmin).orElse(null);
	    evenement.setAdministrateur(administrateur);
	    return evenementRepository.save(evenement);
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
    public List<Evenement> supprimer(@PathVariable Long id) {
        this.evenementRepository.deleteById(id);
        return this.evenementRepository.findAll();

    }
	
	//___________
	
	
	@PutMapping("/archiver/{id}")
    public List<Evenement> archiver(@PathVariable Long id) {
    	Evenement e = this.evenementRepository.findById(id).get();
    	e.setArchive(true);
    	this.evenementRepository.saveAndFlush(e);
    	return this.evenementRepository.findAll();
	}
	//_______________
	
	@PutMapping("/desarchiver/{id}")
    public List<Evenement> desarchiver(@PathVariable Long id) {
		Evenement e = this.evenementRepository.findById(id).get();
        e.setArchive(false);
        this.evenementRepository.saveAndFlush(e);
        return this.evenementRepository.findAll();
    }	///____________________
	
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Evenement> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.evenementRepository.findByArchiveIsFalse();
    }
	
}
