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

import com.example.elearning.models.ChefDeProjet;
import com.example.elearning.models.Planification;
import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.models.Specialite;
import com.example.elearning.repository.ChefDeProjetRepository;
import com.example.elearning.repository.ProjetFreelanceRepository;

@RestController
@RequestMapping("/projetfreelance")
@CrossOrigin("*")
public class ProjetFreelanceController {
	
	@Autowired
	private ProjetFreelanceRepository projetFreelanceRepository;
	
	@Autowired
    private ChefDeProjetRepository chefDeProjetRepository;

	
	
	@PostMapping("/ajouter/{idchef}")
	public ProjetFreelance ajouter(@RequestBody ProjetFreelance projetFreelance, @PathVariable Long idchef) {
	    ChefDeProjet chefDeProjet = chefDeProjetRepository.findById(idchef).orElse(null);
	    projetFreelance.setChefDeProjet(chefDeProjet);
	    return projetFreelanceRepository.save(projetFreelance);
	}
	
	 
	 @GetMapping("/aficherall")
	    public List<ProjetFreelance> afficher() {
	        return this.projetFreelanceRepository.findAll();
	    }
	 @GetMapping("/affichagebyid/{id}")
	    public ProjetFreelance affichagebyid(@PathVariable Long id) {
	        return this.projetFreelanceRepository.getProjetFreelanceById(id);
	    }

	 @PutMapping("/modification/{id}")
	    public ProjetFreelance update(@PathVariable Long id, @RequestBody ProjetFreelance projetFreelance) {
	        ProjetFreelance pf = this.projetFreelanceRepository.findById(id).orElse(null);
	        if (pf != null) {
	           
	        	projetFreelance.setId(id);
	            return this.projetFreelanceRepository.saveAndFlush(projetFreelance);
	        } else {
	            throw new RuntimeException("FAIL");
	        }   
	        
	    }
	
	 @DeleteMapping("/supprimer/{id}")
	    public  List<ProjetFreelance> supprimer(@PathVariable Long id) {
	    this.projetFreelanceRepository.deleteById(id);
	     return this.projetFreelanceRepository.findAll();
	    }
	 
	 //______________
	
	
	 @PutMapping("/archiver/{id}")
	    public List<ProjetFreelance>  archiver(@PathVariable 
	    		Long id) {
	    	ProjetFreelance pf = this.projetFreelanceRepository.findById(id).get();
	    	pf.setArchive(true);
	    	this.projetFreelanceRepository.saveAndFlush(pf);
	    	return this.projetFreelanceRepository.findAll();
	    	
	    }
	  
	
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /projet/listencours
    public List<ProjetFreelance> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.projetFreelanceRepository.findByArchiveIsFalse();
    }
	
	@GetMapping("/listarchivee")
	public List<ProjetFreelance> listeArchivee() {
	    // On récupère toutes les formations où "archive" est true (archivées)
	    return this.projetFreelanceRepository.findByArchiveIsTrue();
	}
    
	@PutMapping("/desarchiver/{id}")
    public List<ProjetFreelance> desarchiver(@PathVariable Long id) {
		ProjetFreelance p = this.projetFreelanceRepository.findById(id).get();
        p.setArchive(false);
        this.projetFreelanceRepository.saveAndFlush(p);
        return this.projetFreelanceRepository.findAll();
    }
	    

}
