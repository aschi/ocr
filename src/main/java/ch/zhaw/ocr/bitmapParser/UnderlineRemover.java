package ch.zhaw.ocr.bitmapParser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class UnderlineRemover extends BitmapParserDecorator {

	public UnderlineRemover(BitmapParser bp) {
		super(bp);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<ContrastMatrix> parse(BufferedImage image){
		List<ContrastMatrix> matrices =  super.parse(image);
		List<ContrastMatrix> returnMatrices = new LinkedList<ContrastMatrix>();
		ContrastMatrix functionalChar = null;
		
		for (ContrastMatrix m : matrices) {
			if (m.getFunctionalChar() == null) {
				m.trim();
				
				
				//If a full Line is an Underline we can remove the whole line
				boolean isFullLine = true;
				for (int yFull = 0; yFull < m.getHeight(); yFull++)  {
					if (!m.isFullRow(yFull)) {
						isFullLine = false;
					}
				}
				
				if (!isFullLine) {
					
					if (functionalChar != null) {
						returnMatrices.add(functionalChar);
						functionalChar = null;
					}

					int start = -1;
					int end = -1;
					for (int y = 0; y < m.getHeight(); y++) {
						if (m.isFullRow(y)) {
							if (start >= 0) {
								end = y;
							} else {
								start = y;
							}
						} 
					}
					
					//In case a underline was found
					if (start > 0 && end > 0) {
						removeUnderline(m, 0, m.getWidth(), start, end);
					}
					
					//Now we have to check if only single Signs are underlined
					//therefore we check the row with the rowhight
					int yStart = -1;
					int yEnd = -1;
					int height = m.getHeight();
					for (int y = 0; y < m.getHeight(); y++) {
						for (int x = 0; x <= m.getWidth() - height; x++) {
							
							int xEnd = x + height;
							
							if (m.isFull(x, xEnd, y, y)) {
								yStart = y;
								yEnd = y;
								//Check how long the underline is
								boolean isStillUnderlined = true;
								for (int xUnderline = xEnd; isStillUnderlined; xUnderline ++) {
									if (m.getValue(xUnderline, y) != 1) {
										isStillUnderlined = false;
									} else {
										xEnd = xUnderline;
									}
								}
								//check how many y's is underline
								boolean isUnderline = true;
								for (int yUnderline = yStart + 1; isUnderline; yUnderline ++) {
									if (yUnderline > m.getHeight()) {
										break;
									}
									isUnderline = m.isFull(x, xEnd, yUnderline, yUnderline);
									if (isUnderline) {
										yEnd = yUnderline;
									}
								}
								
								removeUnderline(m, x, xEnd, yStart, yEnd);
							}
						}
					}
					
					//Check if the row only was an underline that is removed now
					boolean isEmpty = true;
					for (int yEmpty = 0; yEmpty < m.getHeight(); yEmpty++) {
						if (!m.isEmptyRow(yEmpty)) {
							isEmpty = false;
						}
					}
					if (!isEmpty) {
						returnMatrices.add(m);
					}
				} 


			} else {
				functionalChar = m;
				
			}
		}
		
		return returnMatrices;
	}
	
	private void removeUnderline(ContrastMatrix m, int xStart, int xEnd, int yStart, int yEnd) {
		
		for (int x = xStart; x < xEnd; x++) {
			
			//in case the Underline is the last line, we just have to check the line yStart-1
			if (m.getHeight() < (yEnd + 1)) {
				if (m.getValue(x, yStart -1) != 1) {
					for (int y = yStart; y <= yEnd; y++) {
						m.setValue(x, y, 0);
					}
				}
			} else {	
				// Otherwise we also have to check the line yEnd + 1
				if (m.getValue(x, yStart -1) != 1 && m.getValue(xStart, yEnd + 1) != 1) {
					for (int y = yStart; y <= yEnd; y++) {
						m.setValue(x, y, 0);
					}
				}
			}
		}
	}

}
