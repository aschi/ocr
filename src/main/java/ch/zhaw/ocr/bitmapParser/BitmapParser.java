package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

public interface BitmapParser {
	public List<ContrastMatrix> parse(BufferedImage image);
}
