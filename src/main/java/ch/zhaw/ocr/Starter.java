package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.MainGui;

public class Starter {

	private static Dictionary dict = null;
	private static CharacterComperator cc = null;
	private static Ocr ocr = null;

	public static void main(String[] args) {
//		Thread t = new Thread(new Runnable() {
//			public void run() {
				try {
					dict = new Dictionary("production");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
//		});
		

//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
				try {
					cc = new CharacterComperator();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ocr = new Ocr(cc, dict);

				new MainGui(ocr);
//			}
//		});
//		t2.run();d
//		t.run();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				dict.serializeMap(new File(Properties.dictionaryMapSerializiationPath));
				cc.serializeKNN(new File(Properties.knnSerializationPath));
				System.out.println("ocr is shutting down!");
			}
		});
	}
}
