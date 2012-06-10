package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;


/**
 * Abstract class BitmapParserDecorator used as base for all BitmapParser decorators
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public abstract class BitmapParserDecorator implements BitmapParser {
	private BitmapParser bp;
	
	/**
	 * Set BitmapParser
	 * @param bp
	 */
	public BitmapParserDecorator(BitmapParser bp){
		this.bp = bp;
	}
	
	/**
	 * Parse a given BufferedImage and return a list of ContrastMatrices
	 * @param image BufferedImage
	 * @return List of contrast matrices
	 */
	@Override
	public List<ContrastMatrix> parse(BufferedImage image){
		return bp.parse(image);
	}
}
