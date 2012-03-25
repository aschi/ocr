package ch.zhaw.ocr.CharacterRecognition;

import ch.zhaw.ocr.BitmapParser.ContrastMatrix;

public class Character implements Comparable<Character>{
	private ContrastMatrix characterM;
	private int[] comparisonVector = new int[29];

	/*
	 * comparisonVector: Split character matrix into 25 parts (5x5) 
	 * 0-24: Number of relevant pixels in each of the 25 parts. scaled to 255 
	 * 25: Number of relevant pixels in top half. scaled to 255 
	 * 26: Number of relevant pixels in bottom half. scaled to 255 
	 * 27: width / height ratio scaled to 255. again scaled to 255 
	 * 28: indicator for vertically disjoint character (like i or j) (0 or 1)
	 */
	
	/**
	 * TODO: Probably implement comparable representative of char
	 * 
	 * @param characterM
	 */
	public Character(ContrastMatrix characterM) {
		characterM.trim();
		this.characterM = characterM;

		int fh = (int)Math.round(((double)characterM.getHeight()) / (double)5);
		int fw = (int)Math.round(((double)characterM.getWidth()) / (double)5);

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

					//correct "overflowing" pixels (will be in the "last part")
					int yc = (y >= fh*5) ? fh*5-1 : y;
					int xc = (x >= fw*5) ? fw*5-1 : x;
					
					part = (int)Math.floor(yc/fh) * 5 + (int)Math.floor(xc/fw);
					
					comparisonVector[part] += characterM.getValue(x, y);

					// top / bottom half
					if (y < mid) {
						comparisonVector[25] += characterM.getValue(x, y);
					} else {
						comparisonVector[26] += characterM.getValue(x, y);
					}
				}

				if (emptyRow) {
					comparisonVector[28] = 1;
				}
			}

			// scale parts to 255
			for (int i = 0; i < 24; i++) {
				comparisonVector[i] = (int)Math.round(((double)255 * (double)comparisonVector[i]) / ((double)fh * (double)fw));
			}

			// scale top / bottom to 255
			comparisonVector[25] = (255 * comparisonVector[25])
					/ ((characterM.getHeight() * characterM.getWidth()) / 2);
			comparisonVector[26] = (255 * comparisonVector[25])
					/ ((characterM.getHeight() * characterM.getWidth()) / 2);

			comparisonVector[27] = (int)Math.round(255 * ((characterM.getHeight() > characterM
					.getWidth()) ? ((double)characterM.getWidth())
					/ ((double)characterM.getHeight()) : ((double)characterM.getHeight())
					/ ((double)characterM.getWidth())));

		}

	}

	public ContrastMatrix getMatrix() {
		return characterM;
	}
	
	public int[] getComparisonVector(){
		return comparisonVector;
	}

	/**
	 * TODO: Implement comparison :)
	 * 
	 * @return
	 */
	public boolean equals(Object o) {
		if(!(o instanceof Character)){
			return false;
		}
		
		boolean rv = true;
		for(int i = 0;i < comparisonVector.length;i++){
			if(comparisonVector[i] != ((Character)o).getComparisonVector()[i]){
				rv = false;
			}
		}
		
		return rv;
	}

	@Override
	public int compareTo(Character o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
