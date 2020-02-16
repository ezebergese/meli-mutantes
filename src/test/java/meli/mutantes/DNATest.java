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
    public void givenAMutantDNASequence2_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = new String[] {"AAAACC","CCCCGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
    
    @Test
    public void givenANonMutantDNASequence_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
    
    @Test
    public void givenANonMutantDNASequenceOfSize12_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {
    			"ATGCGAATGCGA",
    			"CAGTGCCAGTGC",
    			"TTATTATTATTA",
    			"AGACGCAGACGC",
    			"CGCTCACGCTCA",
    			"TCACTGTCACTG",
    			"ATGCGAATGCGA",
    			"CAGTGCCAGTGC",
    			"TTATTATTATTA",
    			"AGACGCAGACGC",
    			"CGCTCACGCTCA",
    			"TCACTGTCACTG"
    			};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
    
    @Test
    public void givenANonMutantDNASequenceOfSize24_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {
    		    "ATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTG"
    			};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
    
    @Test
    public void givenANonMutantDNASequenceOfSize48_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {
    			"ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG"
    			};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
    
    @Test
    public void givenAMutantDNASequenceOfSize48_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = new String[] {
    			"ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGAAAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTTTTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
    		    "ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
    		    "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
    		    "TTATTATTATTATTATTATTATTATTATTATTATTATTATTATTATTA",
    		    "CGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCACGCTCA",
    		    "AGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGCAGACGC",
    		    "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTGTCACTG"
    			};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
}
