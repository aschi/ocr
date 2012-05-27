package ch.zhaw.ocr.knn.helper;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

public class MatrixHelper {
	
	/**
	 * Element-by-element multiplication of two matrices
	 * @param m1 input matrix one
	 * @param m2 input matrix two
	 * @return result of element-by-element multiplication
	 * @throws IllegalArgumentException
	 */
	public static Matrix elementMultiplication(Matrix m1, Matrix m2) throws IllegalArgumentException{
		if(m1.getColumnCount() != m2.getColumnCount() || m1.getRowCount() != m2.getRowCount()){
			throw new IllegalArgumentException("Matrix dimensions must agree!");
		}
		
		Matrix result = MatrixFactory.createLike(m1);
		
		for(int col = 0;col < m1.getColumnCount();col++){
			for(int row = 0;row < m1.getRowCount();row ++){
				result.set(row, col, m1.get(row, col) * m2.get(row, col));
			}
		}
		
		return result;		
	}
	
	/**
	 * Use sigmoid function on each element of a given matrix
	 * @param m input matrix
	 * @return transformed matrix
	 */
	public static Matrix sigmoid(Matrix m){
		m = MatrixFactory.copy(m);
		for(int col = 0; col < m.getColumnCount();col++){
			for(int row = 0;row < m.getRowCount();row++){
				m.set(row, col, 1/(1+Math.exp(-1 * m.get(row, col))));
			}
		}
		return m;
	}
	
	/**
	 * Calculates the sigmoid gradient of a matrix (sigmoid(m) .* (ones-sigmoind(m))
	 * @param m input matrix
	 * @return sigmoid gradient of matrix m
	 */
	public static Matrix sigmoidGradient(Matrix m){
		Matrix ones = MatrixFactory.ones(m.getRowCount(), m.getColumnCount());
		Matrix sig = sigmoid(m);
		return MatrixHelper.elementMultiplication(sig, ones.minus(sig));
	}
	
	
	public static Matrix add1ToVector(Matrix vector, String horizontalVertical){
		vector = MatrixFactory.copy(vector);
		if(horizontalVertical.equals("horizontal")){
			Matrix rv = MatrixFactory.createMatrix(1, vector.getColumnCount()+1);
			rv.set(0, 0, 1);
			rv.setSubmatrix(vector, 0, 1);

			return rv;
		}else if(horizontalVertical.equals("vertical")){
			Matrix rv = MatrixFactory.createMatrix( vector.getRowCount()+1,1);
			rv.set(0, 0, 1);
			rv.setSubmatrix(vector, 1, 0);

			return rv;
		}else{
			throw new IllegalArgumentException("use either 'horizontal' or 'vertical' to determine the matrix type");
		}
	}
	
	public static Matrix log(Matrix m){
		m = MatrixFactory.copy(m);
		for(int col = 0; col < m.getColumnCount();col++){
			for(int row = 0;row < m.getRowCount();row++){
				m.set(row, col, Math.log(m.get(row, col)));
			}
		}
		return m;
	}
	
	public static Matrix addScalar(Matrix m, double scalar){
		m = MatrixFactory.copy(m);
		for(int col = 0; col < m.getColumnCount();col++){
			for(int row = 0;row < m.getRowCount();row++){
				m.set(row, col, m.get(row, col)+scalar);
			}
		}
		return m;
	}
 	
	/**
	 * Add up all elements of a given matrix
	 * @param m
	 * @return
	 */
	public static double sum(Matrix m){
		double rv = 0;
		for(int col = 0; col < m.getColumnCount();col++){
			for(int row = 0;row < m.getRowCount();row++){
				rv+=m.get(row, col);
			}
		}
		return rv;
	}
	
	/**
	 * Get the max element of a matrix
	 * @param m
	 * @return
	 */
	public static double max(Matrix m){
		double max = 0;
		for(int col = 0; col < m.getColumnCount();col++){
			for(int row = 0;row < m.getRowCount();row++){
				if(m.get(row, col) > max){
					max = m.get(row, col);
				}
			}
		}
		return max;
	}
}
