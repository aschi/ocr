package ch.zhaw.ocr.CharacterRecognition;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.BitmapParser.ContrastMatrix;

public class CharacterRepresentation {
	private ContrastMatrix characterM;
	private double[][] comparisonVector = new double[1][Properties.knnInputLayerSize];

	private int fieldNo = (int) Math.sqrt(Properties.knnInputLayerSize);

	/*
	 * comparisonVector: Split character matrix into 400 parts (20x20)
	 */

	public CharacterRepresentation(ContrastMatrix characterM) {

		int fh = (int) Math.round(((double) characterM.getHeight())
				/ (double) fieldNo);
		int fw = (int) Math.round(((double) characterM.getWidth())
				/ (double) fieldNo);
		
		fh = (fh == 0) ? 1 : fh;
		fw = (fw == 0) ? 1 : fw;

		System.out.println(characterM.getHeight());
		System.out.println(characterM.getWidth());
		
		int fieldSize = fh * fw;

		for (int y = 0; y < characterM.getHeight(); y++) {
			for (int x = 0; x < characterM.getWidth(); x++) {
				// correct "overflowing" pixels (will be in the
				// "last part")
				int yc = (y >= fh * fieldNo) ? fh * fieldNo - 1 : y;
				int xc = (x >= fw * fieldNo) ? fw * fieldNo - 1 : x;

				int part = (int) Math.floor(yc / fh) * 5
						+ (int) Math.floor(xc / fw);

				comparisonVector[0][part] += characterM.getValue(x, y);
			}
		}

		for (int i = 0; i < comparisonVector.length; i++) {
			comparisonVector[0][i] = comparisonVector[0][i] / fieldSize;
		}

	}

	public ContrastMatrix getMatrix() {
		return characterM;
	}

	public double[][] getComparisonVector() {
		return comparisonVector;
	}
}
