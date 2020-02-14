package meli.mutantes;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DNATest {
	
    @Test
    public void givenAMutantDNASequence_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
    
    @Test
    public void givenANonMutantDNASequence_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
}
