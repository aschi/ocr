package ch.zhaw.ocr.nn.helper;

import hu.kazocsaba.math.matrix.Matrix;

public class CostFunctionResult {
	private double J;
	private Matrix thetaGrad;
	public double getJ() {
		return J;
	}
	public void setJ(double j) {
		J = j;
	}
	public Matrix getTheta1Grad() {
		return thetaGrad;
	}
	public void setTheta1Grad(Matrix theta1Grad) {
		this.thetaGrad = theta1Grad;
	}
	
	public CostFunctionResult(double j, Matrix thetaGrad) {
		super();
		J = j;
		this.thetaGrad = thetaGrad;
	}
	
	@Override
	public String toString() {
		return "CostFunctionResult [J=" + J + ", thetaGrad=" + thetaGrad +"]";
	}
	
	
}
