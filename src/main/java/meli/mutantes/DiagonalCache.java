package meli.mutantes;

import java.util.Observable;
import java.util.Observer;

public abstract class DiagonalCache<T> extends BaseCache<T> implements Observer {
	
	private RowChange<T> lastRowChange;
	private int matrixSize;
	
	
	public DiagonalCache(int matrixSize, MatchStrategyFactory<T> strategyFactory) {
		super(numberOfDiagonals(matrixSize), startIndex(matrixSize), strategyFactory);
		this.matrixSize = matrixSize;
	}

	private static int numberOfDiagonals(int matrixSize) {
		return 2 * matrixSize - 1;
	}
	
	private static int startIndex(int matrixSize) {
		return matrixSize - 1;
	}
	
	protected int getCurrentColumnIndex() {
		return lastRowChange.getCurrentColumnIndex();
	}
	
	protected int getCurrentRowIndex() {
		return lastRowChange.getCurrentRowIndex();
	}
	
	protected int getMatrixSize() {
		return matrixSize;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		lastRowChange = (RowChange<T>) arg;
		addElement(lastRowChange.getElement());
	}
}
