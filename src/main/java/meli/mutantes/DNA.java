package meli.mutantes;

import java.util.HashMap;
import java.util.Map;

public class DNA {
	
	private String[] sequence;

	public DNA(String[] sequence) {
		if(sequence == null)
			throw new NullPointerException("dnaSequence is null");
		this.sequence = sequence;
	}
	
	public boolean isMutant() {
		int rowsCount = sequence.length;
		int columnsCount = sequence[0].length();
		Map<String, String[]> buffer = new HashMap<String, String[]>();
		
		for(int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
			for(int columnIndex = 0; columnIndex < columnsCount; columnIndex++) {
				char currentElement = getElementInSequence(rowIndex, columnIndex);
				addElementToBuffer(buffer, currentElement, rowIndex, columnIndex);
			}
		}
		
		return true;
	}
	
	/*
	 * Adds the element to the buffer and returns the number of sequences 
	 * completed with the incorporation of the new element
	 * 
	 */
	private int addElementToBuffer(Map<String, String[]> buffer, 
			char element, int rowIndex, int columnIndex) {
		String[] bufferedRows = buffer.get("rows");
		String[] bufferedColumns = buffer.get("columns");
		
		bufferedRows[rowIndex].equals(element);
		
		return 0;
	}
	
	private char getElementInSequence(int rowIndex, int columnIndex) {
		return sequence[rowIndex].charAt(columnIndex);
	}
}
