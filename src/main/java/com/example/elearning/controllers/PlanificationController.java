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

 
import com.example.elearning.models.Planification;
import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.models.Rapport;
import com.example.elearning.repository.PlanificationRepository;
import com.example.elearning.repository.ProjetFreelanceRepository;

@RestController
@RequestMapping("/Planification")
@CrossOrigin("*")
public class PlanificationController {
	
	@Autowired
	private PlanificationRepository planificationRepository;
	@Autowired
	private ProjetFreelanceRepository projetFreelanceRepository;
	
	
	@PostMapping("/ajouter/{idprojet}")
	public Planification ajouter(@RequestBody Planification planification, @PathVariable Long idprojet) {
	    ProjetFreelance projetFreelance = projetFreelanceRepository.findById(idprojet).orElse(null);
	    planification.setProjetFreelance(projetFreelance);
	    return planificationRepository.save(planification);
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
	    public  List<Planification> supprimer(@PathVariable Long id) {
	        this.planificationRepository.deleteById(id);
	        return this.planificationRepository.findAll();
	    }
	 //__________
	 @PutMapping("/archiver/{id}")
	    public List<Planification>  archiver(@PathVariable 
	    		Long id) {
	    	Planification p = this.planificationRepository.findById(id).get();
	    	p.setArchive(true);
	    	this.planificationRepository.saveAndFlush(p);
	    	return this.planificationRepository.findAll();
	    	
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
	 
	 @PutMapping("/desarchiver/{id}")
	    public List<Planification> desarchiver(@PathVariable Long id) {
			Planification p = this.planificationRepository.findById(id).get();
	        p.setArchive(false);
	        this.planificationRepository.saveAndFlush(p);
	        return this.planificationRepository.findAll();
	    }
		    

	    
	    
	 

}
