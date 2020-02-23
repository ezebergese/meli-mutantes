package meli.mutantes.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import meli.mutantes.model.dna.DNA;
import meli.mutantes.model.dna.DNAAnalysisResult;
import meli.mutantes.repository.DNAAnalysisResultRepository;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

	private MutantService mutantService;
	
	@Mock
	DNAAnalysisResultRepository repository;
	
	@Before
	public void setup() {
		mutantService = new MutantServiceImpl(repository);
	}
	
	@Test
	public void givenAMutantSequence_isMutant_shouldReturnTrue() {
		String[] mutantSequence = new String[] {"AAAAGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCGGGG"};
		given(repository.save(any(DNAAnalysisResult.class)))
			.willReturn(new DNAAnalysisResult(new DNA(mutantSequence), true));
		assertThat(mutantService.isMutant(mutantSequence), is(true));
	}
	
	@Test
	public void givenANonMutantSequence_isMutant_shouldReturnFalse() {
		String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
		given(repository.save(any(DNAAnalysisResult.class)))
		.willReturn(new DNAAnalysisResult(new DNA(mutantSequence), false));
		assertThat(mutantService.isMutant(mutantSequence), is(false));
	}
}
