package ch.zhaw.ocr.Dictionary;

import java.io.File;
import java.io.IOException;

public class DictionaryTest {
	public static void main(String[] args) {
		try {
			Dictionary dict = new Dictionary(new File("res/german.dic"));
			System.out.println(dict.correctWord("qute"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
