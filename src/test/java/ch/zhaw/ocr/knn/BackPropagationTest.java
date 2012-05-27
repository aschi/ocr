package ch.zhaw.ocr.knn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.knn.helper.CostFunctionResult;



public class BackPropagationTest {
	private Matrix theta1;
	private Matrix theta2;
	private BackPropagation bp;
	private List<Matrix> input;
	private List<Integer> expectedOutput;
	
	@Before
	public void setUp(){
		bp = new BackPropagation();
		theta1 = bp.randInitializeWeights(Properties.knnHiddenLayerSize, Properties.knnInputLayerSize);
		theta2 = bp.randInitializeWeights(Properties.knnOutputLayerSize, Properties.knnHiddenLayerSize);
		
		input = new ArrayList<Matrix>();
		input.add(MatrixFactory.random(1, 400));
		//input.add(MatrixFactory.random(1, 400));
		//input.add(MatrixFactory.random(1, 400));
		
		expectedOutput = new ArrayList<Integer>();
		expectedOutput.add(1);
		//expectedOutput.add(25);
		//expectedOutput.add(35);
	}

	@Test
	public void backPropagationTest(){
		CostFunctionResult result = null;
		for(int i = 0;i < 10;i ++){
			result = bp.nnCostFunction(theta1, theta2, Properties.knnInputLayerSize, Properties.knnHiddenLayerSize, Properties.knnOutputLayerSize, input, expectedOutput, 1);		
			theta1 = MatrixFactory.copy(result.getTheta1Grad());
			theta2 = MatrixFactory.copy(result.getTheta2Grad());
			System.out.println(result);
		}
		
		NeuronalNetwork nn = new NeuronalNetwork(theta1, theta2);
		System.out.println(nn.analyseChar(input.get(0)));
		
	}
}
