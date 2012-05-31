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
		int fh = (int) Math.floor(((double) characterM.getHeight())
				/ (double) fieldNo);
		
		int fw = (int) Math.floor(((double) characterM.getWidth())
				/ (double) fieldNo);
		
		int hRes = characterM.getHeight()-(fh*fieldNo);
		int wRes = characterM.getWidth() -(fw*fieldNo);
		
		for(int row = 0; row < fieldNo;row++){
			for(int col = 0; col < fieldNo;col++){
				int part = (row*fieldNo) + col;
				
				for(int x = (col*fw)+((wRes-(col+1))>0 ? col : 0); x < ((((col+1)*fw) + ((wRes-(col+1))>0 ? 1 : 0)));x++){
					for(int y = (row*fh)+((hRes-(row+1))>0 ? row : 0); y < ((((row+1)*fh) + ((hRes-(row+1))>0 ? 1 : 0)));y++){
						comparisonVector[0][part] += characterM.getValue(x, y);
					}
					
				}
				
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
