package meli.mutantes;

public class RowChange<T> {
	
	private ColumnChange<T> columnChange;
	private int currentRowIndex;


	public RowChange(ColumnChange<T> columnChange, int currentRowIndex) {
		this.columnChange = columnChange;
		this.currentRowIndex = currentRowIndex;
	}
	
	public int getCurrentRowIndex() {
		return currentRowIndex;
	}
	
	public int getCurrentColumnIndex() {
		return columnChange.getCurrentColumnIndex();
	}
	
	public T getElement() {
		return columnChange.getElement();
	}
}
