package meli.mutantes.model.dna;

import meli.mutantes.model.patternDetector.EqualElementsDetectorFactory;
import meli.mutantes.model.patternDetector.MatrixPatternDetector;

public class DNA {
	
	private static final int MINIMUM_NUMBER_OF_PATTERNS_FOR_MUTANT = 2;
	private static final int NUMBER_OF_ELEMENTS_IN_PATTERN = 4;
	private Sequence sequence;
	
	
	public DNA(String[] sequenceArray) {
		this.sequence = new Sequence(sequenceArray);
	}
	
	public boolean isMutant() {
		MatrixPatternDetector<String> matrixPatternDetector = 
				new MatrixPatternDetector<String>(sequence.size(), 
						new EqualElementsDetectorFactory<String>(NUMBER_OF_ELEMENTS_IN_PATTERN));
		for(String currentElement : sequence) {
			int totalPatterns = matrixPatternDetector.addElement(currentElement);
			if (enoughPatternsForMutant(totalPatterns)) return true;
		}
		
		return false;
	}
	
	private boolean enoughPatternsForMutant(int totalPatterns) {
		return totalPatterns >= MINIMUM_NUMBER_OF_PATTERNS_FOR_MUTANT;
	}
}
