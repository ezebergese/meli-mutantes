package meli.mutantes.model.dna;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class DNATest {
	
	private String[] createSequenceFromFile(String fileName) {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			return br.lines().toArray(String[]::new);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test(expected = InvalidSequenceException.class)
    public void givenAnInvalidSequence_whenVerifingIfIsMutant_shouldThrowInvalidSequenceException() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACT"};
    	DNA dna = new DNA(mutantSequence);
    	
    	dna.isMutant();
	}
	
	@Test
    public void givenAnSequenceOfSizeTwo_whenVerifingIfIsMutant_shouldThrowInvalidSequenceException() {
    	String[] mutantSequence = new String[] {"AT","CA"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
	}
    
	@Test
    public void givenASequenceOfSizeSixWithNoPatterns_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
	
	@Test
    public void givenASequenceOfSizeSixWithOnePattern_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = new String[] {"AAAAGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
	
	@Test
    public void givenASequenceOfSizeSixWithTwoPatterns_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = new String[] {"AAAAGA","CAGTGC","TTATTT","AGACGG","CGCTCA","TCGGGG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
	
    @Test
    public void givenASequenceOfSizeSixWithThreePatterns_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
    
    @Test
    public void givenASequenceOfSize1536WithNoPatterns_whenVerifingIfIsMutant_shouldReturnFalse() {
    	String[] mutantSequence = createSequenceFromFile("src/test/resources/sequence/nonmutant.txt");
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(false));
    }
    
    @Test
    public void givenAMutantDNASequenceOfSize1536With2Patterns_whenVerifingIfIsMutant_shouldReturnTrue() {
    	String[] mutantSequence = createSequenceFromFile("src/test/resources/sequence/mutant.txt");
    	DNA dna = new DNA(mutantSequence);
    	
    	assertThat(dna.isMutant(), is(true));
    }
}
