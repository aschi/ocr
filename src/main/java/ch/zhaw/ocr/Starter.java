package ch.zhaw.ocr;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.CharacterRecognition.InitialLearning;
import ch.zhaw.ocr.gui.MainGui;

public class Starter {

	
	public static void main(String[] args) {
		CharacterComperator cc = new CharacterComperator();
		InitialLearning.learn(cc);
		new MainGui(cc);
		
		
	}
}
