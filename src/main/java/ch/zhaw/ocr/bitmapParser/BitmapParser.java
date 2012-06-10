package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Interface BitmapParser
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public interface BitmapParser {
	/**
	 * Parse a given BufferedImage and return a list of ContrastMatrices
	 * @param image BufferedImage
	 * @return List of contrast matrices
	 */
	public List<ContrastMatrix> parse(BufferedImage image);
}
