package ch.zhaw.ocr.knn.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import org.junit.Test;

import ch.zhaw.ocr.nn.helper.MatrixHelper;

public class MatrixHelperTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void sigmoidTest(){
		Matrix m = MatrixFactory.createMatrix(1, 1);
		m.set(0, 0, 0);
		
		Matrix expected =  MatrixFactory.createMatrix(1, 1);
		expected.set(0, 0, 0.5);
		
		Matrix result = MatrixHelper.sigmoid(m);
		
		assertTrue(result.get(0, 0) == expected.get(0, 0));
	}
}
