package meli.mutantes.model.patternDetector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import meli.mutantes.model.patternDetector.EqualElementsDetectorFactory;
import meli.mutantes.model.patternDetector.MatrixPatternDetector;

public class MatrixPatternDetectorTest {
	
	private MatrixPatternDetector<String> createMatrixPatternDetector() {
		return new MatrixPatternDetector<String>(6, new EqualElementsDetectorFactory<String>(4));
	}
	
	private void addSixDifferentElements(MatrixPatternDetector<String> matrixPatternDetector) {
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
	}
	
	@Test
	public void whenAddingOneElement_numberOfPatternsShouldBeZero() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(0));
	}
	
	@Test
	public void whenAddingAnElementThreeTimes_numberOfPatternsShouldBeZero() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(0));
	}
	
	@Test
	public void whenAddingAnElementFourTimes_numberOfPatternsShouldBeOne() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(1));
	}
	
	@Test
	public void whenAddingAnElementFiveTimes_numberOfPatternsShouldBeOne() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("A");
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(1));
	}
	
	@Test
	public void whenCompletingAColumnPattern_numberOfPatternsShouldBeOne() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		addSixDifferentElements(matrixPatternDetector);
		addSixDifferentElements(matrixPatternDetector);
		addSixDifferentElements(matrixPatternDetector);
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(1));
	}
	
	@Test
	public void whenAddingElementsWithoutCompletingAColumnPattern_numberOfPatternsShouldBeZero() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		addSixDifferentElements(matrixPatternDetector);
		addSixDifferentElements(matrixPatternDetector);
		addSixDifferentElements(matrixPatternDetector);
		
		int numberOfPatterns = matrixPatternDetector.addElement("B");
		
		assertThat(numberOfPatterns, is(0));
	}
	
	@Test
	public void whenCompletingANWSEDiagonalPattern_numberOfPatternsShouldBeOne() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		int numberOfPatterns = matrixPatternDetector.addElement("A");
		
		assertThat(numberOfPatterns, is(1));
	}
	
	@Test
	public void whenAddingElementsWithoutCompletingANWSEDiagonalPattern_numberOfPatternsShouldBeZero() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		int numberOfPatterns = matrixPatternDetector.addElement("B");
		
		assertThat(numberOfPatterns, is(0));
	}
	
	@Test
	public void whenCompletingANESWDiagonalPattern_numberOfPatternsShouldBeOne() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		int numberOfPatterns = matrixPatternDetector.addElement("F");
		
		assertThat(numberOfPatterns, is(1));
	}
	
	@Test
	public void whenAddingElementsWithoutCompletingANESWDiagonalPattern_numberOfPatternsShouldBeZero() {
		MatrixPatternDetector<String> matrixPatternDetector = createMatrixPatternDetector();
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("F");
		
		matrixPatternDetector.addElement("E");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("A");
		
		matrixPatternDetector.addElement("A");
		matrixPatternDetector.addElement("B");
		matrixPatternDetector.addElement("C");
		matrixPatternDetector.addElement("F");
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		matrixPatternDetector.addElement("D");
		matrixPatternDetector.addElement("E");
		
		int numberOfPatterns = matrixPatternDetector.addElement("Q");
		
		assertThat(numberOfPatterns, is(0));
	}
}
