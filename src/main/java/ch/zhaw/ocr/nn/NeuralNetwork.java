package ch.zhaw.ocr.nn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.nn.helper.MatrixHelper;

public class NeuralNetwork {
	private Matrix theta1;
	private Matrix theta2;

	
	public NeuralNetwork(Matrix theta1, Matrix theta2) {
		super();
		this.theta1 = theta1;
		this.theta2 = theta2;
	}
	
	/**
	 * Create a neural network. The "mode" defines the build method to be used.
	 * @param mode valid values: "production" - loads the knn from a configured file if possible, "rebuild" - rebuilds the knn
	 * @throws IOException 
	 */
	public NeuralNetwork(String mode) throws IOException{
		if(mode.equals("production")){
			File f = new File(Properties.knnSerializationPath);
			if(f.exists()){
				loadNeuronalNetwork(f);
			}else{
				buildNeuronalNetwork(new File(Properties.knnResourcePath));
			}
		}else if(mode.equals("rebuild")){
			buildNeuronalNetwork(new File(Properties.knnResourcePath));
		}
	}

	
	/**
	 * Use the trained neural network to align an input vector with a character
	 * @param input
	 * @return character
	 */
	public char detectCharacter(Matrix input, StringBuffer consoleText) {
		double bigest = 0;
		int bigestPos = 0;
		
		Matrix a1 = MatrixFactory.copy(input);
		a1 = MatrixHelper.add1ToVector(a1, "horizontal");

		Matrix z2 = theta1.mul(a1.transpose());
		Matrix a2 = MatrixHelper.sigmoid(z2);
		a2 = MatrixHelper.add1ToVector(a2, "vertical");

		Matrix z3 = theta2.mul(a2);
		Matrix a3 = MatrixHelper.sigmoid(z3);
		
		//find the "correct" result
		for(int i = 0; i < a3.getRowCount();i++){
			if(a3.get(i, 0) > bigest){
				bigest = a3.get(i, 0);
				bigestPos = i;
			}
		}
		
		consoleText.append("Pos: " + bigestPos + " Char: " + Properties.knnOutputLayer.get(bigestPos) + " Emphasis: "+ bigest + "\n");
		if(bigest < 0.5){
			return Properties.unknownChar;
		}else{
			return Properties.knnOutputLayer.get(bigestPos);	
		}
	}
	
	/**
	 * Loads neural network values (thetas) from a given file
	 * 
	 * @param f
	 * @throws IOException
	 */
	private void loadNeuronalNetwork(File f) throws IOException{
		Matrix m;
		m = MatrixHelper.deserializeMatrix(f);
		Matrix[] thetas = MatrixHelper.unmergeThetas(m, Properties.knnInputLayerSize, Properties.knnHiddenLayerSize, Properties.knnOutputLayerSize);
		this.theta1 = thetas[0];
		this.theta2 = thetas[1];
	}
	
	/**
	 * Saves the neural network to a given file
	 * @param f
	 * @throws IOException 
	 */
	public void saveNeuronalNetwork(File f) throws IOException{
		MatrixHelper.serializeMatrix(MatrixHelper.mergeThetas(theta1, theta2), f);
	}
	
	/**
	 * Builds the neural network using the NeuronalNetworkTraining class
	 * @param resourceFolder
	 */
	private void buildNeuronalNetwork(File resourceFolder){
		NeuronalNetworkTraining nnt = new NeuronalNetworkTraining(resourceFolder);
		nnt.trainNetwork(this, "random");
	}

	public Matrix getTheta1() {
		return theta1;
	}

	public void setTheta1(Matrix theta1) {
		this.theta1 = theta1;
	}

	public Matrix getTheta2() {
		return theta2;
	}

	public void setTheta2(Matrix theta2) {
		this.theta2 = theta2;
	}
}
