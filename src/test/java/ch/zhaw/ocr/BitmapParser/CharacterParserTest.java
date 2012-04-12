package ch.zhaw.ocr.BitmapParser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

public class CharacterParserTest {
	private ContrastMatrix exampleMatrix;
	private List<ContrastMatrix> resultList;
	
	private Mockery context;
	
	
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
				
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
		
		//set up desired result		
		resultList= new LinkedList<ContrastMatrix>();
		ContrastMatrix cm = new ContrastMatrix(4, 5);
		cm.invertMatrix();
		resultList.add(cm);
		
		cm = new ContrastMatrix(5, 5);
		cm.invertMatrix();
		resultList.add(cm);
		
		cm = new ContrastMatrix(3, 5);
		cm.invertMatrix();
		resultList.add(cm);
	}

	private void fillCol(ContrastMatrix cm, int colNo){
		for(int y = 0;y < cm.getContrastMatrix()[0].length;y++){
				cm.setValue(colNo, y, 1);
		}
	}

	@Test
	public void testParse() {
		//input matrices
		final List<ContrastMatrix> matrices = new LinkedList<ContrastMatrix>();
		matrices.add(exampleMatrix);
		
		
		final BitmapParser bp = context.mock(BitmapParser.class);
		
		context.checking(new Expectations() {{
			oneOf (bp).parse(null); will(returnValue(matrices));
	    }});

		
		BitmapParser instance = new CharacterParser(bp);
		List<ContrastMatrix> parsedList = instance.parse(null);
		
		assertTrue(parsedList.equals(resultList));
	}

}
