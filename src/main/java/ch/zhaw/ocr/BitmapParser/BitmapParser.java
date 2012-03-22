package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import ch.zhaw.ocr.*;

public interface BitmapParser {
	public void parse(BufferedImage image);
	public List<ContrastMatrix> getMatrices();
	public ContrastMatrix getMatrix(int i);
	public void setMatrices(List<ContrastMatrix> matrices);
}
