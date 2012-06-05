package ch.zhaw.ocr.bitmapParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.bitmapParser.ContrastMatrix;
import ch.zhaw.ocr.bitmapParser.FunctionalCharacter;

public class ContrastMatrixTest {
	ContrastMatrix exampleMatrix;
	
	
	@Before
	public void setUp() throws Exception {
		exampleMatrix = new ContrastMatrix(4, 4);
		exampleMatrix.setValue(0, 0, 1);
		exampleMatrix.setValue(1, 1, 1);
		exampleMatrix.setValue(2, 2, 1);
		exampleMatrix.setValue(3, 3, 1);
	}

	@Test
	public void testContrastMatrixIntInt() {
		int[][] rev = new int[5][5];
		ContrastMatrix cm = new ContrastMatrix(5, 5);

		boolean eq = true;
		if (rev.length == cm.getContrastMatrix().length
				&& rev[0].length == cm.getContrastMatrix()[0].length) {
			for (int x = 0; x < rev.length; x++) {
				for (int y = 0; y < rev[0].length; y++) {
					if (rev[x][y] != cm.getContrastMatrix()[x][y]) {
						eq = false;
					}
				}
			}
		} else {
			eq = false;
		}

		assertTrue(eq);
	}

	@Test
	public void testContrastMatrixFunctionalCharacter() {
		FunctionalCharacter fc = FunctionalCharacter.carriageReturn;
		ContrastMatrix cm = new ContrastMatrix(fc);

		assertTrue(cm.getFunctionalChar().equals(fc));
	}

	@Test
	public void testToString() {
		String refValue = "1 0 0 0" + System.getProperty("line.separator")
				+ "0 1 0 0" + System.getProperty("line.separator")
				+ "0 0 1 0" + System.getProperty("line.separator")
				+ "0 0 0 1" + System.getProperty("line.separator");

		assertTrue(exampleMatrix.toString().equals(refValue));
	}

	@Test
	public void testGetValue() {
		assertTrue(exampleMatrix.getValue(1, 1) == 1);
	}

	@Test
	public void testGetWidth() {
		assertTrue(exampleMatrix.getWidth() == 4);
	}

	@Test
	public void testGetHeight() {
		assertTrue(exampleMatrix.getHeight() == 4);
	}

	@Test
	public void testGetFunctionalChar() {
		FunctionalCharacter fc = FunctionalCharacter.carriageReturn;
		ContrastMatrix cm = new ContrastMatrix(fc);

		assertTrue(cm.getFunctionalChar().equals(fc));
	}

	@Test
	public void testSetValue() {
		exampleMatrix.setValue(1, 2, 1);
		assertTrue(exampleMatrix.getValue(1, 2) == 1);
		exampleMatrix.setValue(1, 2, 0);
	}

	@Test
	public void testRemoveCol() {
		ContrastMatrix cm = new ContrastMatrix(4, 4);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 1, 1);
		cm.setValue(2, 2, 1);
		cm.setValue(3, 3, 1);
		
		ContrastMatrix cm2 = new ContrastMatrix(3, 4);
		cm2.setValue(0, 0, 1);
		cm2.setValue(1, 1, 1);
		cm2.setValue(2, 2, 1);
		
		cm.removeCol(3);
		
		assertTrue(cm.equals(cm2));
	}

	@Test
	public void testRemoveRow() {
		ContrastMatrix cm = new ContrastMatrix(4, 4);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 1, 1);
		cm.setValue(2, 2, 1);
		cm.setValue(3, 3, 1);
		
		ContrastMatrix cm2 = new ContrastMatrix(4, 3);
		cm2.setValue(0, 0, 1);
		cm2.setValue(1, 1, 1);
		cm2.setValue(3, 2, 1);
		
		cm.removeRow(2);
		
		assertTrue(cm.equals(cm2));
	}

	@Test
	public void testInvertMatrix() {
		ContrastMatrix cm = new ContrastMatrix(4, 4);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 1, 1);
		cm.setValue(2, 2, 1);
		cm.setValue(3, 3, 1);
		
		ContrastMatrix cm2 = new ContrastMatrix(4, 4);
		cm2.setValue(1, 0, 1);
		cm2.setValue(2, 0, 1);
		cm2.setValue(3, 0, 1);
		
		cm2.setValue(0, 1, 1);
		cm2.setValue(2, 1, 1);
		cm2.setValue(3, 1, 1);
		
		cm2.setValue(0, 2, 1);
		cm2.setValue(1, 2, 1);
		cm2.setValue(3, 2, 1);
		
		cm2.setValue(0, 3, 1);
		cm2.setValue(1, 3, 1);
		cm2.setValue(2, 3, 1);
		
		cm.invertMatrix();
		
		assertTrue(cm.equals(cm2));
	}

	@Test
	public void testGetSubMatrix() {
		ContrastMatrix cm = new ContrastMatrix(2, 2);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 1, 1);
		
		ContrastMatrix cm2 = exampleMatrix.getSubMatrix(1, 1, 2, 2);
		
		assertTrue(cm2.equals(cm));
	}

	@Test
	public void testIsFullRow(){
		ContrastMatrix cm = new ContrastMatrix(2, 2);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 0, 1);
		
		assertTrue(cm.isFullRow(0));
	}
	
	@Test
	public void testIsEmptyRow(){
		ContrastMatrix cm = new ContrastMatrix(2, 2);
		cm.setValue(0, 0, 1);
		cm.setValue(1, 0, 1);
		
		assertTrue(cm.isEmptyRow(1));
	}
	
	@Test
	public void testIsFullCol(){
		ContrastMatrix cm = new ContrastMatrix(2, 2);
		cm.setValue(0, 0, 1);
		cm.setValue(0, 1, 1);
		
		assertTrue(cm.isFullCol(0));
	}
	
	@Test
	public void testIsEmptyCol(){
		ContrastMatrix cm = new ContrastMatrix(2, 2);
		cm.setValue(0, 0, 1);
		cm.setValue(0, 1, 1);
		
		assertTrue(cm.isEmptyCol(1));
	}
	
	@Test
	public void testIsFull(){
		ContrastMatrix cm = new ContrastMatrix(4, 4);
		cm.setValue(1, 1, 1);
		cm.setValue(1, 2, 1);
		cm.setValue(2, 1, 1);
		cm.setValue(2, 2, 1);
		
		
		assertTrue(cm.isFull(1,2,1,2));
	}
	
	@Test
	public void testTrim() {
		ContrastMatrix cm2 = new ContrastMatrix(2, 2);
		cm2.setValue(0, 0, 1);
		cm2.setValue(1, 1, 1);
		
		ContrastMatrix cm = new ContrastMatrix(4, 4);
		cm.setValue(1, 1, 1);
		cm.setValue(2, 2, 1);
		
		cm.trim();
		
		assertTrue(cm.equals(cm2));
	}

	
	
}
