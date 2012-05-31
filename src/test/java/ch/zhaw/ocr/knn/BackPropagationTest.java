package ch.zhaw.ocr.knn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.BitmapParser.BitmapParser;
import ch.zhaw.ocr.BitmapParser.CharacterParser;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;
import ch.zhaw.ocr.BitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.CharacterRecognition.CharacterRepresentation;
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
		expectedOutput = new ArrayList<Integer>();
		
		BitmapParser bp = new CharacterParser(new SimpleBitmapParser());
		
		try {
			int i = 0;
			for(ContrastMatrix cm : bp.parse(ImageIO.read(new File("img/sans_learning.png")))){
				cm.trim();
				System.out.println(new CharacterRepresentation(cm).getComparisonVector());
				in.add(MatrixFactory.createMatrix(new CharacterRepresentation(cm).getComparisonVector()));
				expectedOutput.add(i);
				i++;
			}
			System.out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		
		for (int i = 0; i < Properties.knnOutputLayerSize; i++) {
			in.add(MatrixFactory.random(1, 400));
			expectedOutput.add(i);
		}*/
		
		
		
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
						theta1, theta2)), 500, true);

		Matrix[] thetas = MatrixHelper.unmergeThetas(
				MatrixHelper.convertToMatrix(minimizeFunction),
				Properties.knnInputLayerSize, Properties.knnHiddenLayerSize,
				Properties.knnOutputLayerSize);

		NeuralNetwork nn = new NeuralNetwork(thetas[0], thetas[1]);
		System.out.println(nn.detectCharacter(in.get(10)));
		System.out.println(nn.detectCharacter(in.get(25)));
		System.out.println(nn.detectCharacter(in.get(45)));
		System.out.println(nn.detectCharacter(in.get(72)));
	}
}
