package ch.zhaw.ocr.BitmapParser;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class WordParser extends BitmapParserDecorator {

	public WordParser(BitmapParser bp) {
		super(bp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(BufferedImage image) {
		super.parse(image);

		List<ContrastMatrix> rv = new LinkedList<ContrastMatrix>();

		boolean isEmpty;

		int minSpaceSize = 0;
		int maxSpaceSize = 0;

		int spaceCounter = 0;

		int wordStart = -1;
		int wordEndTmp = -1;

		int x = 0;
		int y = 0;

		for (ContrastMatrix m : getMatrices()) {
			if(m.getFunctionalChar() == null){
				/**
				 * Step 1: find min / max space sizes
				 */
				for (x = 0; x < m.getWidth(); x++) {
					isEmpty = true;
					for (y = 0; y < m.getHeight(); y++) {
						if (m.getValue(x, y) == 1) {
							isEmpty = false;
						}
					}
	
					if (isEmpty) {
						spaceCounter++;
					} else {
						// min / max space detection
						if (spaceCounter > 0) {
							if (minSpaceSize == 0 || minSpaceSize > spaceCounter) {
								minSpaceSize = spaceCounter;
							}
	
							if (maxSpaceSize < spaceCounter) {
								maxSpaceSize = spaceCounter;
							}
						}
						spaceCounter = 0;
					}
				}
	
				//System.out.println("min space size: " + minSpaceSize);
				//System.out.println("max space size: " + maxSpaceSize);
	
				//System.out.println(m);
	
				spaceCounter = 0;
				wordEndTmp = -1;
				wordStart = -1;
	
				for (x = 0; x < m.getWidth(); x++) {
					isEmpty = true;
					for (y = 0; y < m.getHeight(); y++) {
						if (m.getValue(x, y) == 1) {
							isEmpty = false;
						}
					}
	
					if (isEmpty) {
						spaceCounter++;
	
						if (wordStart != -1) {
							if (wordEndTmp == -1) {
								wordEndTmp = x;
							}
						}
					} else {
						// word start
						if (wordStart == -1) {
							wordStart = x;
						}
	
						if (spaceCounter != 0) {
							// last space was a "word space" (space size closer to
							// max then to min)
							if ((maxSpaceSize - spaceCounter) < (spaceCounter - minSpaceSize)) {
								rv.add(m.getSubMatrix(wordStart, 0,
										wordEndTmp - wordStart, m.getHeight()));
								rv.add(new ContrastMatrix(FunctionalCharacter.space));
								//System.out.println((maxSpaceSize - spaceCounter)
								//		+ " - " + (spaceCounter - minSpaceSize));
								wordStart = x;
							} else {
								//System.out.println((maxSpaceSize - spaceCounter)
								//		+ " - " + (spaceCounter - minSpaceSize));
							}
						}
	
						// reset counters
						wordEndTmp = -1;
						spaceCounter = 0;
					}
	
				}
	
				// get last word
				if (wordStart != -1) {
					rv.add(m.getSubMatrix(wordStart, 0, m.getWidth()
							- wordStart, m.getHeight()));
				}
			}else{
				//functional character => keep it
				rv.add(m);
			}
		}
		setMatrices(rv);
	}
}
