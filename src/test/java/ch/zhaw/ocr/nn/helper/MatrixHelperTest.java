package ch.zhaw.ocr.nn.helper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import org.junit.Test;

import ch.zhaw.ocr.nn.helper.MatrixHelper;
import de.jungblut.math.DoubleVector;
import de.jungblut.math.dense.DenseDoubleVector;

public class MatrixHelperTest {
	
	@Test
	public void convertToMatrixTest(){
		Matrix m = MatrixFactory.createMatrix(5, 1);
		m.set(0, 0, 0.5);
		m.set(1, 0, 1);
		m.set(2, 0, 2);
		m.set(3, 0, 3);
		m.set(4, 0, 1.5);
		
		
		DoubleVector dv = new DenseDoubleVector(new double[]{0.5,1,2,3,1.5});
		
		Matrix mResult = MatrixHelper.convertToMatrix(dv);
		
		assertTrue(MatrixHelper.matrixEquals(m, mResult));
	}
	
	@Test
	public void convertToDoubleVectorTest(){
		Matrix m = MatrixFactory.createMatrix(5, 1);
		m.set(0, 0, 0.5);
		m.set(1, 0, 1);
		m.set(2, 0, 2);
		m.set(3, 0, 3);
		m.set(4, 0, 1.5);
		
		
		DoubleVector dv = new DenseDoubleVector(new double[]{0.5,1,2,3,1.5});
		
		DoubleVector dvResult = MatrixHelper.convertToDoubleVector(m);
		
		assertTrue(dv.equals(dvResult));
	}
	
	@Test
	public void unmergeThetasTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void mergeThetasTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void maxTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void sumTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void addScalarTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void logTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void add1ToVectorVerticalTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void add1ToVectorHorizontalTest(){
		fail("Not yet implemented");
	}
	
	@Test
	public void elementMultiplicationTest(){
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
	
	@Test
	public void sigmoidGradientTest(){
		Matrix m = MatrixFactory.createMatrix(1, 1);
		m.set(0, 0, 0);
		
		Matrix expected =  MatrixFactory.createMatrix(1, 1);
		expected.set(0, 0, 0.5);
		
		Matrix result = MatrixHelper.sigmoid(m);
		
		assertTrue(result.get(0, 0) == expected.get(0, 0));
	}
}
