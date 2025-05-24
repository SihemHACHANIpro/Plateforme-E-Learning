package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.elearning.models.Candidat;
import com.example.elearning.models.ChefDeProjet;
import com.example.elearning.models.Formateur;
import com.example.elearning.repository.ChefDeProjetRepository;

@RestController
@RequestMapping("/chefdeprojet")
@CrossOrigin("*")
public class ChefDeProjetController {

    @Autowired
    private ChefDeProjetRepository chefDeProjetRepository;

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouter(@RequestBody ChefDeProjet chefDeProjet) {
       ChefDeProjet saved=this.chefDeProjetRepository.save(chefDeProjet);
        return ResponseEntity.ok(saved);
    }
    
    
    //_________________
    @GetMapping("/afficherall")
    public List<ChefDeProjet> afficher() {
        return this.chefDeProjetRepository.findAll();
    }
    //_____________
    
    @GetMapping("/affichagebyid/{id}")
    public ChefDeProjet affichagebyid(@PathVariable Long id) {
        return this.chefDeProjetRepository.getChefDeProjetById(id);
    }
    //____________________
    
    @PutMapping("/modification/{id}")
    public ChefDeProjet update(@PathVariable Long id, @RequestBody ChefDeProjet chefdedrojet) {
    	ChefDeProjet ch = this.chefDeProjetRepository.findById(id).orElse(null);
        if (ch != null) {
            chefdedrojet.setId(id);
            return this.chefDeProjetRepository.saveAndFlush(chefdedrojet);
        } else {
            throw new RuntimeException("FAIL");
        }
    }
    
    
    //____________________
    @DeleteMapping("/supprimer/{id}")
    public List<ChefDeProjet> supprimer(@PathVariable Long id) {
        this.chefDeProjetRepository.deleteById(id);
        return this.chefDeProjetRepository.findAll();
    }
    //_____________________
    
    @PutMapping("/archiver/{id}")
    public List<ChefDeProjet> archiver(@PathVariable Long id) {
    	ChefDeProjet ch = this.chefDeProjetRepository.findById(id).get();
    	ch.setArchive(true);
    	this.chefDeProjetRepository.saveAndFlush(ch);
    	return this.chefDeProjetRepository.findAll();
    	
    }

    @PutMapping("/desarchiver/{id}")
    public List<ChefDeProjet> desarchiver(@PathVariable Long id) {
    	ChefDeProjet ch = this.chefDeProjetRepository.findById(id).get();
        ch.setArchive(false);
        this.chefDeProjetRepository.saveAndFlush(ch);
        return this.chefDeProjetRepository.findAll();
    }
 
    
}
