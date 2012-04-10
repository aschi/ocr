package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


/**
 * Separates text-rows
 * 1. Search for empty pixel-rows
 * 2. Group "not" empty pixel-rows
 * @author adrian
 *
 */
public class RowParser extends BitmapParserDecorator {

	public RowParser(BitmapParser bp) {
		super(bp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(BufferedImage image){
		super.parse(image);
		
		List<ContrastMatrix> rv = new LinkedList<ContrastMatrix>();
		
		boolean isEmpty = true;
		int rowStart = -1;
		
		for(ContrastMatrix m : getMatrices()){
			if(m.getFunctionalChar() == null){
				//trim
				m.trim();
				
				for(int y = 0;y < m.getHeight();y++){
					isEmpty = true;
					
					for(int x = 0;x < m.getHeight();x++){
						if(m.getValue(x, y) == 1){
							isEmpty = false;
						}
					}
					
					if(isEmpty){
						if(rowStart != -1){
							rv.add(m.getSubMatrix(0, rowStart, m.getWidth()-1, y-rowStart));
							rowStart = -1;
						}
					}else{
						if(rowStart == -1){
							rowStart = y;
						}
					}
				}
			}else{
				//functional character => keep it
				rv.add(m);
			}
		}
		//ovverride matrix list
		setMatrices(rv);
	}

	
}
