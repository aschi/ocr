package ch.zhaw.ocr;

import java.io.IOException;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.CharacterRecognition.InitialLearning;
import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.MainGui;

public class Starter {

	private static Dictionary dict = null;

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					dict = new Dictionary("production");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				CharacterComperator cc = new CharacterComperator(dict);
				InitialLearning.learn(cc);
				Ocr ocr = new Ocr(cc, dict);

				new MainGui(ocr);
			}
		});
		t2.run();
		t.run();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				dict.serializeMap();
				System.out.println("ocr is shutting down!");
			}
		});
	}
}
