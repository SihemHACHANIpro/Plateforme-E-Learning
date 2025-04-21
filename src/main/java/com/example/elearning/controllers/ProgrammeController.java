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


import com.example.elearning.models.Programme;
import com.example.elearning.repository.ProgrammeRepository;

@RestController
@RequestMapping("/programme")
public class ProgrammeController {
	@Autowired
	private ProgrammeRepository programmeRepository;
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Programme programme) {
		this.programmeRepository.save(programme);
		return "enregistrée avec succès";
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
    public String supprimer(@PathVariable Long id) {
        this.programmeRepository.deleteById(id);
        return "supprimée avec succès";
    }
	
	@PutMapping("/archiver")
    public String archiver(Long id) {
    	Programme p = this.programmeRepository.findById(id).get();
    	p.setArchive(true);
    	this.programmeRepository.saveAndFlush(p);
    	return "true" ;
    	
    }
	
	@PutMapping("/desarchiver")
	public String desarchiver(Long id) {
		Programme p =this.programmeRepository.findById(id).get();
		p.setArchive(false);
		this.programmeRepository.saveAndFlush(p);
		return "true";
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
    


