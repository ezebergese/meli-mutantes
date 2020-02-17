package meli.mutantes.model.patternDetector;

public interface PatternDetectorFactory<T> {

	PatternDetector<T> newDetector();

}
