package ch.zhaw.ocr.CharacterRecognition;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.BitmapParser.BitmapParser;
import ch.zhaw.ocr.BitmapParser.CharacterParser;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;
import ch.zhaw.ocr.BitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.BitmapParser.WordParser;

public class InitialLearning {

	public static void learn(CharacterComperator cc) {
		List<String> charStrings = Arrays.asList("A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
				"e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
				"2", "3", "4", "5", "6", "7", "8", "9");

		BitmapParser bp = new CharacterParser(
				new SimpleBitmapParser());

		File folder = new File("img/LearningMaterial");
		for (File f : folder.listFiles()) {
			if (f.getName().length() > 4
					&& f.getName().substring(f.getName().length() - 3)
							.equals("jpg")
					|| f.getName().substring(f.getName().length() - 3)
							.equals("png")) {
				try {
					System.out.println("parsing file: "+ f.getName());
					bp.parse(ImageIO.read(f));

					
					
					// convert into character list
					List<Character> chars = new LinkedList<Character>();
					for (ContrastMatrix cm : bp.getMatrices()) {
						//System.out.println(cm);
						chars.add(new Character(cm));
					}

					// learn
					cc.learn(chars, charStrings, 1);
				} catch (IOException e) {
					System.out.println("Bildlesefehler: " + e.getMessage());
				}
			}
		}

	}

}
