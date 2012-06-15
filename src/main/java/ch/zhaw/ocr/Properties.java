package ch.zhaw.ocr;

import java.util.Arrays;
import java.util.List;

/**
 * Global configuration of the OCR software 
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public final class Properties {
	public static final char unknownChar = '~';
	
	/*
	 * History Configuration
 	 */
	public static final String historyResourcefoler = "history";
	
	/**
	 * FileSeparator
	 */
	public static final String fileSeparator = System.getProperty("file.separator");
	
	/*
	 * Dictionary Configuration
	 */
	public static final String dictionaryMapSerializiationPath = "res" + fileSeparator + "dictionary.ser";
	public static final String dictionaryResourceFolder = "res" + fileSeparator + "dictionaryMaterial";

	
	/*
	 * KNN Configuration
	 */
	public static final int nnInputLayerSize = 400;
	public static final int nnHiddenLayerSize = 250;
	
	public static final List<Character> nnOutputLayer = Arrays.asList('A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö',
			'Ü', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'ä', 'ö', 'ü', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '.', ',', ':', ';', '?', '!');
	
	public static final int nnOutputLayerSize = nnOutputLayer.size();
	
	public static final int nnMaxIterationCount = 500;
	
	public static final String nnResourcePath = "res" + fileSeparator + "knnMaterial";
	public static final String nnSerializationPath = "res" + fileSeparator + "knn.ser";
	
}
