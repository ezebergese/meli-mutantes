package meli.mutantes;

public class DNA {
	
	private static final int NUMBER_OF_MATCHES_FOR_MUTANT = 1;
	private Sequence sequence;
	
	
	public DNA(String[] sequenceArray) {
		this.sequence = new Sequence(sequenceArray);
	}
	
	public boolean isMutant() {
		MatrixCache<String> matrixCache = new MatrixCache<String>(sequence.size());
		for(String currentElement : sequence) {
			int totalMatches = matrixCache.addElement(currentElement);
			if (enoughMatchesForMutant(totalMatches)) return true;
		}
		
		return false;
	}

	private boolean enoughMatchesForMutant(int totalMatches) {
		return totalMatches >= NUMBER_OF_MATCHES_FOR_MUTANT;
	}
}
