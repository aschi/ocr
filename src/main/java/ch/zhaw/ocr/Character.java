package ch.zhaw.ocr;

import java.util.LinkedList;
import java.util.List;

public class Character{
	ContrastMatrix characterM;
		
	/**
	 * TODO: Probably implement comparable representative of char
	 * @param characterM
	 */
	private Character(ContrastMatrix characterM){
		this.characterM = characterM;
	}
	
	public ContrastMatrix getMatrix(){
		return characterM;
	}
	
	/**
	 * TODO: Implement comparison :)
	 * @return
	 */
	public boolean equals(){
		return false;
	}
	
	/**
	 * Parse a contrast matrix (representig a word) to get all contained characters
	 * @param wordM
	 * @return
	 */
	public static List<Character> parseCharacters(ContrastMatrix wordM){
		List<Character> rv = new LinkedList<Character>();
	
		boolean emptyRow = true;
		
		//remove empty row
		for(int y = 0;y < wordM.getHeight();y++){
			emptyRow = true;
			
			for(int x = 0;x < wordM.getWidth();x++){
				if(wordM.getValue(x, y) != 0){
					emptyRow = false;
				}
			}
			if(emptyRow){
				wordM.removeRow(y);
				y--;
			}
		}
		
		boolean emptyCol = true;
		int characterStart = -1;
		
		//extract characters
		for(int x = 0; x < wordM.getWidth();x++){
			emptyCol = true;
			
			for(int y = 0;y < wordM.getHeight();y++){
				if(wordM.getValue(x, y) != 0){
					emptyCol = false;
				}
			}
			
			if(emptyCol){
				if(characterStart != -1){
					//column after character is empty => start -> x-1 is a character
					rv.add(new Character(wordM.getSubMatrix(characterStart, 0, x-characterStart, wordM.getHeight())));
					characterStart = -1;
				}
			}else{
				if(characterStart == -1){
					characterStart = x;
				}
			}	
		}
		
		//last charakter
		if(characterStart != -1){
			rv.add(new Character(wordM.getSubMatrix(characterStart, 0, wordM.getWidth()-characterStart, wordM.getHeight())));
		}
		
		return rv;
	}
}
