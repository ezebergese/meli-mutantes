package meli.mutantes;

public class CacheElement<T> {
	
	private MatchStrategy<T> strategy;
	
	
	public CacheElement(MatchStrategy<T> strategy) {
		this.strategy = strategy;
	}
	
	public int add(T newElement) {
		return strategy.add(newElement);
	}
}
