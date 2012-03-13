package ch.zhaw.ocr;

import java.util.LinkedList;
import java.util.List;

public class Word {
	private ContrastMatrix matrix;
	
	public Word(ContrastMatrix matrix){
		this.matrix = matrix;
	}
	
	public ContrastMatrix getMatrix(){
		return matrix;
	}
	
	/**
	 * Parse a contrast matrix (representig a textline) to get all contained words
	 * Step 1: Search for horizontal spaces (empty vertical lines). Identify spaces between characters / spaces between words
	 * Step 2: Split line
	 * @param lineM matrix representing a line
	 * @return
	 */
	public static List<Word> parseWords(ContrastMatrix lineM){
		//trim matrix
		lineM.trim();
		
		List<Word> rv = new LinkedList<Word>();
		
		boolean isEmpty;
		
		int minSpaceSize = 0;
		int maxSpaceSize = 0;
		
		int spaceCounter = 0;
		
		int wordStart = -1;
		int wordEndTmp = -1;
		
		int x = 0;
		int y = 0;
		
		/**
		 * Step 1: find min / max space sizes
		 */
		for(x = 0;x < lineM.getWidth();x++){
			isEmpty = true;
			for(y = 0;y < lineM.getHeight();y++){
				if(lineM.getValue(x, y) == 1){
					isEmpty = false;
				}
			}
			
			if(isEmpty){
				spaceCounter++;
			}else{
				//min / max space detection
				if(spaceCounter > 0){
					if(minSpaceSize == 0 || minSpaceSize > spaceCounter){
						minSpaceSize = spaceCounter;
					}
					
					if(maxSpaceSize < spaceCounter){
						maxSpaceSize = spaceCounter;
					}
				}
				spaceCounter = 0;
			}
		}
		
		
		System.out.println("min space size: " +minSpaceSize);
		System.out.println("max space size: " +maxSpaceSize);
		
		System.out.println(lineM);
		
		spaceCounter = 0;
		wordEndTmp = -1;
		wordStart = -1;
		
		for(x = 0;x < lineM.getWidth();x++){
			isEmpty = true;
			for(y = 0;y < lineM.getHeight();y++){
				if(lineM.getValue(x, y) == 1){
					isEmpty = false;
				}
			}
			
			if(isEmpty){
				spaceCounter++;
				
				if(wordStart != -1){
					if(wordEndTmp == -1){
						wordEndTmp = x;
					}			
				}
			}else{
				//word start
				if(wordStart == -1){
					wordStart = x;
				}
				
				if(spaceCounter != 0){
					//last space was a "word space" (space size closer to max then to min)
					if((maxSpaceSize-spaceCounter) < (spaceCounter-minSpaceSize)){
						rv.add(new Word(lineM.getSubMatrix(wordStart, 0, wordEndTmp-wordStart, lineM.getHeight())));
						System.out.println((maxSpaceSize-spaceCounter) + " - " + (spaceCounter-minSpaceSize));		
						wordStart = x;
					}else{
						System.out.println((maxSpaceSize-spaceCounter) + " - " + (spaceCounter-minSpaceSize));					
					}
				}
				
				//reset counters
				wordEndTmp = -1;
				spaceCounter = 0;
			}
			
			
		}
		
		//get last word
		if(wordStart != -1){
			rv.add(new Word(lineM.getSubMatrix(wordStart, 0, lineM.getWidth()-wordStart, lineM.getHeight())));
		}
		
		
		
		return rv;
	}
}
