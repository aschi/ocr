package ch.zhaw.ocr.knn.helper;

import java.util.Arrays;
import java.util.List;

public final class NNConfiguration {
	public static final int inputLayerSize = 400;
	public static final int hiddenLayerSize = 50;
	
	public static final List<String> outputLayer = Arrays.asList("A", "B",
			"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Ä", "Ö",
			"Ü", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			"z", "ä", "ö", "ü", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", ".", ",", ":", ";", "?", "!");
	
	public static final int outputLayerSize = outputLayer.size();
}
