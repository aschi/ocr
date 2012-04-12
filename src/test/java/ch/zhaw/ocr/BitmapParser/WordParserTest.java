package ch.zhaw.ocr.BitmapParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordParserTest {
	private ContrastMatrix exampleMatrix;
	
	@Before
	public void setUp() throws Exception {
		exampleMatrix = new ContrastMatrix(20, 5);
		
		fillCol(exampleMatrix, 0);
		fillCol(exampleMatrix, 1);
		fillCol(exampleMatrix, 2);
		fillCol(exampleMatrix, 3);
		
		fillCol(exampleMatrix, 6);
		fillCol(exampleMatrix, 7);
		fillCol(exampleMatrix, 8);
		fillCol(exampleMatrix, 9);
		fillCol(exampleMatrix, 10);
	
		fillCol(exampleMatrix, 17);
		fillCol(exampleMatrix, 18);
		fillCol(exampleMatrix, 19);
	}

	private void fillCol(ContrastMatrix cm, int colNo){
		for(int y = 0;y < cm.getContrastMatrix()[0].length;y++){
				cm.setValue(colNo, y, 1);
		}
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}

}
