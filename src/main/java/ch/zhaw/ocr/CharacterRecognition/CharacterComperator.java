package ch.zhaw.ocr.CharacterRecognition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import ch.zhaw.ocr.Property;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;
import ch.zhaw.ocr.NeuronalNetwork.Neuron;
import ch.zhaw.ocr.NeuronalNetwork.NeuronalNetwork;

public class CharacterComperator {
	private NeuronalNetwork<Character, String> characterRecognitionNetwork;

	public CharacterComperator() throws IOException {
		characterRecognitionNetwork = new NeuronalNetwork<Character, String>();
		
		File f = new File(Property.knnSerializationPath);
		if(f.exists()){
				System.out.println("deserialize knn...");
				deserializeKNN(f);
		}else{
			System.out.println("create knn...");
			InitialLearning.learn(this);
		}
		
	}

	public void learn(List<Character> input, List<String> chars, double emphasis) {
		if (input.size() != chars.size()) {
			throw new IllegalArgumentException(
					"List dimensions must agree! input: " + input.size()
							+ ", chars: " + chars.size());
		}

		for (int i = 0; i < input.size(); i++) {
			characterRecognitionNetwork.addNeuron(input.get(i), chars.get(i),
					emphasis);
		}
	}

	public String detectCharacter(Character c) {
		if (c.getMatrix().getFunctionalChar() != null) {
			return c.getMatrix().getFunctionalChar().toString();
		} else {

			// System.out.println(c.getMatrix());

			double highestEmph = 0;
			String rv = "";
			Set<Neuron<Character, String>> subset = characterRecognitionNetwork
					.getNeuronsFromSource(c);
			if (!subset.isEmpty()) {
				for (Neuron<Character, String> n : subset) {
					// System.out.println(n.getTarget() + " - " +
					// n.getEmphasis());
					if (n.getEmphasis() > highestEmph) {
						rv = n.getTarget();
						highestEmph = n.getEmphasis();
					}
				}
			} else {
				// System.out.println("---");
				// System.out.println(c.getMatrix());
				// for(int i = 0;i < c.getComparisonVector().length;i++){
				// System.out.println("["+i+"]: " + c.getComparisonVector()[i]);
				// }
				//
				//
				// System.out.println("not found");
				// Set<Neuron<Character, String>> nset =
				// characterRecognitionNetwork.getNeuronsFromTarget("i");
				// for(Neuron<Character, String> n : nset){
				// for(int i = 0;i <
				// n.getSource().getComparisonVector().length;i++){
				// System.out.println("["+i+"]: " +
				// n.getSource().getComparisonVector()[i]);
				// }
				// System.out.println(n.getSource().getMatrix());
				// }

			}
			return rv;
		}
	}

	public void deserializeKNN(File f) throws IOException {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(f));
			String line = null; // not declared within while loop
			while ((line = input.readLine()) != null) {
				for (String entry : line.split("###")) {
					if (!entry.trim().isEmpty()) {
						String[] eArray = entry.split("##");

						
						// load contrast matrix
						String[] cmArr = eArray[0].split(",");
						ContrastMatrix cm = new ContrastMatrix(
								cmArr[0].length(), cmArr.length);
						
						int x = 0;
						int y = 0;
						
						for (String row : cmArr) {
							x = 0;
							for (char c : row.toCharArray()) {
								cm.setValue(x, y,
										Integer.parseInt(String.valueOf(c)));
								x++;
							}
							y++;
						}

						// add neuron
						characterRecognitionNetwork.addNeuron(
								new Character(cm), eArray[1],
								Double.parseDouble(eArray[2]));
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			input.close();
		}

	}

	public void serializeKNN(File f) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f),
					100 * 1024);

			for (Neuron<Character, String> n : characterRecognitionNetwork
					.getNeuronSet()) {
				ContrastMatrix cm = n.getSource().getMatrix();
				for (int y = 0; y < cm.getHeight(); y++) {
					for (int x = 0; x < cm.getWidth(); x++) {
						bw.write(String.valueOf(cm.getValue(x, y)));
					}
					if (y + 1 < cm.getHeight()) {
						bw.write(',');
					}
				}
				bw.write("##");
				bw.write(n.getTarget());
				bw.write("##");
				bw.write(Double.toString(n.getEmphasis()));
				bw.write("###");
				bw.flush();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
