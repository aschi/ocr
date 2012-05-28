package ch.zhaw.ocr.knn.helper;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;
import de.jungblut.math.DoubleVector;
import de.jungblut.math.dense.DenseDoubleVector;

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
	
	/**
	 * Merge thetas into one vector. Equivalent to [theta1(:);theta2(:)]
	 * @param theta1
	 * @param theta2
	 * @return
	 */
	public static Matrix mergeThetas(Matrix theta1, Matrix theta2){
		int len = (theta1.getColumnCount()*theta1.getRowCount()) + (theta2.getColumnCount()*theta2.getRowCount());
		Matrix rv = MatrixFactory.createMatrix(len, 1);
		
		int n = 0;
		
		//add theta 1
		for(int col = 0; col < theta1.getColumnCount();col++){
			for(int row = 0; row < theta1.getRowCount();row++){
				rv.set(n, 0, theta1.get(row, col));
				n++;
			}
		}
		
		//add theta 2
		for(int col = 0; col < theta2.getColumnCount();col++){
			for(int row = 0; row < theta2.getRowCount();row++){
				rv.set(n, 0, theta2.get(row, col));
				n++;
			}
		}
		
		return rv;
	}
	
	/**
	 * Generate theta1 + theta2 from a given merged vector
	 * @param mergedThetas
	 * @param inputLayerSize
	 * @param hiddenLayerSize
	 * @param outputLayerSize
	 * @return an array containing theta1 (unmergeThetas[0]) and theta2 (unmergeThetas[1])
	 */
	public static Matrix[] unmergeThetas(Matrix mergedThetas, int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		int[] thetaLen = new int[2];
		thetaLen[0] = hiddenLayerSize * (inputLayerSize+1);
		thetaLen[1] = outputLayerSize * (hiddenLayerSize+1);
		
		if(thetaLen[0] + thetaLen[1] > mergedThetas.getRowCount()){
			throw new IllegalArgumentException("thetaLen[0] + thetaLen[1] > mergedThetas.getRowCount()");
		}
		
		Matrix[] rv = new Matrix[2];
		rv[0] = MatrixFactory.createMatrix(hiddenLayerSize, inputLayerSize+1);
		rv[1] = MatrixFactory.createMatrix(outputLayerSize, hiddenLayerSize+1);
		
		int n = 0;
		
		for(int i = 0; i < rv.length; i ++){
			for(int col = 0; col < rv[i].getColumnCount();col++){
				for(int row = 0; row < rv[i].getRowCount();row++){
					rv[i].set(row, col, mergedThetas.get(n, 0));
					n++;
				}
			}
		}
		
		return rv;
	}
	
	/**
	 * Create a double vector from a 1 column matrix. Used for fmincg function
	 * @param m input matrix
	 * @return output DoubleVector
	 */
	public static DoubleVector convertToDoubleVector(Matrix m){
		if(m.getColumnCount() > 1){
			throw new IllegalArgumentException("Only vectors (matrix with one column) may be converted");
		}
		
		double[] arr = new double[m.getRowCount()];
		
		for(int row = 0; row < m.getRowCount();row++){
			arr[row] = m.get(row, 0);
		}
		
		return new DenseDoubleVector(arr);
	}
	
	/**
	 * Convert a DoubleVector into a matrix. Used to process output of fmincg function
	 * @param dv input DoubleVector
	 * @return generated matrix
	 */
	public static Matrix convertToMatrix(DoubleVector dv){
		Matrix m = MatrixFactory.createMatrix(dv.getLength(), 1);
		
		for(int row = 0; row < dv.getLength(); row ++){
			m.set(row, 0, dv.get(row));
		}
		
		return m;
	}
}
