package meli.mutantes.model.patternDetector;

public class MatrixPatternDetector<T> {
	
	private ColumnPatternDetector<T> columnPatternDetector;
	private RowPatternDetector<T> rowPatternDetector;
	private NWSEDDiagonalPatternDetector<T> nwseDiagonalPatternDetector;
	private NESWDiagonalPatternDetector<T> neswDiagonalPatternDetector;
	
	
	public MatrixPatternDetector(int size, PatternDetectorFactory<T> patternDetectorFactory) {
		columnPatternDetector = new ColumnPatternDetector<T>(size, patternDetectorFactory);
		rowPatternDetector = new RowPatternDetector<T>(size, patternDetectorFactory);
		nwseDiagonalPatternDetector = new NWSEDDiagonalPatternDetector<T>(size, patternDetectorFactory);
		neswDiagonalPatternDetector = new NESWDiagonalPatternDetector<T>(size, patternDetectorFactory);
		
		columnPatternDetector.addObserver(rowPatternDetector);
		rowPatternDetector.addObserver(nwseDiagonalPatternDetector);
		rowPatternDetector.addObserver(neswDiagonalPatternDetector);
	}
	
	public int addElement(T element) {
		columnPatternDetector.addElement(element);
		return getAllPatterns();
	}
	
	private int getAllPatterns() {
		return columnPatternDetector.getPatternsFoundCount()
				+ rowPatternDetector.getPatternsFoundCount()
				+ nwseDiagonalPatternDetector.getPatternsFoundCount()
				+ neswDiagonalPatternDetector.getPatternsFoundCount();
	}
}
