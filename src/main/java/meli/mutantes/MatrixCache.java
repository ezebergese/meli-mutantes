package meli.mutantes;

public class MatrixCache<T> {
	
	private ColumnCache<T> columnCache;
	private RowCache<T> rowCache;
	private NWSEDDiagonalCache<T> nwseDiagonalCache;
	private NESWDiagonalCache<T> neswDiagonalCache;
	
	
	public MatrixCache(int size) {
		columnCache = new ColumnCache<T>(size);
		rowCache = new RowCache<T>(size);
		nwseDiagonalCache = new NWSEDDiagonalCache<T>(size);
		neswDiagonalCache = new NESWDiagonalCache<T>(size);
		
		columnCache.addObserver(rowCache);
		rowCache.addObserver(nwseDiagonalCache);
		rowCache.addObserver(neswDiagonalCache);
	}
	
	public int addElement(T element) {
		columnCache.addElement(element);
		return getAllSequences();
	}
	
	private int getAllSequences() {
		return columnCache.getSequences()
				+ rowCache.getSequences()
				+ nwseDiagonalCache.getSequences()
				+ neswDiagonalCache.getSequences();
	}
}
