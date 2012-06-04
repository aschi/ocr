package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Removes 'freestanding' pixels
 * 
 * @author adrian
 */

public class DistortionRemover extends BitmapParserDecorator {

	public DistortionRemover(BitmapParser bp) {
		super(bp);
	}

	@Override
	public List<ContrastMatrix> parse(BufferedImage image) {
		List<ContrastMatrix> matrices = super.parse(image);

		for (ContrastMatrix cm : matrices) {
			if (cm.getFunctionalChar() == null) {
				for (int y = 0; y < cm.getHeight(); y++) {
					for (int x = 0; x < cm.getWidth(); x++) {
						if (surroundingPixels0(cm, x, y)) {
							cm.setValue(x, y, 0);
						}
					}
				}
			}

		}

		return matrices;
	}

	private boolean surroundingPixels0(ContrastMatrix cm, int x, int y) {
		for (int yl = y - 1; yl <= y + 1; yl++) {
			for (int xl = x - 1; xl <= x + 1; xl++) {
				if(xl < cm.getWidth() && xl >= 0 && yl < cm.getHeight() && yl >= 0 && !(xl == x && yl == y)){
					if(cm.getValue(xl, yl) != 0){
						return false;
					}
				}
			}
		}
		return true;
	}

}
