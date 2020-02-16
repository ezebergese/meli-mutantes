package meli.mutantes;

public interface MatchStrategyFactory<T> {

	MatchStrategy<T> newStrategy();

}
