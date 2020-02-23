package meli.mutantes.model.patternDetector;

public class NWSEDDiagonalPatternDetector<T> extends DiagonalPatternDetector<T> {
	
	public NWSEDDiagonalPatternDetector(int matrixSize, PatternDetectorFactory<T> strategyFactory) {
		super(matrixSize, strategyFactory);
	}

	@Override
	protected int calculateNewIndex(int currentIndex) {
		return getCurrentColumnIndex() 
				- getCurrentRowIndex() 
				+ getMatrixSize() - 1;
	}
}
