package meli.mutantes.model.patternDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class LinealPatternDetector<T> extends Observable {
	
	private List<PatternDetector<T>> patternDetectors;
	private int currentIndex;
	private int patternsFoundCount;
	
	
	public LinealPatternDetector(int size, int startIndex, 
			PatternDetectorFactory<T> patternDetectorFactory) {
		initializeElements(size, patternDetectorFactory);
		currentIndex = startIndex;
		patternsFoundCount = 0;
	}
	
	private void initializeElements(int size, 
			PatternDetectorFactory<T> patternDetectorFactory) {
		patternDetectors = new ArrayList<PatternDetector<T>>();
		for (int i = 0; i < size; i++) {
			patternDetectors.add(patternDetectorFactory.newDetector());
		}
	}
	
	protected int getSize() {
		return patternDetectors.size();
	}
	
	public int getPatternsFoundCount() {
		return patternsFoundCount;
	}
	
	public void addElement(T element) {
		PatternDetector<T> paternDetector = getCurrentPatternDetector();
		patternsFoundCount += paternDetector.add(element);
		currentIndex = calculateNewIndex(currentIndex);
		notifyObservers(currentIndex, element);
	}
	
	private PatternDetector<T> getCurrentPatternDetector() {
		return patternDetectors.get(currentIndex);
	}
	
	protected abstract int calculateNewIndex(int currentIndex);
	
	protected abstract void notifyObservers(int currentIndex, T element);
}
