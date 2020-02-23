package meli.mutantes.model.patternDetector;

public class NESWDiagonalPatternDetector<T> extends DiagonalPatternDetector<T> {
	
	public NESWDiagonalPatternDetector(int matrixSize, PatternDetectorFactory<T> strategyFactory) {
		super(matrixSize, strategyFactory);
	}
	
	@Override
	protected int calculateNewIndex(int currentIndex) {
		return getCurrentRowIndex() 
				- invertedColIndex() 
				+ getMatrixSize() - 1;
	}
	
	private int invertedColIndex() {
		return getMatrixSize() - 1 - getCurrentColumnIndex();
	}
}
