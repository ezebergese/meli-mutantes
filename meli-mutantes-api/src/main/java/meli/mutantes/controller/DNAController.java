package meli.mutantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import meli.mutantes.service.MutantService;

@RestController
@RequestMapping("/mutant")
public class DNAController {
	
	@Autowired
	private MutantService mutantService;
	
	
	@PostMapping
	public ResponseEntity<String> isMutant(@RequestBody DNAPayload dnaPayload) {
		if (mutantService.isMutant(dnaPayload.getDna()))
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
}
