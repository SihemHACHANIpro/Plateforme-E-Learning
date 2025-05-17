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

import com.example.elearning.models.Specialite;
import com.example.elearning.repository.SpecialiteRepository;

@RestController
@RequestMapping("/Specialite")
@CrossOrigin("*")
public class SpcialiteController {
	@Autowired
	SpecialiteRepository specialiteRepository;
	@PostMapping("/ajouter")
	
	public String ajouter(@RequestBody Specialite Specialite ) {
	this.specialiteRepository.save(Specialite);
	return"enregistrée avec succès";
	}
	
	
	@GetMapping("afficherall")
	public List<Specialite>afficher(){
		return this.specialiteRepository.findAll();
	}
	
	
	@GetMapping("affichagebyid/{id}")
	public Specialite affichagebyid(@PathVariable Long id) {
		return this.specialiteRepository.getSpecialiteById(id);
	}
	//___________
	
	
	@DeleteMapping("/supprimer/{id}")
    public  List<Specialite> supprimer(@PathVariable Long id) {
        this.specialiteRepository.deleteById(id);
        return this.specialiteRepository.findAll();
    }

	//__________________
	
	@PutMapping("/modification/{id}")
	public Specialite  update(@PathVariable Long id, @RequestBody Specialite specialite) {

		Specialite s=this.specialiteRepository.findById(id).orElse(null);
		if (s != null) {
			 specialite.setId(id);
			return this.specialiteRepository.saveAndFlush(specialite);	
		} else {
            throw new RuntimeException("FAIL");
        }
		
	}
	
	//__________________
	
	 @PutMapping("/archiver/{id}")
	    public List<Specialite>  archiver(@PathVariable 
	    		Long id) {
		 Specialite s = this.specialiteRepository.findById(id).get();
	    	s.setArchive(true);
	    	this.specialiteRepository.saveAndFlush(s);
	    	return this.specialiteRepository.findAll();
	    	
	    }
	  
	
	
	
	
	@GetMapping("/listencours")
	public List<Specialite>listencours(){
		return this.specialiteRepository.findByArchiveIsFalse();
	}
	
	@PutMapping("/desarchiver")
	public String desarchiver(Long id) {
		Specialite ps =this.specialiteRepository.findById(id).get();
		ps.setArchive(false);
		this.specialiteRepository.saveAndFlush(ps);
		return "true";
	}
	
	
	
	@DeleteMapping("/supprimerarchiver/{id}")
	public String supprimerSpecialiteArchiver(@PathVariable Long id) {

	    Specialite sp = this.specialiteRepository.findById(id).orElse(null);
	    if (sp != null && sp.isArchive()) {
	        this.specialiteRepository.deleteById(id);
	        return "Specialite archivée supprimée avec succès !";
	    } else if (sp == null) {
	        return "Specialite non trouvée.";
	    } else {
	        // Si la formation n'est pas archivée
	        return "La Specialite n'est pas archivée, elle ne peut pas être supprimée.";
	    }

	}
	@GetMapping("/listarchivee")
	public List<Specialite> listeArchivee() {
	    // On récupère toutes les formations où "archive" est true (archivées)
	    return this.specialiteRepository.findByArchiveIsTrue();
	}
	}


