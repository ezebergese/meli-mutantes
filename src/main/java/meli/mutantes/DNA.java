package meli.mutantes;

public class DNA {
	
	private Sequence sequence;
	
	
	public DNA(String[] sequenceArray) {
		this.sequence = new Sequence(sequenceArray);
	}
	
	public boolean isMutant() {
		MatrixCache<String> matrixCache = new MatrixCache<String>(sequence.size());
		for(String currentElement : sequence) {
			int totalSequences = matrixCache.addElement(currentElement);
			if (totalSequences != 0) return true;
		}
		
		return false;
	}
}
