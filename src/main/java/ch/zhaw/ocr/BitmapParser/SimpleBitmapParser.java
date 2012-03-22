package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import ch.zhaw.ocr.ContrastMatrix;

public class SimpleBitmapParser implements BitmapParser{
	private List<ContrastMatrix> matrices;
	
	public SimpleBitmapParser() {
		matrices = new LinkedList<ContrastMatrix>();
	}
	
	@Override
	/**
	 * Parses an image & creates a ContrastMatrix based on the picture
	 * 
	 * @param image the image to be parsed
	 * @return ContrastMatrix representing the image
	 */
	public void parse(BufferedImage image) {
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

		matrices.add(rv);
	}

	@Override
	public List<ContrastMatrix> getMatrices() {
		return matrices;
	}

	@Override
	public ContrastMatrix getMatrix(int i) {
		return matrices.get(i);
	}

	@Override
	public void setMatrices(List<ContrastMatrix> matrices) {
		this.matrices = matrices;
	}
	
}
