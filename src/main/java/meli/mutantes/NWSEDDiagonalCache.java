package meli.mutantes;

public class NWSEDDiagonalCache<T> extends DiagonalCache<T> {
	
	public NWSEDDiagonalCache(int matrixSize, MatchStrategyFactory<T> strategyFactory) {
		super(matrixSize, strategyFactory);
	}

	@Override
	protected int calculateNewIndex(int currentIndex) {
		return getCurrentColumnIndex() 
				- getCurrentRowIndex() 
				+ getMatrixSize() - 1;
	}
	
	@Override
	protected void notifyObservers(int currentIndex, T element) {
		// This buffer does not notify other buffers
	}
}
