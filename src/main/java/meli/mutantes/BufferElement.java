package meli.mutantes;

public class BufferElement<T> {
	
	private T element;
	private int count;
	
	public BufferElement() {
		element = null;
		count = 0;
	}
	
	public void add(T elementToAdd) {
		if (element != null && 
				element.equals(elementToAdd))
			count++;
		else {
			element = elementToAdd;
			count = 1;
		}
	}
	
	public boolean checkSequence(int numberOfElementsForSequence) {
		return count == numberOfElementsForSequence;
	}
}
