package ch.zhaw.ocr.nn.helper;

import static org.junit.Assert.assertTrue;
import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import org.junit.Test;

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
		Matrix m = MatrixFactory.createMatrix(new double[][]{{1},{4},{2},{5},{3},{6},{4},{1},{5},{2},{6},{3}});
		
		Matrix expected1 = MatrixFactory.createMatrix(new double[][]{{1,2,3},{4,5,6}});
		Matrix expected2 = MatrixFactory.createMatrix(new double[][]{{4,5,6},{1,2,3}});
		
		Matrix[] result = MatrixHelper.unmergeThetas(m, 2, 2, 2);

		boolean equals = true;
		if(!MatrixHelper.matrixEquals(result[0], expected1)){
			equals = false;
		}
		if(!MatrixHelper.matrixEquals(result[1], expected2)){
			equals = false;
		}
		assertTrue(equals);
	}
	
	@Test
	public void mergeThetasTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3},{4,5,6}});
		Matrix m2 = MatrixFactory.createMatrix(new double[][]{{4,5,6},{1,2,3}});
		
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{1},{4},{2},{5},{3},{6},{4},{1},{5},{2},{6},{3}});
		
		assertTrue(MatrixHelper.matrixEquals(MatrixHelper.mergeThetas(m1, m2), expected));
	}
	
	@Test
	public void maxTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3}});
		assertTrue(MatrixHelper.max(m1) == 3);
	}
	
	@Test
	public void sumTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3}});
		assertTrue(MatrixHelper.sum(m1) == 6);
	}
	
	@Test
	public void addScalarTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3}});
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{2,3,4}});
		assertTrue(MatrixHelper.matrixEquals(MatrixHelper.addScalar(m1, 1), expected));
	}
	
	@Test
	public void logTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3}});
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{Math.log(1),Math.log(2),Math.log(3)}});
		assertTrue(MatrixHelper.matrixEquals(MatrixHelper.log(m1), expected));
	}
	
	@Test
	public void add1ToVectorVerticalTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1},{2},{3}});
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{1},{1},{2},{3}});
		
		assertTrue(MatrixHelper.matrixEquals(expected, MatrixHelper.add1ToVector(m1, "vertical")));
	}
	
	@Test
	public void add1ToVectorHorizontalTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3}});
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{1,1,2,3}});
		
		assertTrue(MatrixHelper.matrixEquals(expected, MatrixHelper.add1ToVector(m1, "horizontal")));
	}
	
	@Test
	public void elementMultiplicationTest(){
		Matrix m1 = MatrixFactory.createMatrix(new double[][]{{1,2,3},{3,4,5}});
		Matrix m2 = MatrixFactory.createMatrix(new double[][]{{1,2,3},{3,4,5}});
			
		Matrix expected = MatrixFactory.createMatrix(new double[][]{{1,4,9},{9,16,25}});
		
		assertTrue(MatrixHelper.matrixEquals(MatrixHelper.elementMultiplication(m1, m2), expected));
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
