package com.example.elearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.elearning.models.ProjetFreelance;
import com.example.elearning.repository.ProjetFreelanceRepository;

@RestController
@RequestMapping("/projetfreelance")
public class ProjetFreelanceController {
	@Autowired
	private ProjetFreelanceRepository projetFreelanceRepository;
	@PostMapping("/ajouter")
	public String ajouter(@RequestBody ProjetFreelance projetfreelance) {
		this.projetFreelanceRepository.save(projetfreelance);
		return "enregistrée avec succès";
	}
	 
	 @GetMapping("/aficherall")
	    public List<ProjetFreelance> afficher() {
	        return this.projetFreelanceRepository.findAll();
	    }

	

}
