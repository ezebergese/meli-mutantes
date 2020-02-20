package meli.mutantes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class HealthCheckController {

	@GetMapping
	public String getStatus() {
		return "OK";
	}
}
