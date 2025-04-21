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

 
import com.example.elearning.models.Planification;
import com.example.elearning.repository.PlanificationRepository;

@RestController
@RequestMapping("/Planification")
public class PlanificationController {
	
	@Autowired
	private PlanificationRepository planificationRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Planification planification) {
	this.planificationRepository.save(planification);
	return "enregistrée avec succès";
	}
	//______________
	
	 @GetMapping("/afficherall")
	    public List<Planification> afficher() {
	        return this.planificationRepository.findAll();
	    }
	 //____________
	 @GetMapping("/affichagebyid/{id}")
	    public Planification affichagebyid(@PathVariable Long id) {
	        return this.planificationRepository.getPlanificationById(id);
	    }
	 //__________________
	 
	 @PutMapping("/modification/{id}")
	    public Planification update(@PathVariable Long id, @RequestBody Planification planification) {
	        Planification pn = this.planificationRepository.findById(id).orElse(null);
	        if (pn != null) {
	            planification.setId(id);
	            return this.planificationRepository.saveAndFlush(planification);
	        } else {
	            throw new RuntimeException("FAIL");
	        }
	    }
	 //______________________
	 @DeleteMapping("/supprimer/{id}")
	    public String supprimer(@PathVariable Long id) {
	        this.planificationRepository.deleteById(id);
	        return "supprimée avec succès";
	    }
	 //__________
	 @PutMapping("/archiver")
	    public String archiver(Long id) {
	    	Planification pn = this.planificationRepository.findById(id).get();
	    	pn.setArchive(true);
	    	this.planificationRepository.saveAndFlush(pn);
	    	return "true" ;
	    	
	    }
	 ///______________________
	 @GetMapping("/listencours") 
	    public List<Planification> listeEnCours() {     
	        return this.planificationRepository.findByArchiveIsFalse();
	        
	    }
	 //_________________
	 
	 @GetMapping("/listarchivee")
		public List<Planification> listeArchivee() {
		     //On récupère toutes les formations où "archive" est true (archivées)
		   return this.planificationRepository.findByArchiveIsTrue();
		}
	 //_____________________
	 
	 @PutMapping("/desarchiver")
	    public String desarchiver(Long id) {
	    	Planification pn = this.planificationRepository.findById(id).get();
	    	pn.setArchive(false);
	    	this.planificationRepository.saveAndFlush(pn);
	    	return "true";
	    }
	    
	    
	 

}
