package ch.zhaw.ocr.BitmapParser;

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
