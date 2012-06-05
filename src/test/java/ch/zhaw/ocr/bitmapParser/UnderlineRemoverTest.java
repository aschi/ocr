package ch.zhaw.ocr.bitmapParser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

public class UnderlineRemoverTest {
	
	private ContrastMatrix inputMatrix;
	private List<ContrastMatrix> expectedResultList;
	
	private Mockery context;
	
	
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery();
				
		inputMatrix = new ContrastMatrix(30, 10);
		
		fillRow(inputMatrix, 0);
		fillRow(inputMatrix, 1);
		fillRow(inputMatrix, 2);
		fillRow(inputMatrix, 3);
		fillRow(inputMatrix, 4);
		fillRow(inputMatrix, 5);
		
		fillRow(inputMatrix, 7);
		fillRow(inputMatrix, 8);
		
		fillCol(inputMatrix, 5);
		fillCol(inputMatrix, 6);
		fillCol(inputMatrix, 7);
	
		fillCol(inputMatrix, 11);
		fillCol(inputMatrix, 12);
		

		fillCol(inputMatrix, 18);
		fillCol(inputMatrix, 19);
		fillCol(inputMatrix, 20);
		fillCol(inputMatrix, 21);
		
		//set up desired result		
		expectedResultList= new LinkedList<ContrastMatrix>();
		ContrastMatrix cm = new ContrastMatrix(30, 10);
				
		fillRow(cm, 0);
		fillRow(cm, 1);
		fillRow(cm, 2);
		fillRow(cm, 3);
		fillRow(cm, 4);
		fillRow(cm, 5);
	
		fillCol(cm, 5);
		fillCol(cm, 6);
		fillCol(cm, 7);
	
		fillCol(cm, 11);
		fillCol(cm, 12);

		fillCol(cm, 18);
		fillCol(cm, 19);
		fillCol(cm, 20);
		fillCol(cm, 21);
		
		expectedResultList.add(cm);
		
	}
	
	private void fillRow(ContrastMatrix cm, int rowNo){
		for(int x = 0;x < cm.getWidth();x++){
				cm.setValue(x, rowNo, 1);
		}
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

		
		BitmapParser instance = new UnderlineRemover(bp);
		List<ContrastMatrix> parsedList = instance.parse(null);
		for (ContrastMatrix m : expectedResultList) {
			System.out.println(m);
		}
		System.out.println("----------- result --------");
		for (ContrastMatrix m : parsedList) {
			System.out.println(m);
		}
		assertTrue(parsedList.equals(expectedResultList));
	}

}
