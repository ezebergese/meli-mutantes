package meli.mutantes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import meli.mutantes.model.dna.DNAAnalysisResult;

@Repository
public interface DNAAnalysisResultRepository extends CrudRepository<DNAAnalysisResult, String> {
}