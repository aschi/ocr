package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class CharacterParser extends BitmapParserDecorator {

	public CharacterParser(BitmapParser bp) {
		super(bp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(BufferedImage image) {
		super.parse(image);
		List<ContrastMatrix> rv = new LinkedList<ContrastMatrix>();

		boolean emptyRow = true;

		for (ContrastMatrix m : getMatrices()) {
			if(m.getFunctionalChar() == null){
				m.trim();
	
				boolean emptyCol = true;
				int characterStart = -1;
	
				// extract characters
				for (int x = 0; x < m.getWidth(); x++) {
					emptyCol = true;
	
					for (int y = 0; y < m.getHeight(); y++) {
						if (m.getValue(x, y) != 0) {
							emptyCol = false;
						}
					}
	
					if (emptyCol) {
						if (characterStart != -1) {
							// column after character is empty => start -> x-1 is a
							// character
							rv.add(m.getSubMatrix(characterStart, 0, x
									- characterStart, m.getHeight()));
							characterStart = -1;
						}
					} else {
						if (characterStart == -1) {
							characterStart = x;
						}
					}
				}
	
				// last charakter
				if (characterStart != -1) {
					rv.add(m.getSubMatrix(characterStart, 0, m.getWidth()
							- characterStart, m.getHeight()));
				}
			}else{
				//functional character => keep it
				rv.add(m);
			}
			//ovverride matrix list
			setMatrices(rv);
		}
	}

}
