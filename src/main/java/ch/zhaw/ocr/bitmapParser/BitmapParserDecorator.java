package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.List;


public abstract class BitmapParserDecorator implements BitmapParser {
	private BitmapParser bp;
	
	public BitmapParserDecorator(BitmapParser bp){
		this.bp = bp;
	}
	
	@Override
	public List<ContrastMatrix> parse(BufferedImage image){
		return bp.parse(image);
	}
}
