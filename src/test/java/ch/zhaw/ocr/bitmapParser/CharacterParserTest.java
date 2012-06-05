package ch.zhaw.ocr.bitmapParser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.bitmapParser.BitmapParser;
import ch.zhaw.ocr.bitmapParser.CharacterParser;
import ch.zhaw.ocr.bitmapParser.ContrastMatrix;

public class CharacterParserTest {
	private ContrastMatrix inputMatrix;
	private List<ContrastMatrix> expectedResultList;
	
	private Mockery context;
	
	
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
				
		inputMatrix = new ContrastMatrix(20, 5);
		
		fillCol(inputMatrix, 0);
		fillCol(inputMatrix, 1);
		fillCol(inputMatrix, 2);
		fillCol(inputMatrix, 3);
		
		fillCol(inputMatrix, 6);
		fillCol(inputMatrix, 7);
		fillCol(inputMatrix, 8);
		fillCol(inputMatrix, 9);
		fillCol(inputMatrix, 10);
	
		fillCol(inputMatrix, 17);
		fillCol(inputMatrix, 18);
		fillCol(inputMatrix, 19);
		
		//set up desired result		
		expectedResultList= new LinkedList<ContrastMatrix>();
		ContrastMatrix cm = new ContrastMatrix(4, 5);
		cm.invertMatrix();
		expectedResultList.add(cm);
		
		cm = new ContrastMatrix(5, 5);
		cm.invertMatrix();
		expectedResultList.add(cm);
		
		cm = new ContrastMatrix(3, 5);
		cm.invertMatrix();
		expectedResultList.add(cm);
	}

	private void fillCol(ContrastMatrix cm, int colNo){
		for(int y = 0;y < cm.getContrastMatrix()[0].length;y++){
				cm.setValue(colNo, y, 1);
		}
	}

	@Test
	public void testParse() {
		//input matrices
		final List<ContrastMatrix> inputList = new LinkedList<ContrastMatrix>();
		inputList.add(inputMatrix);
		
		
		final BitmapParser bp = context.mock(BitmapParser.class);
		
		context.checking(new Expectations() {{
			oneOf (bp).parse(null); will(returnValue(inputList));
	    }});

		
		BitmapParser instance = new CharacterParser(bp);
		List<ContrastMatrix> parsedList = instance.parse(null);
		
		assertTrue(parsedList.equals(expectedResultList));
	}

}
