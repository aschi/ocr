package ch.zhaw.ocr.Dictionary;

import java.io.File;
import java.io.IOException;

public class DictionaryTest {
	public static void main(String[] args) {
		try {
			long t1 = System.currentTimeMillis();
			Dictionary dict = new Dictionary(new File("res/german.dic"));
			System.out.println("dictionary loaded...(" + (System.currentTimeMillis()-t1) +"ms)");
			long t2 = System.currentTimeMillis();
			System.out.println(dict.correctWord("qute") + " ("+ (System.currentTimeMillis()-t2) +"ms)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
