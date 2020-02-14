package meli.mutantes;

import java.util.ArrayList;
import java.util.List;

public class Buffer<T> {
	
	private List<BufferElement<T>> rowsBuffer;
	private List<BufferElement<T>> columnsBuffer;
	private List<BufferElement<T>> NWSEDiagonalBuffer;
	private List<BufferElement<T>> NESWDiagonalBuffer;
	private int currentRowIndex;
	private int currentColumnIndex;
	private int currentNWSEDiagonalIndex;
	private int currentNESWDiagonalIndex;
	private int numberOfSequences;
	
	public Buffer(int rowsCount, int columnsCount) {
		rowsBuffer = new ArrayList<BufferElement<T>>();
		columnsBuffer = new ArrayList<BufferElement<T>>();
		NWSEDiagonalBuffer = new ArrayList<BufferElement<T>>();
		NESWDiagonalBuffer = new ArrayList<BufferElement<T>>();
		currentRowIndex = 0;
		currentColumnIndex = 0;
		currentNWSEDiagonalIndex = rowsCount - 1;
		currentNESWDiagonalIndex = 0;
		numberOfSequences = 0;
		initBuffers(rowsCount, columnsCount);
	}
	
	private void initBuffers(int rowsCount, int columnsCount) {
		for (int i = 0; i < rowsCount; i++) {
			rowsBuffer.add(new BufferElement<T>());
		}
		for (int i = 0; i < rowsCount; i++) {
			columnsBuffer.add(new BufferElement<T>());
		}
		for (int i = 0; i < numberOfDiagonals(rowsCount); i++) {
			NWSEDiagonalBuffer.add(new BufferElement<T>());
		}
		for (int i = 0; i < numberOfDiagonals(rowsCount); i++) {
			NESWDiagonalBuffer.add(new BufferElement<T>());
		}
	}

	private int numberOfDiagonals(int rowsCount) {
		return 2 * rowsCount - 1;
	}

	public int addElement(T element) {
		numberOfSequences += addElementToColumn(element);
		numberOfSequences += addElementToRow(element);
		numberOfSequences += addElementNWSEDiagonal(element);
		numberOfSequences += addElementNESWDiagonal(element);
		
		
		currentColumnIndex++;
		currentRowIndex += (currentColumnIndex)/columnsBuffer.size();
		currentColumnIndex = (currentColumnIndex)%columnsBuffer.size();
		currentNWSEDiagonalIndex = calculateDiagonalIndex();
		currentNESWDiagonalIndex = calculateNESWDiagonalIndex();
		
		return numberOfSequences;
	}

	private int calculateDiagonalIndex() {
		return currentColumnIndex - currentRowIndex + rowsBuffer.size() - 1;
	}
	
	private int calculateNESWDiagonalIndex() {
		return currentRowIndex - invertedColIndex() + rowsBuffer.size() - 1;
	}

	private int invertedColIndex() {
		return columnsBuffer.size() - 1 - currentColumnIndex;
	}

	private int addElementNWSEDiagonal(T element) {
		BufferElement<T> bufferElement = 
				NWSEDiagonalBuffer.get(currentNWSEDiagonalIndex);
		bufferElement.add(element);
		
		return (bufferElement.checkSequence(4)) ? 1 : 0;
	}
	
	private int addElementNESWDiagonal(T element) {
		BufferElement<T> bufferElement = 
				NESWDiagonalBuffer.get(currentNESWDiagonalIndex);
		bufferElement.add(element);
		
		return (bufferElement.checkSequence(4)) ? 1 : 0;
	}
	
	private int addElementToColumn(T element) {
		BufferElement<T> bufferElement = columnsBuffer.get(currentColumnIndex);
		bufferElement.add(element);
		
		return (bufferElement.checkSequence(4)) ? 1 : 0;
	}
	
	private int addElementToRow(T element) {
		BufferElement<T> bufferElement = rowsBuffer.get(currentRowIndex);
		bufferElement.add(element);
		
		return (bufferElement.checkSequence(4)) ? 1 : 0;
	}
}
