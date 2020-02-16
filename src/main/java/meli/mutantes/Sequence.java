package meli.mutantes;

import java.util.Iterator;

public class Sequence implements Iterable<String> {
	
	private String[] elements;
	
	
	public Sequence(String[] elements) {
		if(elements == null)
			throw new NullPointerException("elements is null");
		this.elements = elements;
	}
	
	public int size() {
		return elements.length;
	}
	
	@Override
	public Iterator<String> iterator() {
		return new SequenceIterator();
	}
	
	private class SequenceIterator implements Iterator<String> {
		
		private int currentRowIndex;
		private int currentColumnIndex;
		
		
		SequenceIterator() {
			currentRowIndex = 0;
			currentColumnIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return currentRowIndex < size() || currentColumnIndex < size(); 
		}

		@Override
		public String next() {
			return new String(new char[] {elements[currentRowIndex++].charAt(currentColumnIndex++)});
		}
	}
}