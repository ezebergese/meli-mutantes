package meli.mutantes;

public class CacheElement<T> {
	
	private T element;
	private int count;
	
	public CacheElement() {
		element = null;
		count = 0;
	}
	
	public int add(T newElement) {
		if (elementChanged(newElement))
			setNewElement(newElement);
		else
			incrementCount();
		
		return verifySequence();
	}
	
	private int verifySequence() {
		boolean sequenceFound = (count == 4);
		if (sequenceFound)
			resetCache();
		
		return (sequenceFound) ? 1 : 0;
	}

	private void resetCache() {
		element = null;
		count = 0;
	}

	private void incrementCount() {
		count++;
	}
	
	private void restartCount() {
		count = 1;
	}
	
	private void setNewElement(T newElement) {
		this.element = newElement;
		restartCount();
	}
	
	private boolean elementChanged(T newElement) {
		return element == null || !element.equals(newElement);
	}
}
