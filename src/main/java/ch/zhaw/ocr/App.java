package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.BitmapParser.BitmapParser;
import ch.zhaw.ocr.BitmapParser.CharacterParser;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;
import ch.zhaw.ocr.BitmapParser.RowParser;
import ch.zhaw.ocr.BitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.BitmapParser.UnderlineRemover;
import ch.zhaw.ocr.BitmapParser.WordParser;
import ch.zhaw.ocr.CharacterRecognition.Character;
import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.CharacterRecognition.InitialLearning;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		/*
		 * 
		 
		CharacterComperator cc = new CharacterComperator();	
		
		InitialLearning.learn(cc);

		// cc.detectCharacter()
		try {
			BitmapParser bp = new CharacterParser(new WordParser(new UnderlineRemover(new RowParser(
					new SimpleBitmapParser()))));
			
			List<ContrastMatrix> matrices = bp.parse(ImageIO.read(new File("img/underline2.PNG")));
			for(ContrastMatrix cm : matrices){
				String c = cc.detectCharacter(new Character(cm));
				System.out.print(c.equals("") ? "?" : c);
			}
			
			// ContrastMatrix cm = ContrastMatrix.parseImage(ImageIO.read(new
			// File("img/debian_line.png")));

			// System.out.println(cm);

			// List<Word> wl = Word.parseWords(cm);

			// List<Character> chl =
			// Character.parseCharacters(wl.get(wl.size()-1).getMatrix());

			// for(Character c : chl){
			// System.out.println(c.getMatrix());
			// }

			// System.out.println(wl.get(wl.size()-1).getMatrix());

			// for(Word m : wl){
			// System.out.println(m.getMatrix());
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
}
