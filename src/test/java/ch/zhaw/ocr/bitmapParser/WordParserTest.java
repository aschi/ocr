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
import ch.zhaw.ocr.bitmapParser.ContrastMatrix;
import ch.zhaw.ocr.bitmapParser.FunctionalCharacter;
import ch.zhaw.ocr.bitmapParser.WordParser;

public class WordParserTest {
	private ContrastMatrix inputMatrix;
	private List<ContrastMatrix> expectedResultList;
	
	private Mockery context;
	
	
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
				
		inputMatrix = new ContrastMatrix(40, 5);
		
		//word 1
		fillCol(inputMatrix, 0);
		fillCol(inputMatrix, 1);
		fillCol(inputMatrix, 2);
		fillCol(inputMatrix, 3);
		
		fillCol(inputMatrix, 6);
		fillCol(inputMatrix, 7);
		fillCol(inputMatrix, 8);
		fillCol(inputMatrix, 9);
		fillCol(inputMatrix, 10);
	
		fillCol(inputMatrix, 13);
		fillCol(inputMatrix, 14);
		fillCol(inputMatrix, 15);
		fillCol(inputMatrix, 16);
		fillCol(inputMatrix, 17);
		
		//word 2
		fillCol(inputMatrix, 27);
		fillCol(inputMatrix, 28);
		fillCol(inputMatrix, 29);
		fillCol(inputMatrix, 30);
		
		fillCol(inputMatrix, 33);
		fillCol(inputMatrix, 34);
		
		fillCol(inputMatrix, 37);
		fillCol(inputMatrix, 38);
		fillCol(inputMatrix, 39);
		
		
		//set up desired result		
		expectedResultList= new LinkedList<ContrastMatrix>();
		ContrastMatrix cm = new ContrastMatrix(18, 5);
		fillCol(cm, 0);
		fillCol(cm, 1);
		fillCol(cm, 2);
		fillCol(cm, 3);
		
		fillCol(cm, 6);
		fillCol(cm, 7);
		fillCol(cm, 8);
		fillCol(cm, 9);
		fillCol(cm, 10);
	
		fillCol(cm, 13);
		fillCol(cm, 14);
		fillCol(cm, 15);
		fillCol(cm, 16);
		fillCol(cm, 17);
		expectedResultList.add(cm);
		
		expectedResultList.add(new ContrastMatrix(FunctionalCharacter.space));
		
		cm = new ContrastMatrix(13, 5);
		fillCol(cm, 0);
		fillCol(cm, 1);
		fillCol(cm, 2);
		fillCol(cm, 3);
		
		fillCol(cm, 6);
		fillCol(cm, 7);
		
		fillCol(cm, 10);
		fillCol(cm, 11);
		fillCol(cm, 12);
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

		
		BitmapParser instance = new WordParser(bp);
		List<ContrastMatrix> parsedList = instance.parse(null);
		
		assertTrue(parsedList.equals(expectedResultList));
	}
}
