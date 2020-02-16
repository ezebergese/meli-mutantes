package meli.mutantes;

public class EqualElementsStrategyFactory<T> implements MatchStrategyFactory<String> {

	private int numberOfElementsForMatch;
	
	
	public EqualElementsStrategyFactory(int numberOfElementsForMatch) {
		this.numberOfElementsForMatch = numberOfElementsForMatch;
	}

	@Override
	public MatchStrategy<String> newStrategy() {
		return new EqualElements<String>(numberOfElementsForMatch);
	}
}
