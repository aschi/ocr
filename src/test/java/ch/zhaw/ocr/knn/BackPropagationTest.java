package ch.zhaw.ocr.knn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.knn.helper.CostFunctionResult;
import ch.zhaw.ocr.knn.helper.MatrixHelper;
import de.jungblut.math.DoubleVector;
import de.jungblut.math.minimize.CostFunction;
import de.jungblut.math.minimize.Fmincg;
import de.jungblut.math.tuple.Tuple;

public class BackPropagationTest {
	private Matrix theta1;
	private Matrix theta2;
	private BackPropagation bp;
	private List<Matrix> in;
	private List<Integer> expectedOutput;

	@Before
	public void setUp() {
		bp = new BackPropagation();
		theta1 = bp.randInitializeWeights(Properties.knnHiddenLayerSize,
				Properties.knnInputLayerSize);
		theta2 = bp.randInitializeWeights(Properties.knnOutputLayerSize,
				Properties.knnHiddenLayerSize);

		in = new ArrayList<Matrix>();
		in.add(MatrixFactory.random(1, 400));
		in.add(MatrixFactory.random(1, 400));
		in.add(MatrixFactory.random(1, 400));

		expectedOutput = new ArrayList<Integer>();
		expectedOutput.add(1);
		expectedOutput.add(25);
		expectedOutput.add(35);
	}

	@Test
	public void backPropagationTest() {

		CostFunction inlineFunction = new CostFunction() {
			@Override
			public Tuple<Double, DoubleVector> evaluateCost(DoubleVector input) {

				CostFunctionResult rv = bp.nnCostFunction(
						MatrixHelper.convertToMatrix(input),
						Properties.knnInputLayerSize,
						Properties.knnHiddenLayerSize,
						Properties.knnOutputLayerSize, in, expectedOutput, 1);

				return new Tuple<Double, DoubleVector>(rv.getJ(),
						MatrixHelper.convertToDoubleVector(rv.getTheta1Grad()));
			}
		};

		DoubleVector minimizeFunction = Fmincg.minimizeFunction(inlineFunction,
				MatrixHelper.convertToDoubleVector(MatrixHelper.mergeThetas(
						theta1, theta2)), 100, true);

		Matrix[] thetas = MatrixHelper.unmergeThetas(
				MatrixHelper.convertToMatrix(minimizeFunction),
				Properties.knnInputLayerSize, Properties.knnHiddenLayerSize,
				Properties.knnOutputLayerSize);
		
		NeuronalNetwork nn = new NeuronalNetwork(thetas[0], thetas[1]);
		System.out.println(nn.analyseChar(in.get(1)));

	}
}
