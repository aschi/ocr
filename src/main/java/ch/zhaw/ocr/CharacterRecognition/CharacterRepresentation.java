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

	
	/*
	public CharacterRepresentation(ContrastMatrix characterM) {
		this.characterM = characterM;

		if (characterM.getFunctionalChar() != null) {
			//handle functional characters
			for(int i = 0;i < comparisonVector.length;i++){
				comparisonVector[0][i] = characterM.getFunctionalChar().getCharacter();
			}
		} else {
			//default character preprocessing
			characterM.trim();

			int fh = (int) Math.round(((double) characterM.getHeight())
					/ (double) 5);
			int fw = (int) Math.round(((double) characterM.getWidth())
					/ (double) 5);

			int mid = characterM.getHeight() / 2;

			boolean emptyRow;

			int part = 0;

			if (fh != 0 && fw != 0) {
				for (int y = 0; y < characterM.getHeight(); y++) {
					emptyRow = true;
					for (int x = 0; x < characterM.getWidth(); x++) {
						if (characterM.getValue(x, y) == 1) {
							emptyRow = false;
						}

						// correct "overflowing" pixels (will be in the
						// "last part")
						int yc = (y >= fh * 5) ? fh * 5 - 1 : y;
						int xc = (x >= fw * 5) ? fw * 5 - 1 : x;

						part = (int) Math.floor(yc / fh) * 5
								+ (int) Math.floor(xc / fw);

						comparisonVector[0][part] += characterM.getValue(x, y);

						// top / bottom half
						if (y < mid) {
							comparisonVector[0][25] += characterM.getValue(x, y);
						} else {
							comparisonVector[0][26] += characterM.getValue(x, y);
						}
					}

					if (emptyRow) {
						comparisonVector[0][28] = 1;
					}
				}

				// scale parts to 255
				for (int i = 0; i < 25; i++) {
					comparisonVector[0][i] = comparisonVector[0][i] = comparisonVector[0][i] / (fh*fw);
				}

				// scale top / bottom to 25
				comparisonVector[0][25] = (comparisonVector[0][25])/ ((characterM.getHeight() * characterM.getWidth()) / 2);
				comparisonVector[0][26] = (comparisonVector[0][26])/ ((characterM.getHeight() * characterM.getWidth()) / 2);

				comparisonVector[0][27] = ((characterM.getHeight() > characterM.getWidth()) 
								? ((double) characterM.getWidth()) / ((double) characterM.getHeight())
								: ((double) characterM.getHeight())	/ ((double) characterM.getWidth()));

			}
		}

	}
		*/
	public ContrastMatrix getMatrix() {
		return characterM;
	}

	public double[][] getComparisonVector() {
		return comparisonVector;
	}
}
