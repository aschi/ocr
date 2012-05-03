package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.CharacterRecognition.InitialLearning;
import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.gui.MainGui;

public class Starter {

	public static void main(String[] args) {
		Dictionary dict = null;

		try {
			dict = new Dictionary(new File("res/dictionaryMaterial"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CharacterComperator cc = new CharacterComperator(dict);

		InitialLearning.learn(cc);
		new MainGui(cc);

	}
}
