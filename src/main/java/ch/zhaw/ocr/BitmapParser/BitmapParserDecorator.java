package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;

import ch.zhaw.ocr.ContrastMatrix;

public abstract class BitmapParserDecorator implements BitmapParser {
	private BitmapParser bp;
	
	public BitmapParserDecorator(BitmapParser bp){
		this.bp = bp;
	}
	
	@Override
	public void parse(BufferedImage image){
		bp.parse(image);
	}

	@Override
	public List<ContrastMatrix> getMatrices(){
		return bp.getMatrices();
	}

	@Override
	public ContrastMatrix getMatrix(int i){
		return bp.getMatrix(i);
	}

	@Override
	public void setMatrices(List<ContrastMatrix> matrices) {
		bp.setMatrices(matrices);
	}

}
