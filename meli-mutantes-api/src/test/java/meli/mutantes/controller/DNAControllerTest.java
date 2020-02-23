package meli.mutantes.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import meli.mutantes.service.MutantService;

@RunWith(SpringRunner.class)
@WebMvcTest(DNAController.class)
public class DNAControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MutantService mutantService;
	
	
	@Test
	public void givenAMutantSequence_whenPostMutant_shouldReturn200() throws Exception {
		given(mutantService.isMutant(any())).willReturn(true);
		mockMvc.perform(
				post("/mutant")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\n" + 
							"    \"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" + 
							"}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void givenANonMutantSequence_whenPostMutant_shouldReturn403() throws Exception {
		given(mutantService.isMutant(any())).willReturn(false);
		mockMvc.perform(
				post("/mutant")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\n" + 
							"    \"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"CGCTCA\",\"TCACTG\"]\n" + 
							"}"))
				.andExpect(status().isForbidden());
	}
}
