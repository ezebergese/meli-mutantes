package meli.mutantes.model.patternDetector;

public class ColumnPatternDetector<T> extends LinealPatternDetector<T> {
	
	public ColumnPatternDetector(int matrixSize, 
			PatternDetectorFactory<T> patternDetectorFactory) {
		super(matrixSize, startIndex(), patternDetectorFactory);
	}
	
	private static int startIndex() {
		return 0;
	}
	
	@Override
	protected int calculateNewIndex(int currentIndex) {
		return (++currentIndex)%getSize();
	}
	
	@Override
	protected void notifyObservers(int currentIndex, T element) {
		setChanged();
		notifyObservers(new ColumnChange<T>(currentIndex, element));
	}
}
