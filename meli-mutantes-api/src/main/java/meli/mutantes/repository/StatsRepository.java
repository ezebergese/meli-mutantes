package meli.mutantes.repository;

import org.springframework.data.repository.CrudRepository;

import meli.mutantes.controller.DnaAnalysisResultStats;

public interface StatsRepository extends CrudRepository<DnaAnalysisResultStats, Integer> {
}
