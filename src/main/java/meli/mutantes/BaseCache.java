package meli.mutantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class BaseCache<T> extends Observable {
	
	private List<CacheElement<T>> elements;
	private int currentIndex;
	private int numberOfSequences;
	
	
	public BaseCache(int size, int startIndex, MatchStrategyFactory<T> strategyFactory) {
		initializeElements(size, strategyFactory);
		currentIndex = startIndex;
		numberOfSequences = 0;
	}
	
	private void initializeElements(int size, MatchStrategyFactory<T> strategyFactory) {
		elements = new ArrayList<CacheElement<T>>();
		for (int i = 0; i < size; i++) {
			elements.add(new CacheElement<T>(strategyFactory.newStrategy()));
		}
	}
	
	protected int getSize() {
		return elements.size();
	}
	
	public int getSequences() {
		return numberOfSequences;
	}
	
	public void addElement(T element) {
		CacheElement<T> cacheElement = getCurrentElement();
		numberOfSequences += cacheElement.add(element);
		currentIndex = calculateNewIndex(currentIndex);
		notifyObservers(currentIndex, element);
	}
	
	private CacheElement<T> getCurrentElement() {
		return elements.get(currentIndex);
	}
	
	protected abstract int calculateNewIndex(int currentIndex);
	
	protected abstract void notifyObservers(int currentIndex, T element);
}
