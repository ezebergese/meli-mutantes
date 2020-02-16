package meli.mutantes;

public class NESWDiagonalCache<T> extends DiagonalCache<T> {
	
	public NESWDiagonalCache(int matrixSize, MatchStrategyFactory<T> strategyFactory) {
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
	
	@Override
	protected void notifyObservers(int currentIndex, T element) {
		// This buffer does not notify other buffers
	}
}
