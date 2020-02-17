package meli.mutantes.model.patternDetector;

public class EqualElementsDetector<T> implements PatternDetector<T> {

	private T element;
	private int numberOfElementsInPattern;
	private int count;
	
	public EqualElementsDetector(int numberOfElementsInPattern) {
		this.numberOfElementsInPattern = numberOfElementsInPattern;
		element = null;
		count = 0;
	}
	
	@Override
	public int add(T newElement) {
		if (elementChanged(newElement))
			setNewElement(newElement);
		else
			incrementCount();
		
		return verifySequence();
	}
	
	private int verifySequence() {
		boolean patternFound = (count == numberOfElementsInPattern);
		if (patternFound)
			reset();
		
		return (patternFound) ? 1 : 0;
	}
	
	private void reset() {
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
