package ch.zhaw.ocr.BitmapParser;

/**
 * ContrastMatrix
 * 
 * @author adrian
 * 
 */
public class ContrastMatrix {
	private int[][] contrastMatrix;
	private FunctionalCharacter functionalChar = null;
	
	/**
	 * Initialise an empty ContrastMatrix using the given height + width
	 * 
	 * @param width
	 * @param height
	 */
	public ContrastMatrix(int width, int height) {
		contrastMatrix = new int[width][height];
	}

	/**
	 * Allows to represent a functional character
	 * 
	 */
	public ContrastMatrix(FunctionalCharacter functionalChar){
		contrastMatrix = new int[1][1];
		this.functionalChar = functionalChar;
	}
	
	/**
	 * Return the string representative of the current object
	 */
	public String toString() {
		if(functionalChar != null){
			return functionalChar.toString();
		}else{
			StringBuffer sb = new StringBuffer();
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					sb.append(contrastMatrix[x][y]
							+ ((x != getWidth() - 1) ? " " : ""));
				}
				sb.append(y != getHeight() ? System.getProperty("line.separator")
						: "");
			}
			return sb.toString();
		}
	}

	/**
	 * Get a value
	 * 
	 * @param x
	 *            X-Coordinate
	 * @param y
	 *            Y-Coordinate
	 * @return value value of the field
	 */
	public int getValue(int x, int y) {
		return contrastMatrix[x][y];
	}

	public int getWidth() {
		return contrastMatrix.length;
	}

	public int getHeight() {
		return contrastMatrix[0].length;
	}
	
	public FunctionalCharacter getFunctionalChar(){
		return functionalChar;
	}

	/**
	 * Set a matrix value
	 * 
	 * @param x
	 *            X-Coordinate
	 * @param y
	 *            Y-Coordinate
	 * @param value
	 *            value to set
	 */
	public void setValue(int x, int y, int value) {
		contrastMatrix[x][y] = value;
	}

	/**
	 * Delete a column from the matrix
	 * 
	 * @param colNo
	 *            column to delete
	 */
	public void removeCol(int colNo) {
		int[][] newMatrix = new int[getWidth() - 1][getHeight()];
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (x != colNo) {
					newMatrix[(x >= colNo) ? x - 1 : x][y] = contrastMatrix[x][y];
				}
			}
		}
		this.contrastMatrix = newMatrix;
	}

	/**
	 * Delete a row from the matrix
	 * 
	 * @param rowNo
	 *            row to delete
	 */
	public void removeRow(int rowNo) {
		int[][] newMatrix = new int[getWidth()][getHeight() - 1];
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				if (y != rowNo) {
					newMatrix[x][(y >= rowNo) ? y - 1 : y] = contrastMatrix[x][y];
				}
			}
		}
		this.contrastMatrix = newMatrix;
	}

	/**
	 * Invert the whole matrix (1 => 0 ; 0 => 1)
	 */
	public void invertMatrix() {
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				setValue(x, y, getValue(x, y) == 1 ? 0 : 1);
			}
		}
	}

	/**
	 * Get a sub matrix
	 * 
	 * @param offsetX
	 * @param offsetY
	 * @param width
	 * @param height
	 * @return
	 */
	public ContrastMatrix getSubMatrix(int offsetX, int offsetY, int width,
			int height) {
		// avoid array erros ... TODO: exception?
		if ((offsetX + width) > getWidth() && (offsetY + height) > getHeight()) {
			return null;
		}

		ContrastMatrix rv = new ContrastMatrix(width, height);

		// copy values
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rv.setValue(x, y, getValue(offsetX + x, offsetY + y));
			}
		}

		return rv;
	}


	/**
	 * Remove empty leading & tailing rows / cols
	 */
	public void trim() {
		boolean isEmpty;
	
		// remove empty leading cols
		isEmpty = true;
		while(isEmpty){
			for (int y = 0; y < getHeight(); y++) {
				if (getValue(0, y) != 0) {
					isEmpty = false;
				}
			}

			if (isEmpty) {
				removeCol(0);
			}
		}

		// remove empty tailing cols
		isEmpty = true;
		while (isEmpty) {
			for (int y = 0; y < getHeight(); y++) {
				if (getValue(getWidth()-1, y) != 0) {
					isEmpty = false;
				}
			}

			if (isEmpty) {
				removeCol(getWidth()-1);
			}
		}

		// remove empty leading rows
		isEmpty = true;
		while(isEmpty){
			for (int x = 0; x < getWidth(); x++) {
				if (getValue(x, 0) != 0) {
					isEmpty = false;
				}
			}

			if (isEmpty) {
				removeRow(0);
			}
		}
		
		

		// remove empty tailing rows
		isEmpty = true;
		while (isEmpty) {
			for (int x = 0; x < getWidth(); x++) {
				if (getValue(x, getHeight()-1) != 0) {
					isEmpty = false;
				}
			}

			if (isEmpty) {
				removeRow(getHeight()-1);
			}
		}
	}



}
