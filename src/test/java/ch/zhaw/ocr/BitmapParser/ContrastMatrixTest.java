package ch.zhaw.ocr.BitmapParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testInvertMatrix() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubMatrix() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrim() {
		fail("Not yet implemented");
	}

}
