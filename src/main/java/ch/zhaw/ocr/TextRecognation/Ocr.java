package ch.zhaw.ocr.TextRecognation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.Property;
import ch.zhaw.ocr.BitmapParser.BitmapParser;
import ch.zhaw.ocr.BitmapParser.CharacterParser;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;
import ch.zhaw.ocr.BitmapParser.RowParser;
import ch.zhaw.ocr.BitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.BitmapParser.UnderlineRemover;
import ch.zhaw.ocr.BitmapParser.WordParser;
import ch.zhaw.ocr.CharacterRecognition.Character;
import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.Dictionary.Dictionary;

public class Ocr {
	
	private CharacterComperator cc;
	private Dictionary dic;
	
	public Ocr(CharacterComperator cc, Dictionary dic) {
		this.cc = cc;
		this.dic = dic;
	}
	
	public String parseImage(File f) {
		long t1 = System.currentTimeMillis();
		
		StringBuffer textBuffer = new StringBuffer();
		StringBuffer wordBuffer = new StringBuffer();

		// parse bitmap
		BitmapParser bp = new CharacterParser(new WordParser(
				new UnderlineRemover(new RowParser(new SimpleBitmapParser()))));

		try {
			
			
			//Word seperating
			
			
			List<ContrastMatrix> matrices = bp.parse(ImageIO.read(f));
			// character output
			for (ContrastMatrix cm : matrices) {
				String c = cc.detectCharacter(new Character(cm));
				//if the character is a functional character
				if (cm.getFunctionalChar() != null) {
					System.out.println("Dictionary Input: "+wordBuffer.toString());
					System.out.println("Dictionary Output: "+dic.correctWord(wordBuffer.toString()));
					textBuffer.append(dic.correctWord(wordBuffer.toString()));
					textBuffer.append(c);
					wordBuffer.delete(0, wordBuffer.length());
				} else {
					wordBuffer.append(c.equals("") ? Property.unknownChar : c);
				}
				
			}
			//check last word in dictionary
			if (wordBuffer.length() > 0) {
				textBuffer.append(dic.correctWord(wordBuffer.toString()));
			}
			System.out.println("Einlesen des Textes abgeschlossen...("+ (System.currentTimeMillis()-t1) +"ms)");
			return textBuffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
