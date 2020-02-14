package meli.mutantes;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BufferTest {

	private void addSixDifferentElements(Buffer<String> buffer) {
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
	}

	@Test
	public void givenA6x6Buffer_whenAddingAnElement_shouldReturnZeroSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6Buffer_whenAddingAnElementThreeTimes_shouldReturnZeroSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		buffer.addElement("A");
		buffer.addElement("A");
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6Buffer_whenAddingAnElementFourTimes_shouldReturnOneSequence() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		buffer.addElement("A");
		buffer.addElement("A");
		buffer.addElement("A");
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenA6x6BufferWithACompleteFirstRow_whenAddingAnElement_shouldReturnZeroSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		addSixDifferentElements(buffer);
		
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6BufferWithACompleteFirstRow_whenAddingAnElementThreeTimes_shouldReturnZeroSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		addSixDifferentElements(buffer);
		
		buffer.addElement("A");
		buffer.addElement("A");
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6BufferWithThreeEqualValuesInTheFirstColumn_whenAddingAnElementInTheFirstColumnWithTheSameValue_shouldReturnOneSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		addSixDifferentElements(buffer);
		addSixDifferentElements(buffer);
		addSixDifferentElements(buffer);
		
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
	@Test
	public void givenA6x6BufferWithThreeEqualValuesInTheFirstColumn_whenAddingAnElementInTheFirstColumnWithADifferentValue_shouldReturnOneSequences() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		addSixDifferentElements(buffer);
		addSixDifferentElements(buffer);
		addSixDifferentElements(buffer);
		
		int numberOfCompletedSequences = buffer.addElement("B");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6BufferWithANWSEDiagonalThatHasThreeEqualValues_whenAddingAnElementWithEqualValueInTheSameDiagonal_shouldReturnOneSequence() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
		
		buffer.addElement("F");
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		
		buffer.addElement("E");
		buffer.addElement("F");
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
		
		int numberOfCompletedSequences = buffer.addElement("A");
		
		assertThat(numberOfCompletedSequences, is(1));
	}
	
		@Test
	public void givenA6x6BufferWithANWSEDiagonalThatHasThreeEqualValues_whenAddingAnElementWithDifferentValueInTheSameDiagonal_shouldReturnZeroSequence() {
		Buffer<String> buffer = new Buffer<String>(6,6);
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
		
		buffer.addElement("F");
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		buffer.addElement("E");
		
		buffer.addElement("E");
		buffer.addElement("F");
		buffer.addElement("A");
		buffer.addElement("B");
		buffer.addElement("C");
		buffer.addElement("D");
		
		buffer.addElement("D");
		buffer.addElement("E");
		buffer.addElement("F");
		
		int numberOfCompletedSequences = buffer.addElement("B");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
	
	@Test
	public void givenA6x6BufferWithANESWDiagonalThatHasThreeEqualValues_whenAddingAnElementWithEqualValueInTheSameDiagonal_shouldReturnOneSequence() {
		Buffer<String> buffer = new Buffer<String>(6,6);
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
	public void givenA6x6BufferWithANESWDiagonalThatHasThreeEqualValues_whenAddingAnElementWithDifferentValueInTheSameDiagonal_shouldReturnOneSequence() {
		Buffer<String> buffer = new Buffer<String>(6,6);
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
		
		int numberOfCompletedSequences = buffer.addElement("Q");
		
		assertThat(numberOfCompletedSequences, is(0));
	}
}
