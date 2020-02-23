package meli.mutantes.model.dna;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;

import meli.mutantes.model.patternDetector.EqualElementsDetectorFactory;
import meli.mutantes.model.patternDetector.MatrixPatternDetector;

@DynamoDBDocument
public class DNA {
	
	private static final int MINIMUM_NUMBER_OF_PATTERNS_FOR_MUTANT = 2;
	private static final int NUMBER_OF_ELEMENTS_IN_PATTERN = 4;
	
	private Sequence sequence;
	
	
	/*
	 * Used only by DynamoDb
	 */
	@Deprecated
	public DNA() {
	}
	
	public DNA(String[] sequenceArray) {
		this.sequence = new Sequence(sequenceArray);
	}
	
	@DynamoDBAttribute
	public Sequence getSequence() {
		return sequence;
	}
	
	@DynamoDBIgnore
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
