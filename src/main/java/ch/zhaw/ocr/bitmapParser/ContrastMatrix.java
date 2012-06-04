package ch.zhaw.ocr.bitmapParser;

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
	public ContrastMatrix(FunctionalCharacter functionalChar) {
		contrastMatrix = new int[1][1];
		this.functionalChar = functionalChar;
	}

	/**
	 * Return the string representative of the current object
	 */
	public String toString() {
		if (functionalChar != null) {
			return functionalChar.toString();
		} else {
			StringBuffer sb = new StringBuffer();
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					sb.append(contrastMatrix[x][y]
							+ ((x != getWidth() - 1) ? " " : ""));
				}
				sb.append(y != getHeight() ? System
						.getProperty("line.separator") : "");
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

	public int[][] getContrastMatrix() {
		return contrastMatrix;
	}

	public FunctionalCharacter getFunctionalChar() {
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
	 * Check if the given row is full
	 * 
	 * @param y
	 *            row number
	 * @return indicates whether the column is full or not
	 */
	public boolean isFullRow(int y) {
		for (int x = 0; x < getWidth(); x++) {
			if (getValue(x, y) != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the given row is empty
	 * 
	 * @param y
	 *            row number
	 * @return indicates whether the column is empty or not
	 */
	public boolean isEmptyRow(int y) {
		for (int x = 0; x < getWidth(); x++) {
			if (getValue(x, y) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if any given part of the matrix is full
	 * @param startX start x coordinate of the part
	 * @param endX end x coordinate of the part
	 * @param startY start y coordinate of the part
	 * @param endY end y coordinate of the part
	 * @return indicates whether the given part is full or not
	 */
	public boolean isFull(int startX, int endX, int startY, int endY){
		startX = (startX < getWidth()) ? startX : (getWidth()-1);
		startX = (startX >= 0) ? startX : 0;
		endX = (endX < getWidth()) ? endX : (getWidth()-1);
		endX = (endX >= 0) ? endX : 0;
		
		startY = (startY < getHeight()) ? startY : (getHeight()-1);
		startY = (startY >= 0) ? startY : 0;
		endY = (endY < getHeight()) ? endY : (getHeight()-1);
		endY = (endY >= 0) ? endY : 0;
		
		for(int x = startX; x <= endX;x++){
			for(int y = startY; y <= endY;y++){
				if(getValue(x, y) != 1){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if the given column is full
	 * 
	 * @param x
	 *            column number
	 * @return indicates whether the column is full or not
	 */
	public boolean isFullCol(int x) {
		for (int y = 0; y < getHeight(); y++) {
			if (getValue(x, y) != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the given column is empty
	 * 
	 * @param x
	 *            column number
	 * @return indicates whether the column is empty or not
	 */
	public boolean isEmptyCol(int x) {
		for (int y = 0; y < getHeight(); y++) {
			if (getValue(x, y) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Remove empty leading & tailing rows / cols
	 */
	public void trim() {
		if (getFunctionalChar() == null) {
			boolean isEmpty;

			// remove empty leading cols
			isEmpty = true;
			while (isEmpty) {
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
					if (getValue(getWidth() - 1, y) != 0) {
						isEmpty = false;
					}
				}

				if (isEmpty) {
					removeCol(getWidth() - 1);
				}
			}

			// remove empty leading rows
			isEmpty = true;
			while (isEmpty) {
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
					if (getValue(x, getHeight() - 1) != 0) {
						isEmpty = false;
					}
				}

				if (isEmpty) {
					removeRow(getHeight() - 1);
				}
			}
		}
	}

	/**
	 * Equals If this object represents a functional char: Compare the stored
	 * functional char If not compare all elements of the contrastMatrix
	 */
	public boolean equals(Object o) {
		if (o instanceof ContrastMatrix) {
			ContrastMatrix co = (ContrastMatrix) o;
			if (getFunctionalChar() != null) {
				// functional char. just check functional char
				if (getFunctionalChar().equals(co.getFunctionalChar())) {
					return true;
				} else {
					return false;
				}
			} else {
				// default contrast matrix
				// check all values
				boolean equals = true;
				if (getContrastMatrix().length == co.getContrastMatrix().length
						&& getContrastMatrix()[0].length == co
								.getContrastMatrix()[0].length) {
					for (int x = 0; x < getContrastMatrix().length; x++) {
						for (int y = 0; y < getContrastMatrix()[0].length; y++) {
							if (getContrastMatrix()[x][y] != co
									.getContrastMatrix()[x][y]) {
								equals = false;
							}
						}
					}
				} else {
					equals = false;
				}
				return equals;
			}
		} else {
			// no contrast matrix
			return false;
		}
	}
}
