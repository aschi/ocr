package ch.zhaw.ocr;

import java.util.Arrays;
import java.util.List;

public final class Properties {
	public static final char unknownChar = '~';
	
	/*
	 * History Configuration
 	 */
	public static final String historyResourcefoler = "history";
	
	/*
	 * Dictionary Configuration
	 */
	public static final String dictionaryMapSerializiationPath = "res/dictionary.ser";
	public static final String dictionaryResourceFolder = "res/dictionaryMaterial";

	
	/*
	 * KNN Configuration
	 */
	public static final int knnInputLayerSize = 400;
	public static final int knnHiddenLayerSize = 237;
	
	public static final List<Character> knnOutputLayer = Arrays.asList('A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö',
			'Ü', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'ä', 'ö', 'ü', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '.', ',', ':', ';', '?', '!');
	
	public static final int knnOutputLayerSize = knnOutputLayer.size();
	
	public static final String knnResourcePath = "res/knnMaterial";
	public static final String knnSerializationPath = "res/knn.ser";
	
}
