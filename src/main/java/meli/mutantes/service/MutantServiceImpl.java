package meli.mutantes.service;


import org.springframework.stereotype.Service;

import meli.mutantes.model.dna.DNA;

@Service
public class MutantServiceImpl implements MutantService {

	public boolean isMutant(String[] dnaSequence) {
		return new DNA(dnaSequence).isMutant();
	}
}
