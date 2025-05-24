package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.elearning.models.Candidat;
import com.example.elearning.models.Formation;
import com.example.elearning.repository.CandidatRepository;


@RestController
@RequestMapping("/candidat")
@CrossOrigin("*")
public class CandidatsControllers {
	
	@Autowired
	private CandidatRepository candidatRepository;
	
	@PostMapping("/ajouter")
	public ResponseEntity<?> ajouter(@RequestBody Candidat candidat) {
	    Candidat saved = candidatRepository.save(candidat);
	    return ResponseEntity.ok(saved);
	}

	//
	@GetMapping("/afficherall")
    public List<Candidat> afficher() {
        return this.candidatRepository.findAll();
    }
	
	@GetMapping("/affichagebyid/{id}")
    public Candidat affichagebyid(@PathVariable Long id) {
        return this.candidatRepository.getCandidatById(id);
    }
	
	@PutMapping("/modification/{id}")
    public Candidat update(@PathVariable Long id, @RequestBody Candidat candidat) {
        Candidat c = this.candidatRepository.findById(id).orElse(null);
        if (c != null) {
            candidat.setId(id);
            return this.candidatRepository.saveAndFlush(candidat);
        } else {
            throw new RuntimeException("FAIL");
        }
    }
	
	//____________________
	@DeleteMapping("/supprimer/{id}")
    public List<Candidat> supprimer(@PathVariable Long id) {
        this.candidatRepository.deleteById(id);
        return this.candidatRepository.findAll();
    }

	//_________
	
	@PutMapping("/archiver/{id}")
    public List<Candidat> archiver(@PathVariable Long id) {
    	Candidat c = this.candidatRepository.findById(id).get();
    	c.setArchive(true);
    	this.candidatRepository.saveAndFlush(c);
    	return this.candidatRepository.findAll();
    	
    }
	
	@PutMapping("/desarchiver/{id}")
    public List<Candidat> desarchiver(@PathVariable Long id) {
		Candidat c = this.candidatRepository.findById(id).get();
        c.setArchive(false);
        this.candidatRepository.saveAndFlush(c);
        return this.candidatRepository.findAll();
    }

}





