package meli.mutantes;

import java.util.Observable;
import java.util.Observer;

public class RowCache<T> extends BaseCache<T> implements Observer {
	
	private ColumnChange<T> lastColumnChange;
	
	
	public RowCache(int matrixSize, MatchStrategyFactory<T> strategyFactory) {
		super(matrixSize, startIndex(), strategyFactory);
	}

	private static int startIndex() {
		return 0;
	}
	
	@Override
	protected int calculateNewIndex(int currentIndex) {
		// If column index goes back to 0 means that the current position is in a new row
		return (lastColumnChange.getCurrentColumnIndex() == 0) ? ++currentIndex : currentIndex;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		lastColumnChange = (ColumnChange<T>) arg;
		addElement(lastColumnChange.getElement());
	}
	
	@Override
	protected void notifyObservers(int currentIndex, T element) {
		setChanged();
		notifyObservers(new RowChange<T>(lastColumnChange, currentIndex));
	}
}
