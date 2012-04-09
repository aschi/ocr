package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

public interface BitmapParser {
	public void parse(BufferedImage image);
	public List<ContrastMatrix> getMatrices();
	public ContrastMatrix getMatrix(int i);
	public void setMatrices(List<ContrastMatrix> matrices);
}
