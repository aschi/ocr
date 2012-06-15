package ch.zhaw.ocr.nn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.nn.helper.MatrixHelper;

/**
 * Class representing the used NeuralNetwork. Consists of two theta matrices.
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
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
			File f = new File(Properties.nnSerializationPath);
			if(f.exists()){
				loadNeuronalNetwork(f);
			}else{
				buildNeuronalNetwork(new File(Properties.nnResourcePath));
			}
		}else if(mode.equals("rebuild")){
			buildNeuronalNetwork(new File(Properties.nnResourcePath));
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
		
		consoleText.append("Pos: " + bigestPos + " Char: " + Properties.nnOutputLayer.get(bigestPos) + " Emphasis: "+ bigest + "\n");
		if(bigest < 0.2){
			return Properties.unknownChar;
		}else{
			return Properties.nnOutputLayer.get(bigestPos);	
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
		Matrix[] thetas = MatrixHelper.unmergeThetas(m, Properties.nnInputLayerSize, Properties.nnHiddenLayerSize, Properties.nnOutputLayerSize);
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
		NeuralNetworkTraining nnt = new NeuralNetworkTraining(resourceFolder);
		nnt.trainNetwork(this, "random", Properties.nnMaxIterationCount);
	}
	
	
	/**
	 * Get theta matrix 1
	 * @return theta 1
	 */
	public Matrix getTheta1() {
		return theta1;
	}

	/**
	 * Set theta matrix 1
	 * @param theta1 new theta 1
	 */
	public void setTheta1(Matrix theta1) {
		this.theta1 = theta1;
	}

	/**
	 * Get theta matrix 2
	 * @return theta 2
	 */
	public Matrix getTheta2() {
		return theta2;
	}

	/**
	 * Set theta matrix 2
	 * @param theta2 new theta 2
	 */
	public void setTheta2(Matrix theta2) {
		this.theta2 = theta2;
	}
}
