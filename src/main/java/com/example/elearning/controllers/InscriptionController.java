package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elearning.models.Administrateur;
import com.example.elearning.models.Candidat;
import com.example.elearning.models.ChefDeProjet;
import com.example.elearning.models.Formation;
import com.example.elearning.models.Inscription;
import com.example.elearning.repository.AdministrateurRepository;
import com.example.elearning.repository.CandidatRepository;
import com.example.elearning.repository.FormationRepository;
import com.example.elearning.repository.InscriptionRepository;

@RestController
@RequestMapping("/inscription")
@CrossOrigin("*")
public class InscriptionController {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	//@Autowired
	//private CandidatRepository candidatRepository;
	//@Autowired
	//private FormationRepository formationRepository;
	//@Autowired
	//private AdministrateurRepository administrateurRepository;
	

	//@PostMapping("/ajouter/{idformation}/{idcandidat}/{idadmin}")
	//public Inscription ajouter(@RequestBody Inscription inscription, @PathVariable Long idformation, @PathVariable Long idcandidat,@PathVariable Long idadmin) {
	   // Formation formation = formationRepository.findById(idformation).orElse(null);
	    //inscription.setFormation(formation);
	    //Candidat candidat = candidatRepository.findById(idcandidat).orElse(null);
	   // inscription.setCandidat(candidat);
	  // Administrateur administrateur = administrateurRepository.findById(idadmin).orElse(null);
	   // inscription.setAdmin(administrateur);

	   // return inscriptionRepository.save(inscription);
	//}
	
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody Inscription inscription) {
	this.inscriptionRepository.save(inscription);
	return "enregistrée avec succès";
	}
	
	 @GetMapping("/afficherall")
	    public List<Inscription> afficher() {
	        return this.inscriptionRepository.findAll();
	    }
}
