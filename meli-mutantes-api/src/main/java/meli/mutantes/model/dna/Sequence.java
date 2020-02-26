package meli.mutantes.model.dna;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Sequence implements Iterable<String> {
	
	private String[] elements;
	
	
	public Sequence(String[] elements) {
		if(elements == null)
			throw new NullPointerException("elements is null");
		this.elements = elements;
	}
	
	/*
	 * Used only by DynamoDb
	 */
	@Deprecated
	@DynamoDBAttribute
	public List<String> getElements() {
		return Arrays.asList(elements);
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
			return currentRowIndex < size() && currentColumnIndex < size(); 
		}

		@Override
		public String next() {
			String nextElement = getNextElement();
			currentColumnIndex = (++currentColumnIndex)%size();
			currentRowIndex = (currentColumnIndex == 0) ? ++currentRowIndex : currentRowIndex;
			
			return nextElement;
		}

		private String getNextElement() {
			try {
				return new String(new char[] {elements[currentRowIndex].charAt(currentColumnIndex)});
			} catch(IndexOutOfBoundsException e) {
				throw new InvalidSequenceException();
			}
		}
	}
}
