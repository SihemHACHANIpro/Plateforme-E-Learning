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

import com.example.elearning.models.Candidat;
import com.example.elearning.models.Formateur;
import com.example.elearning.models.Formation;
import com.example.elearning.models.Programme;
import com.example.elearning.repository.FormationRepository;
import com.example.elearning.repository.ProgrammeRepository;

@RestController
@RequestMapping("/programme")
@CrossOrigin("*")
public class ProgrammeController {
	@Autowired
	private ProgrammeRepository programmeRepository;
	
	@Autowired
	private FormationRepository formationRepository;

	
	@PostMapping("/ajouter/{idformation}")
	public Programme ajouter(@RequestBody Programme programme, @PathVariable Long idformation) {
	    Formation formation = formationRepository.findById(idformation).orElse(null);
	    programme.setFormation(formation);
	    return programmeRepository.save(programme);
	}


	   


	
	@GetMapping("/aficherall")
    public List<Programme> afficher() {
        return this.programmeRepository.findAll();
    }
	
	@GetMapping("/affichagebyid/{id}")
    public Programme affichagebyid(@PathVariable Long id) {
        return this.programmeRepository.getProgrammeById(id);
    }
	
	
	@PutMapping("/modification/{id}")
	public Programme update(@PathVariable Long id, @RequestBody Programme programme) {
	    Programme p = this.programmeRepository.findById(id).orElse(null);
	    if (p != null) {
	        programme.setId(id);
	        return this.programmeRepository.saveAndFlush(programme);
	    } else {
	        throw new RuntimeException("FAIL");
	    }
	}
	
	
	
	@DeleteMapping("/supprimer/{id}")
    public  List<Programme> supprimer(@PathVariable Long id) {
        this.programmeRepository.deleteById(id);
        return this.programmeRepository.findAll();
    }
	
	@PutMapping("/archiver/{id}")
    public List<Programme>  archiver(@PathVariable 
    		Long id) {
    	Programme p = this.programmeRepository.findById(id).get();
    	p.setArchive(true);
    	this.programmeRepository.saveAndFlush(p);
    	return this.programmeRepository.findAll();
    	
    }
	
	
	
	
	@PutMapping("/desarchiver/{id}")
    public List<Programme> desarchiver(@PathVariable Long id) {
		Programme p = this.programmeRepository.findById(id).get();
        p.setArchive(false);
        this.programmeRepository.saveAndFlush(p);
        return this.programmeRepository.findAll();
    }
	//_____________________
	@DeleteMapping("/supprimerarchiver/{id}")
    public String supprimerProgrammeArchivee(@PathVariable Long id) {
        // Recherche la formation par son ID
        Programme p = this.programmeRepository.findById(id).orElse(null);

        // Si la formation existe et est archivée
        if (p != null && p.isArchive()) {
            // Supprimer la formation de la base de données
            this.programmeRepository.deleteById(id);
            return "Le programme archivée supprimée avec succès !";
        } else if (p == null) {
            // Si la formation n'est pas trouvée
            return "le programme non trouvée.";
        } else {
            // Si la formation n'est pas archivée
            return "Le proramme n'est pas archivée, il ne peut pas être supprimée.";
        }
    }
    

	
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Programme> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.programmeRepository.findByArchiveIsFalse();
    }
	
	@GetMapping("/listarchivee")
	public List<Programme> listeArchivee() {
	    // On récupère toutes les formations où "archive" est true (archivées)
	    return this.programmeRepository.findByArchiveIsTrue();
	}

	    
}
    


