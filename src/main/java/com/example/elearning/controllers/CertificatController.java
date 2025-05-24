package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.elearning.models.Certificat;
import com.example.elearning.models.Evenement;
import com.example.elearning.models.Postuler;
import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.repository.CandidatRepository;
import com.example.elearning.repository.CerificatRepository;

@RestController
@RequestMapping("/certificat")
@CrossOrigin("*")
public class CertificatController {
	
	@Autowired
	private  CerificatRepository certificatRepository;
	@Autowired
	private CandidatRepository candidatRepository;
	
	@PostMapping("/ajouter")
	   public String ajouter(@RequestBody Certificat certificat) {
		this.certificatRepository.save(certificat);
		return "enregistrée avec succès";
	}
	@PostMapping("/ajouter/{idcandidat}")
    public Certificat ajouter(@RequestBody Certificat certificat,@PathVariable Long idcandidat) {
     Candidat candidat=candidatRepository.findById(idcandidat).orElse(null);
      certificat.setCandidat(candidat);
      return certificatRepository.save(certificat);
  }
	
	
	
	@GetMapping("/aficherall")
    public List<Certificat> afficher() {
        return this.certificatRepository.findAll();
    }
	
//_____
	@GetMapping("/affichagebyid/{id}")
    public Certificat affichagebyid(@PathVariable Long id) {
        return this.certificatRepository.getCertificatById(id);
    }
	
	//_______________
	
	@PutMapping("/modification/{id}")
	public Certificat update(@PathVariable Long id, @RequestBody Certificat certificat) {
	    Certificat c = this.certificatRepository.findById(id).orElse(null);
	    if (c != null) {
	        certificat.setId(id);
	        return this.certificatRepository.saveAndFlush(certificat);
	    } else {
	        throw new RuntimeException("FAIL");
	    }
	}
	
	//______________
	
	@DeleteMapping("/supprimer/{id}")
    public List<Certificat> supprimer(@PathVariable Long id) {
        this.certificatRepository.deleteById(id);
        return this.certificatRepository.findAll();
    }
	
	
	
	
	@PutMapping("/archiver/{id}")
    public List<Certificat> archiver(@PathVariable Long id) {
    	Certificat c = this.certificatRepository.findById(id).get();
    	c.setArchive(true);
    	this.certificatRepository.saveAndFlush(c);
    	return this.certificatRepository.findAll();
	}
	

	@PutMapping("/desarchiver/{id}")
    public List<Certificat> desarchiver(@PathVariable Long id) {
		Certificat e = this.certificatRepository.findById(id).get();
        e.setArchive(false);
        this.certificatRepository.saveAndFlush(e);
        return this.certificatRepository.findAll();
    }	///____________________
	
	
	
	///____________________
	@GetMapping("/listencours") // Le chemin pour appeler cette méthode est /formation/listencours
    public List<Certificat> listeEnCours() {
        // On récupère toutes les formations où "archive" est false (pas archivées)
        return this.certificatRepository.findByArchiveIsFalse();
    }
	
	
	    
}
