package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.CharacterRecognition.CharacterComperator;
import ch.zhaw.ocr.Dictionary.Dictionary;
import ch.zhaw.ocr.TextRecognation.Ocr;
import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.knn.NeuralNetwork;

public class Starter {

	private static Dictionary dict = null;
	private static NeuralNetwork nn = null;
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
					nn = new NeuralNetwork("production");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ocr = new Ocr(nn, dict);

				new MainGui(ocr);
//			}
//		});
//		t2.run();d
//		t.run();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					dict.serializeMap(new File(Properties.dictionaryMapSerializiationPath));
					nn.saveNeuronalNetwork(new File(Properties.knnSerializationPath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error while saving the ocr");
					e.printStackTrace();
				}
				System.out.println("ocr is shutting down!");
			}
		});
	}
}
