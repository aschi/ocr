package ch.zhaw.ocr.bitmapParser;

/**
 * Enum FunctionalCharacter used to represent functional characters (space & carriageReturn)
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public enum FunctionalCharacter {
	space			(' '),
	carriageReturn	('\n');
	
	private char character;
	
	FunctionalCharacter(char character){
		this.character = character;
	}
	
	public String toString(){
		return Character.toString(character);
	}
	public char getCharacter(){
		return character;
	}
}
