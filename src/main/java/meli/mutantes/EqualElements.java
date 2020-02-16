package meli.mutantes;

public class EqualElements<T> implements MatchStrategy<T> {
	
	private T element;
	private int numberOfElementsForAMatch;
	private int count;
	
	public EqualElements(int numberOfElementsForAMatch) {
		this.numberOfElementsForAMatch = numberOfElementsForAMatch;
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
		boolean sequenceFound = (count == numberOfElementsForAMatch);
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
