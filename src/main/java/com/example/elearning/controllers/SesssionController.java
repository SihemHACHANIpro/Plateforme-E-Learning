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

import com.example.elearning.models.Formation;
import com.example.elearning.models.Programme;
import com.example.elearning.models.Session;
import com.example.elearning.repository.FormationRepository;
import com.example.elearning.repository.SessionRepository;

@RestController
@RequestMapping("/session")
@CrossOrigin("*")
public class SesssionController {
	
	@Autowired
	SessionRepository sessionRepository;
	@Autowired
	private FormationRepository formationRepository;	
	@PostMapping("/ajouter/{idformation}")
	public Session ajouter(@RequestBody Session session, @PathVariable Long idformation) {
	    Formation formation = formationRepository.findById(idformation).orElse(null);
	    session.setFormation(formation);
	    return sessionRepository.save(session);
	}

	
	
	@GetMapping("/aficherall")
	public List<Session>afficher(){
		return this.sessionRepository.findAll();
	}
	
	
	
	@GetMapping("affichagebyid/{id}")
	public Session affichagebyid(@PathVariable Long id) {
		return this.sessionRepository.getSessionById(id);
	}
	
	
	@PutMapping("/modification/{id}")
	public Session update(@PathVariable Long id, @RequestBody Session session) {
	    Session s = this.sessionRepository.findById(id).orElse(null);
	    if (s != null) {
	        session.setId(id);
	        return this.sessionRepository.saveAndFlush(session);
	    } else {
	        throw new RuntimeException("FAIL");
	    }
	}

	
	
	
	
	@DeleteMapping("/supprimer/{id}")
    public  List<Session> supprimer(@PathVariable Long id) {
        this.sessionRepository.deleteById(id);
        return this.sessionRepository.findAll();
    }
	 
	
	@PutMapping("/archiver/{id}")
	public List<Session> archiver(@PathVariable Long id) {
		Session s= this.sessionRepository.findById(id).get();
		s.setArchive(true);
		this.sessionRepository.saveAndFlush(s);
    	return this.sessionRepository.findAll();

	}
	
	
  


	
	@PutMapping("/desarchiver/{id}")
    public List<Session> desarchiver(@PathVariable Long id) {
		Session s = this.sessionRepository.findById(id).get();
        s.setArchive(false);
        this.sessionRepository.saveAndFlush(s);
        return this.sessionRepository.findAll();
    }
	
	
	
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Session> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.sessionRepository.findByArchiveIsFalse();
    }
	
	 @DeleteMapping("/supprimerarchiver/{id}")
	    public String supprimerFormationArchivee(@PathVariable Long id) {
		 Session s= this.sessionRepository.findById(id).orElse(null);
		 if (s != null && s.isArchive()) {
			 this.sessionRepository.deleteById(id);
			 return "Session archivée supprimée avec succès !";
		 }else if (s == null) {
			 return "Session non trouvée.";
		 } else {
	            // Si la formation n'est pas archivée
	            return " La session n'est pas archivée, elle ne peut pas être supprimée.";
	        }
	 }
	 
	 @GetMapping("/listarchivee")
		public List<Session> listeArchivee() {
		    // On récupère toutes les formations où "archive" est true (archivées)
		    return this.sessionRepository.findByArchiveIsTrue();
		}
	
}
