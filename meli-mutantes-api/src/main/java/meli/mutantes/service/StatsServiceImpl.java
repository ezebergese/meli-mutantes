package meli.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meli.mutantes.controller.DnaAnalysisResultStats;
import meli.mutantes.repository.StatsRepository;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsRepository repository;

	public DnaAnalysisResultStats getStats() {
		return repository.findOne(1);
	}
}
