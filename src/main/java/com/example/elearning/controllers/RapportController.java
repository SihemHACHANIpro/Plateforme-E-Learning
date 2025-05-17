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


import com.example.elearning.models.Rapport;
import com.example.elearning.repository.RapportRepository;

@RestController
@RequestMapping("/rapport")
@CrossOrigin("*")
public class RapportController {
	@Autowired
	private RapportRepository rapportRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Rapport rapport) {
	this.rapportRepository.save(rapport);
	return "enregistrée avec succès";
	}
	//__________
	 @GetMapping("/afficherall")
	    public List<Rapport> afficher() {
	        return this.rapportRepository.findAll();
	    }
	 //___________________
	 @GetMapping("/affichagebyid/{id}")
	    public Rapport affichagebyid(@PathVariable Long id) {
	        return this.rapportRepository.getRapportById(id);
	    }

	 //____________________________
	 
	 @PutMapping("/modification/{id}")
	    public Rapport update(@PathVariable Long id, @RequestBody Rapport rapport) {
	        Rapport r = this.rapportRepository.findById(id).orElse(null);
	        if (r != null) {
	            rapport.setId(id);
	            return this.rapportRepository.saveAndFlush(rapport);
	        } else {
	            throw new RuntimeException("FAIL");
	        }
	    }

//_________________
	 
	 @DeleteMapping("/supprimer/{id}")
	    public List<Rapport> supprimer(@PathVariable Long id) {
	        this.rapportRepository.deleteById(id);
	        return this.rapportRepository.findAll();
	    }
	 
	 //_____________________
	 @PutMapping("/archiver/{id}")
	    public List<Rapport>  archiver(@PathVariable Long id) {
	    	Rapport r = this.rapportRepository.findById(id).get();
	    	r.setArchive(true);
	    	this.rapportRepository.saveAndFlush(r);
	    	return this.rapportRepository.findAll();

	    	
	    }
	 
	 
	 
	 //____________________
	 @GetMapping("/listencours") 
	    public List<Rapport> listeEnCours() {
	        return this.rapportRepository.findByArchiveIsFalse();
	    }
	 ///______________
	 @GetMapping("/listarchivee")
		public List<Rapport> listeArchivee() {
		    return this.rapportRepository.findByArchiveIsTrue();
		}
	 @PutMapping("/desarchiver")
	    public String desarchiver(Long id) {
	    	Rapport r = this.rapportRepository.findById(id).get();
	    	r.setArchive(false);
	    	this.rapportRepository.saveAndFlush(r);
	    	return "true";
	    }
}
