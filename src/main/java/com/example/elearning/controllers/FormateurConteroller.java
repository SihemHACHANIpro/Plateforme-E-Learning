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

import com.example.elearning.models.Formateur;
import com.example.elearning.repository.FormateurRepository;

@RestController
@RequestMapping("/formateur")
@CrossOrigin("*")
public class FormateurConteroller {

    @Autowired
    private FormateurRepository formateurRepository;

    
    
    
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouter(@RequestBody Formateur formateur) {
        Formateur saved = this.formateurRepository.save(formateur);
        return ResponseEntity.ok(saved); // ✅ on retourne l'objet sauvegardé (en JSON)
    }

       
    
    //@PostMapping("/ajouter")
    //public String ajouter(@RequestBody Formateur formateur) {
       // this.formateurRepository.save(formateur);
       // return "enregistrée avec succès";
    //}
    
  //________________
    
    @GetMapping("/aficherall")
    public List<Formateur> afficher() {
        return this.formateurRepository.findAll();
    }
  //__________________
    @GetMapping("/affichagebyid/{id}")
    public Formateur affichagebyid(@PathVariable Long id) {
        return this.formateurRepository.getFormateurById(id);
    }

   //___________
    
    @DeleteMapping("/supprimer/{id}")
    public List<Formateur> supprimer(@PathVariable Long id) {
        this.formateurRepository.deleteById(id);
        return this.formateurRepository.findAll();
    }
   //_______________
    
    @PutMapping("/modification/{id}")
    public Formateur update(@PathVariable Long id, @RequestBody Formateur formateur) {
        Formateur fr = this.formateurRepository.findById(id).orElse(null);
        if (fr != null) {
            formateur.setId(id);
            return this.formateurRepository.saveAndFlush(formateur);
        } else {
            throw new RuntimeException("FAIL");
        } 
    }
    
    @PutMapping("/archiver/{id}")
    public List<Formateur> archiver(@PathVariable Long id) {
    	Formateur f = this.formateurRepository.findById(id).get();
    	f.setArchive(true);
    	this.formateurRepository.saveAndFlush(f);
    	return this.formateurRepository.findAll();
    	
    }
    
    //___________
    
    @GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Formateur> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.formateurRepository.findByArchiveIsFalse();
    }
    
    

    
    
}
