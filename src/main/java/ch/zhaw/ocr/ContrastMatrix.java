package ch.zhaw.ocr;

import java.awt.image.BufferedImage;

/**
 * ContrastMatrix
 * 
 * @author adrian
 * 
 */
public class ContrastMatrix {
	private int[][] contrastMatrix;

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
	 * Return the string representative of the current object
	 */
	public String toString() {
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
		boolean leadingEmptyRows = true;
		boolean leadingEmptyCols = true;

		boolean isRowEmpty;
		boolean isColEmpty;

		// remove empty leading cols
		for (int x = 0; x < getWidth(); x++) {
			isColEmpty = true;
			for (int y = 0; y < getHeight(); y++) {
				if (getValue(x, y) != 0) {
					isColEmpty = false;
				}
			}

			if (isColEmpty && leadingEmptyCols) {
				removeCol(0);
				x--;
			} else {
				leadingEmptyCols = false;
			}
		}

		// remove empty tailing cols
		isColEmpty = true;
		while (isColEmpty) {
			for (int y = 0; y < getHeight(); y++) {
				if (getValue(getWidth()-1, y) != 0) {
					isColEmpty = false;
				}
			}

			if (isColEmpty) {
				removeCol(getWidth()-1);
			}
		}

		// remove empty leading cols
		for (int y = 0; y < getHeight(); y++) {
			isRowEmpty = true;
			for (int x = 0; x < getWidth(); x++) {
				if (getValue(x, y) != 0) {
					isRowEmpty = false;
				}
			}

			if (isRowEmpty && leadingEmptyRows) {
				removeRow(0);
				y--;
			} else {
				leadingEmptyRows = false;
			}
		}

		// remove empty tailing rows
		isRowEmpty = true;
		while (isRowEmpty) {
			for (int x = 0; x < getWidth(); x++) {
				if (getValue(x, getHeight()-1) != 0) {
					isRowEmpty = false;
				}
			}

			if (isRowEmpty) {
				removeRow(getHeight()-1);
			}
		}
	}

	/**
	 * Parses an image & creates a ContrastMatrix based on the picture
	 * 
	 * @param image
	 *            the image to be parsed
	 * @return ContrastMatrix representing the image
	 */
	public static ContrastMatrix parseImage(BufferedImage image) {
		ContrastMatrix rv = new ContrastMatrix(image.getWidth(),
				image.getHeight());

		int brightCount = 0;
		int darkCount = 0;

		int rgb = 0;
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				rgb = image.getRGB(x, y);

				red = (rgb >> 16) & 0x000000FF;
				green = (rgb >> 8) & 0x000000FF;
				blue = (rgb) & 0x000000FF;

				// average rgb value < 200 = 1
				if ((red + blue + green) / 3 < 200) {
					rv.setValue(x, y, 1);
					darkCount++;
				} else {
					rv.setValue(x, y, 0);
					brightCount++;
				}
			}
		}

		// invert matrix if there are more dark then bright pixels
		if (darkCount > brightCount) {
			rv.invertMatrix();
		}

		return rv;
	}
}
