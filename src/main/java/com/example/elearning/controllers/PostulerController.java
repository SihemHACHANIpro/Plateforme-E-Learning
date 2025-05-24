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
import com.example.elearning.models.Candidat;
import com.example.elearning.models.Evenement;
import com.example.elearning.models.Postuler;
import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.repository.CandidatRepository;
import com.example.elearning.repository.PostulerRepository;
import com.example.elearning.repository.ProjetFreelanceRepository;

@RestController
@RequestMapping("/postuler")
@CrossOrigin("*")
public class PostulerController {
	
	@Autowired
	private PostulerRepository postulerRepository;
	@Autowired
	private CandidatRepository candidatRepository;
	@Autowired
	private ProjetFreelanceRepository projetFreelanceRepository;
	
	
	
	@PostMapping("/ajouter/{idprojet}/{idcandidat}")
      public Postuler ajouter(@RequestBody Postuler postuler,@PathVariable Long idprojet,@PathVariable Long idcandidat) {
       ProjetFreelance projetFreelance=projetFreelanceRepository.findById(idprojet).orElse(null);
      postuler.setProjet(projetFreelance); 
       Candidat candidat=candidatRepository.findById(idcandidat).orElse(null);
        postuler.setCandidat(candidat);
        return postulerRepository.save(postuler);
    }
	
	
	
	
	//__________________
	
		@GetMapping("/aficherall")
	    public List<Postuler> afficher() {
	        return this.postulerRepository.findAll();
	    }
		//______________________
		
		@GetMapping("/affichagebyid/{id}")
	    public Postuler affichagebyid(@PathVariable Long id) {
	        return this.postulerRepository.getPostulerById(id);
	    }
		
		
		//____
		
		@DeleteMapping("/supprimer/{id}")
	    public List<Postuler> supprimer(@PathVariable Long id) {
	        this.postulerRepository.deleteById(id);
	        return this.postulerRepository.findAll();
	    }
		
		@PutMapping("/archiver/{id}")
	    public List<Postuler> archiver(@PathVariable Long id) {
	    	Postuler p = this.postulerRepository.findById(id).get();
	    	p.setArchive(true);
	    	this.postulerRepository.saveAndFlush(p);
	    	return this.postulerRepository.findAll();
		}
		
		
		
		///____________________
		
		@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
	    public List<Postuler> listeEnCours() {
	        // On récupère toutes les formations où "archive" est false (pas archivées)
	        return this.postulerRepository.findByArchiveIsFalse();
	    }
		
		
		@PutMapping("/modification/{id}")
		public Postuler update(@PathVariable Long id, @RequestBody Postuler postuler) {
		    Postuler p = this.postulerRepository.findById(id).orElse(null);
		    if (p != null) {
		       postuler.setId(id);
		        return this.postulerRepository.saveAndFlush(postuler);
		    } else {
		        throw new RuntimeException("FAIL");
		    }    
		    
		}
		
		@PutMapping("/desarchiver/{id}")
	    public List<Postuler> desarchiver(@PathVariable Long id) {
			Postuler e = this.postulerRepository.findById(id).get();
	        e.setArchive(false);
	        this.postulerRepository.saveAndFlush(e);
	        return this.postulerRepository.findAll();
	    }	///____________________
		
		
		
}
