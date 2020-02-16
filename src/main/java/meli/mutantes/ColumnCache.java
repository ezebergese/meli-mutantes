package meli.mutantes;

public class ColumnCache<T> extends BaseCache<T> {
	
	public ColumnCache(int matrixSize) {
		super(matrixSize, startIndex());
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
