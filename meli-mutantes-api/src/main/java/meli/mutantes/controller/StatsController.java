package meli.mutantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import meli.mutantes.service.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {
	
	@Autowired
	private StatsService service;

	@GetMapping
	public DnaAnalysisResultStats getStats() {
		return service.getStats();
	}
}
