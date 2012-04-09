package ch.zhaw.ocr.CharacterRecognition;

import ch.zhaw.ocr.BitmapParser.ContrastMatrix;

public class Character implements Comparable<Character>{
	private ContrastMatrix characterM;
	private int[] comparisonVector = new int[29];
	private int[] emphasisVector = new int[29];

	/*
	 * comparisonVector: Split character matrix into 25 parts (5x5) 
	 * 0-24: Number of relevant pixels in each of the 10 parts. scaled to 10 
	 * 25: Number of relevant pixels in top half. scaled to 10 
	 * 26: Number of relevant pixels in bottom half. scaled to 10 
	 * 27: width / height ratio scaled to 10
	 * 28: indicator for vertically disjoint character (like i or j) (0 or 1)
	 */
	
	/**
	 * TODO: Probably implement comparable representative of char
	 * 
	 * @param characterM
	 */
	public Character(ContrastMatrix characterM) {
		setEmphasis();
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
			for (int i = 0; i < 25; i++) {
				comparisonVector[i] = (int)Math.round(((double)255 * (double)comparisonVector[i]) / ((double)fh * (double)fw));
			}

			// scale top / bottom to 25
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
	
	//All emphasises have to bet set
	//The emphasises are set for each field from comparison Vector
	private void setEmphasis() {
		/*
		 * comparisonVector: Split character matrix into 25 parts (5x5) 
		 * 0-24: Number of relevant pixels in each of the 10 parts. scaled to 10 
		 * 25: Number of relevant pixels in top half. scaled to 10 
		 * 26: Number of relevant pixels in bottom half. scaled to 10 
		 * 27: width / height ratio scaled to 10
		 * 28: indicator for vertically disjoint character (like i or j) (0 or 1)
		 */
		//0-24 should have the same emphasis
		for (int i = 0; i <= 24; i++) {
			emphasisVector[i] = 1;
		}
		//25/26: should have a larger emphasis than fields 0-24
		emphasisVector[25] = 10;
		emphasisVector[26] = emphasisVector[25];
		//27: should have nearly the same emphasis like field 25/26
		emphasisVector[27] = 15;
		//28: should have the highest emphasis, because there can't be f.e. an "a" with a disjointed character
		emphasisVector[28] = 1000;
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
		
		int diff = 0;
		
		for(int i = 0;i < comparisonVector.length;i++){
			if(comparisonVector[i] != ((Character)o).getComparisonVector()[i]){
				diff+= Math.abs(comparisonVector[i]-((Character)o).getComparisonVector()[i]) * emphasisVector[i];
			}
		}
		
		if(diff < 1300){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public int compareTo(Character o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
