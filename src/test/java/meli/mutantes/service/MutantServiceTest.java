package meli.mutantes.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MutantServiceTest {

	private MutantService mutantService;
	
	
	@Before
	public void setup() {
		mutantService = new MutantServiceImpl();
	}
	
	@Test
	public void givenAMutantSequence_isMutant_shouldReturnTrue() {
		String[] mutantSequence = new String[] {"AAAAGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCGGGG"};
		assertThat(mutantService.isMutant(mutantSequence), is(true));
	}
	
	@Test
	public void givenANonMutantSequence_isMutant_shouldReturnFalse() {
		String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
		assertThat(mutantService.isMutant(mutantSequence), is(false));
	}
}
