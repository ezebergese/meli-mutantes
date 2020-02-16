package meli.mutantes;

public class ColumnChange<T> {

	private int currentIndex;
	private T element;


	public ColumnChange(int currentIndex, T element) {
		this.currentIndex = currentIndex;
		this.element = element;
	}
	
	public int getCurrentColumnIndex() {
		return currentIndex;
	}
	
	public T getElement() {
		return element;
	}
}
