package meli.mutantes.model.patternDetector;

public class EqualElementsDetectorFactory<T> implements PatternDetectorFactory<T> {

	private int numberOfElementsInPattern;
	
	
	public EqualElementsDetectorFactory(int numberOfElementsInPattern) {
		this.numberOfElementsInPattern = numberOfElementsInPattern;
	}

	@Override
	public PatternDetector<T> newDetector() {
		return new EqualElementsDetector<T>(numberOfElementsInPattern);
	}
}
