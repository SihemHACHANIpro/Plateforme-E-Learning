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

import com.example.elearning.models.Utilisateur;
import com.example.elearning.repository.UtilisateurRepository;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin("*")
public class UtilisateurController {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Utilisateur utilisateur ) {
		this.utilisateurRepository.save(utilisateur);
		return "enregistrée avec succès";
	}
	//_____________
	@GetMapping("/afficherall")
    public List<Utilisateur> afficher() {
        return this.utilisateurRepository.findAll();
    }
	//________________
	@GetMapping("/affichagebyid/{id}")
    public Utilisateur affichagebyid(@PathVariable Long id) {
        return this.utilisateurRepository.getUtilisateurById(id);
    }
	//________________
	@PutMapping("/modification/{id}")
	public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
	    Utilisateur u = this.utilisateurRepository.findById(id).orElse(null);
	    if (u != null) {
	        utilisateur.setId(id);
	        return this.utilisateurRepository.saveAndFlush(utilisateur);
	    } else {
	        throw new RuntimeException("FAIL");
	    }
	}
	//_____________________
	
	@DeleteMapping("/supprimer/{id}")
    public List<Utilisateur> supprimer(@PathVariable Long id) {
        this.utilisateurRepository.deleteById(id);
        return this.utilisateurRepository.findAll();
    }
	
	@PutMapping("/archiver/{id}")
    public List<Utilisateur> archiver(@PathVariable Long id) {
    	Utilisateur u = this.utilisateurRepository.findById(id).get();
    	u.setArchive(true);
    	this.utilisateurRepository.saveAndFlush(u);
    	return this.utilisateurRepository.findAll();

    	
    }
	
	
	
	//___________
	@GetMapping("/listencours") 
    public List<Utilisateur> listeEnCours() {
    return this.utilisateurRepository.findByArchiveIsFalse();
    }
	//______________
	@GetMapping("/listarchivee")
	public List<Utilisateur> listeArchivee() {
	 return this.utilisateurRepository.findByArchiveIsTrue();
	}
	
	@PutMapping("/desarchiver")
	public String desarchiver(Long id) {
	Utilisateur u =this.utilisateurRepository.findById(id).get();
		u.setArchive(false);
		this.utilisateurRepository.saveAndFlush(u);
		return "true";
	}
	
}
