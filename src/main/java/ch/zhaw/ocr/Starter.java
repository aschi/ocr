package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.dictionary.Dictionary;
import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.nn.NeuralNetwork;
import ch.zhaw.ocr.textRecognition.Ocr;

/**
 * Main class used to start the ocr software. Initialises dictionary, neural network and starts the gui.
 * Contains a shutdown hook to save dictionary and knn data
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public class Starter {

	private static Dictionary dict = null;
	private static NeuralNetwork nn = null;
	private static Ocr ocr = null;

	public static void main(String[] args) {
				try {
					dict = new Dictionary("production");
				} catch (IOException e) {
					System.out.println("Error while loading dictionary");
					e.printStackTrace();
				}

				try {
					nn = new NeuralNetwork("production");
				} catch (IOException e) {
					System.out.println("Error while loading neuronal network");
					e.printStackTrace();
				}
				
				ocr = new Ocr(nn, dict);

				new MainGui(ocr);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					dict.serializeDictionary(new File(Properties.dictionaryMapSerializiationPath));
					nn.saveNeuronalNetwork(new File(Properties.nnSerializationPath));
				} catch (IOException e) {
					System.out.println("Error while saving the ocr");
					e.printStackTrace();
				}
				System.out.println("ocr is shutting down!");
			}
		});
	}
}
