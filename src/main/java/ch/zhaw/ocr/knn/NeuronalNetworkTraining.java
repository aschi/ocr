package ch.zhaw.ocr.knn;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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

public class NeuronalNetworkTraining {
	private BackPropagation bp;
	private List<Matrix> in;
	private List<Integer> expectedOutput;
	private Matrix theta1;
	private Matrix theta2;
	private File resourceFolder;

	public NeuronalNetworkTraining(File resourceFolder) {
		this.resourceFolder = resourceFolder;
		bp = new BackPropagation();
	}

	/**
	 * Trains a neural network using the files in the given resourceFolder. These files should contain 1 character for each output neuron
	 * @param nn neural network to be trained
	 * @param thetaMode
	 *            "random" = create new random thetas.
	 */
	public void trainNetwork(NeuralNetwork nn, String thetaMode) {
		// initialize thetas
		if (thetaMode.equals("random") || nn.getTheta1() == null
				|| nn.getTheta2() == null) {
			// initialize thetas randomly
			theta1 = bp.randInitializeWeights(Properties.knnHiddenLayerSize,
					Properties.knnInputLayerSize);
			theta2 = bp.randInitializeWeights(Properties.knnOutputLayerSize,
					Properties.knnHiddenLayerSize);
		} else {
			theta1 = nn.getTheta1();
			theta2 = nn.getTheta2();
		}

		// get input from resource folder
		BitmapParser bmp = new CharacterParser(new SimpleBitmapParser());

		in = new ArrayList<Matrix>();
		expectedOutput = new ArrayList<Integer>();

		int i = 0;

		try {
			for (File f : resourceFolder.listFiles()) {
				i = 0;
				if (f.getName().length() > 4
						&& f.getName().substring(f.getName().length() - 3)
								.equals("jpg")
						|| f.getName().substring(f.getName().length() - 3)
								.equals("png")) {
					
					for (ContrastMatrix cm : bmp.parse(ImageIO.read(f))) {
						cm.trim();
						in.add(MatrixFactory
								.createMatrix(new CharacterRepresentation(cm)
										.getComparisonVector()));
						expectedOutput.add(i);
						i++;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// declare cost function in an "minimizable" form
		CostFunction inlineFunction = new CostFunction() {
			@Override
			public Tuple<Double, DoubleVector> evaluateCost(DoubleVector input) {

				CostFunctionResult rv = bp.nnCostFunction(
						MatrixHelper.convertToMatrix(input),
						Properties.knnInputLayerSize,
						Properties.knnHiddenLayerSize,
						Properties.knnOutputLayerSize, in, expectedOutput, Properties.knnLambda);

				return new Tuple<Double, DoubleVector>(rv.getJ(),
						MatrixHelper.convertToDoubleVector(rv.getTheta1Grad()));
			}
		};

		// minimize function using the Fmincg implementation of thomas jungblut
		DoubleVector minimizeFunction = Fmincg.minimizeFunction(inlineFunction,
				MatrixHelper.convertToDoubleVector(MatrixHelper.mergeThetas(
						theta1, theta2)), Properties.knnMaxIterationCount, true);

		// read thetas
		Matrix[] thetas = MatrixHelper.unmergeThetas(
				MatrixHelper.convertToMatrix(minimizeFunction),
				Properties.knnInputLayerSize, Properties.knnHiddenLayerSize,
				Properties.knnOutputLayerSize);

		// write thetas back to the given neuronal network
		nn.setTheta1(thetas[0]);
		nn.setTheta2(thetas[1]);
	}
}
