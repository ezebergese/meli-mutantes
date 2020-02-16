package meli.mutantes;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MatrixCacheTest {
	
	private void addSixDifferentElements(MatrixCache<String> cache) {
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
	}
	
	@Test
	public void givenACacheOfSize6_whenAddingAnElement_shouldReturnZeroSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6_whenAddingAnElementThreeTimes_shouldReturnZeroSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("A");
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6_whenAddingAnElementFourTimes_shouldReturnOneSequence() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("A");
		cache.addElement("A");
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenACacheOfSize6_whenAddingAnElementFiveTimes_shouldReturnOneSequence() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("A");
		cache.addElement("A");
		cache.addElement("A");
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenACacheOfSize6WithACompleteFirstRow_whenAddingAnElement_shouldReturnZeroSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		addSixDifferentElements(cache);
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6WithACompleteFirstRow_whenAddingAnElementThreeTimes_shouldReturnZeroSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		addSixDifferentElements(cache);
		cache.addElement("A");
		cache.addElement("A");
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6WithThreeEqualValuesInTheFirstColumn_whenAddingAnElementInTheFirstColumnWithTheSameValue_shouldReturnOneSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		addSixDifferentElements(cache);
		addSixDifferentElements(cache);
		addSixDifferentElements(cache);
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenACacheOfSize6WithThreeEqualValuesInTheFirstColumn_whenAddingAnElementInTheFirstColumnWithADifferentValue_shouldReturnOneSequences() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		addSixDifferentElements(cache);
		addSixDifferentElements(cache);
		addSixDifferentElements(cache);
		
		int numberOfCompletedSequences = cache.addElement("B");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6WithANWSEDiagonalThatHasThreeEqualValues_whenAddingAnElementWithEqualValueInTheSameDiagonal_shouldReturnOneSequence() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
		
		cache.addElement("F");
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		
		cache.addElement("E");
		cache.addElement("F");
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
		
		int numberOfCompletedSequences = cache.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenACacheOfSize6WithANWSEDiagonalThatHasThreeEqualValues_whenAddingAnElementWithDifferentValueInTheSameDiagonal_shouldReturnZeroSequence() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
		
		cache.addElement("F");
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		
		cache.addElement("E");
		cache.addElement("F");
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
		
		int numberOfCompletedSequences = cache.addElement("B");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenACacheOfSize6WithANESWDiagonalThatHasThreeEqualValues_whenAddingAnElementWithEqualValueInTheSameDiagonal_shouldReturnOneSequence() {
		MatrixCache<String> buffer = new MatrixCache<String>(6);
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
		
		buffer.addElement("E");
		buffer.addElement("D");
		buffer.addElement("C");
		buffer.addElement("B");
		buffer.addElement("F");
		buffer.addElement("A");
		
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("F");
		buffer.addElement("D");
		buffer.addElement("E");
		
		buffer.addElement("D");
		buffer.addElement("E");
		
		int numberOfCompletedSequences = buffer.addElement("F");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenACacheOfSize6WithANESWDiagonalThatHasThreeEqualValues_whenAddingAnElementWithDifferentValueInTheSameDiagonal_shouldReturnOneSequence() {
		MatrixCache<String> cache = new MatrixCache<String>(6);
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("D");
		cache.addElement("E");
		cache.addElement("F");
		
		cache.addElement("E");
		cache.addElement("D");
		cache.addElement("C");
		cache.addElement("B");
		cache.addElement("F");
		cache.addElement("A");
		
		cache.addElement("A");
		cache.addElement("B");
		cache.addElement("C");
		cache.addElement("F");
		cache.addElement("D");
		cache.addElement("E");
		
		cache.addElement("D");
		cache.addElement("E");
		
		int numberOfCompletedSequences = cache.addElement("Q");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
}
