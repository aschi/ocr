package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

public interface BitmapParser {
	public List<ContrastMatrix> parse(BufferedImage image);
}
