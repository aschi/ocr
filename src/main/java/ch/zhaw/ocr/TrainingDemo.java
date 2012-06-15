package ch.zhaw.ocr;

import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.bitmapParser.BitmapParser;
import ch.zhaw.ocr.bitmapParser.CharacterParser;
import ch.zhaw.ocr.bitmapParser.ContrastMatrix;
import ch.zhaw.ocr.bitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.nn.CharacterRepresentation;
import ch.zhaw.ocr.nn.NeuralNetwork;
import ch.zhaw.ocr.nn.NeuralNetworkTraining;

public class TrainingDemo {
	private BitmapParser bp;
	private NeuralNetwork nn;	
	private NeuralNetworkTraining nnt;
	
	public static void main(String[] args) {
		try {
			new TrainingDemo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public TrainingDemo() throws IOException {
		bp = new CharacterParser(new SimpleBitmapParser());
		nnt = new NeuralNetworkTraining(new File("img/demoTrainingMaterial"));
			
		List<ContrastMatrix> matrices = bp.parse(ImageIO.read(new File("img/test.png")));
		
		//untrainiert
		System.out.println("Step 1: Untrained network");
		pause();
		nn = new NeuralNetwork("empty");
		nnt.trainNetwork(nn, "random", 0);
		
		parseList(matrices);
		
		pause();
		//5 iterationen
		System.out.println("Step 2: 5 Iterations");
		pause();
		//nn = new NeuralNetwork("empty");
		nnt.trainNetwork(nn, "random", 5);

		parseList(matrices);
		
		pause();
		//20 iterationen
		System.out.println("Step 3: 20 iterations");
		pause();
		//nn = new NeuralNetwork("empty");
		nnt.trainNetwork(nn, "random", 20);
		
		parseList(matrices);
		pause();
		//production
		System.out.println("Step 4: 500 iterations");
		pause();
		nn = new NeuralNetwork("production");
		parseList(matrices);
	}
	
	private void parseList(List<ContrastMatrix> matrices){
		StringBuffer output = new StringBuffer();
		
		for(ContrastMatrix cm : matrices){
			nn.detectCharacter(MatrixFactory.createMatrix(new CharacterRepresentation(cm).getComparisonVector()), output);
		}
		System.out.println(output);
	}
	
	private void pause(){
		System.out.println("Press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
	
	
}
