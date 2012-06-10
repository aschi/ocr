package ch.zhaw.ocr.textRecognition;

import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.bitmapParser.BitmapParser;
import ch.zhaw.ocr.bitmapParser.CharacterParser;
import ch.zhaw.ocr.bitmapParser.ContrastMatrix;
import ch.zhaw.ocr.bitmapParser.RowParser;
import ch.zhaw.ocr.bitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.bitmapParser.UnderlineRemover;
import ch.zhaw.ocr.bitmapParser.WordParser;
import ch.zhaw.ocr.dictionary.Dictionary;
import ch.zhaw.ocr.nn.CharacterRepresentation;
import ch.zhaw.ocr.nn.NeuralNetwork;

/**
 * Class OCR. Combines TextParsing, Neural Network and Dictionary
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class Ocr {
	
	private NeuralNetwork nn;
	private Dictionary dic;
	
	/**
	 * Initialise OCR
	 * @param nn NeuralNetwork instance to be used
	 * @param dic Dictionary instance to be used
	 */
	public Ocr(NeuralNetwork nn, Dictionary dic) {
		this.nn = nn;
		this.dic = dic;
	}
	
	/**
	 * Parse a given image and return the parsed text as string
	 * @param f input image
	 * @param consoleText a string buffer used to display a nn console in our UI.
	 * @return parsed text
	 */
	public String parseImage(File f, StringBuffer consoleText) {
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
				char c = 0;
				if(cm.getFunctionalChar() != null){
					c = cm.getFunctionalChar().getCharacter();
				}else{
					c = nn.detectCharacter(MatrixFactory.createMatrix(new CharacterRepresentation(cm).getComparisonVector()), consoleText);
				}
				//if the character is a functional character
				if (cm.getFunctionalChar() != null) {
					String correctedWord = dic.correctWord(wordBuffer.toString());
					
					consoleText.append("Dictionary Input: "+wordBuffer.toString() + "\n");
					consoleText.append("Dictionary Output: "+ correctedWord + "\n");
					
					textBuffer.append(correctedWord);
					textBuffer.append(c);
					wordBuffer.delete(0, wordBuffer.length());
				} else {
					wordBuffer.append(c);
				}
				
			}
			//check last word in dictionary
			if (wordBuffer.length() > 0) {
				textBuffer.append(dic.correctWord(wordBuffer.toString()));
			}
			consoleText.append("Einlesen des Textes abgeschlossen...("+ (System.currentTimeMillis()-t1) +"ms)");
			
			return textBuffer.toString().replace(Properties.unknownChar, '_');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get the dictionary
	 * @return dictionary
	 */
	public Dictionary getDictionary() {
		return dic;
	}

}
