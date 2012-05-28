package ch.zhaw.ocr.knn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;
import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.knn.helper.MatrixHelper;

public class NeuronalNetwork {
	private Matrix theta1;
	private Matrix theta2;

	
	public NeuronalNetwork(Matrix theta1, Matrix theta2) {
		super();
		this.theta1 = theta1;
		this.theta2 = theta2;
	}
	
	/**
	 * Use the trained neuronal network to align an input vector with a character
	 * @param input
	 * @return character
	 */
	public char analyseChar(Matrix input) {
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
		
		//return char if something is found with emphasis > 0.5
		//if(bigest > 0.2){
			System.out.println("Pos: " + bigestPos + " Char: " + Properties.knnOutputLayer.get(bigestPos) + " Emphasis: "+ bigest);
			return Properties.knnOutputLayer.get(bigestPos);
		//}else{
		//	return Properties.unknownChar;
		//}
	}

	
}
