package ch.zhaw.ocr.nn;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.bitmapParser.ContrastMatrix;

/**
 * CharacterRepresentation. Used to create valid input vectors for our neural network
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public class CharacterRepresentation {
	private ContrastMatrix characterM;
	private double[][] comparisonVector = new double[1][Properties.nnInputLayerSize];

	private int fieldNo = (int) Math.sqrt(Properties.nnInputLayerSize);

	/*
	 * comparisonVector: Split character matrix into 400 parts (20x20)
	 */

	
	  	
	public CharacterRepresentation(ContrastMatrix characterM) {
		characterM.trim();
		
		int fh = (int) Math.round(((double) characterM.getHeight())
				/ (double) fieldNo);
		int fw = (int) Math.round(((double) characterM.getWidth())
				/ (double) fieldNo);
		
		fh = (fh == 0) ? 1 : fh;
		fw = (fw == 0) ? 1 : fw;
		
		int fieldSize = fh * fw;

		for (int y = 0; y < characterM.getHeight(); y++) {
			for (int x = 0; x < characterM.getWidth(); x++) {
				// correct "overflowing" pixels (will be in the
				// "last part")
				int yc = (y >= fh * fieldNo) ? fh * fieldNo - 1 : y;
				int xc = (x >= fw * fieldNo) ? fw * fieldNo - 1 : x;

				int part = (int) Math.floor(yc / fh) * fieldNo
						+ (int) Math.floor(xc / fw);

				comparisonVector[0][part] += characterM.getValue(x, y);
			}
		}

		for (int i = 0; i < comparisonVector.length; i++) {
			comparisonVector[0][i] = comparisonVector[0][i] / fieldSize;
		}

	}
	
	/**
	 * Get contrast matrix
	 * @return contrast matrix
	 */
	public ContrastMatrix getMatrix() {
		return characterM;
	}

	/**
	 * Get comparison vector (input vector of neural network)
	 * @return comparison vector
	 */
	public double[][] getComparisonVector() {
		return comparisonVector;
	}
}
