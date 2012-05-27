package ch.zhaw.ocr.knn.helper;

import hu.kazocsaba.math.matrix.Matrix;

public class CostFunctionResult {
	private double J;
	private Matrix theta1Grad;
	private Matrix theta2Grad;
	public double getJ() {
		return J;
	}
	public void setJ(double j) {
		J = j;
	}
	public Matrix getTheta1Grad() {
		return theta1Grad;
	}
	public void setTheta1Grad(Matrix theta1Grad) {
		this.theta1Grad = theta1Grad;
	}
	public Matrix getTheta2Grad() {
		return theta2Grad;
	}
	public void setTheta2Grad(Matrix theta2Grad) {
		this.theta2Grad = theta2Grad;
	}
	public CostFunctionResult(double j, Matrix theta1Grad, Matrix theta2Grad) {
		super();
		J = j;
		this.theta1Grad = theta1Grad;
		this.theta2Grad = theta2Grad;
	}
	
	@Override
	public String toString() {
		return "CostFunctionResult [J=" + J + ", theta1Grad=" + theta1Grad
				+ ", theta2Grad=" + theta2Grad + "]";
	}
	
	
}
