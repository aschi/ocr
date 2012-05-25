package ch.zhaw.ocr.knn.helper;

import hu.kazocsaba.math.matrix.Matrix;
import hu.kazocsaba.math.matrix.MatrixFactory;

import java.util.List;

public class TrainingNN {

	private Matrix nnCostFunction(Matrix theta1, Matrix theta2,
			int inputLayerSize, int hiddenLayerSize, int outputLayerSize,
			List<Matrix> trainingVectors, List<Integer> expectedResults,
			double lambda) {
		Matrix theta1Grad = MatrixFactory.createLike(theta1);
		Matrix theta2Grad = MatrixFactory.createLike(theta2);

		Matrix idMat = MatrixFactory.identity(outputLayerSize);

		Matrix s = null;

		for (int i = 0; i < trainingVectors.size(); i++) {

			Matrix a1 = trainingVectors.get(i);
			a1 = MatrixHelper.add1ToVector(a1, "horizontal");

			Matrix z2 = theta1.mul(a1.transpose());
			Matrix a2 = MatrixHelper.sigmoid(z2);
			a2 = MatrixHelper.add1ToVector(a2, "vertical");

			Matrix z3 = theta2.mul(a2);
			Matrix a3 = MatrixHelper.sigmoid(z3);

			Matrix h = MatrixFactory.copy(a3);

			Matrix yk = idMat.getSubmatrix(expectedResults.get(i),
					expectedResults.get(i), 0, idMat.getColumnCount() - 1);

			Matrix ykOnes = MatrixFactory.ones(yk.getRowCount(),
					yk.getColumnCount());
			Matrix hOnes = MatrixFactory.ones(h.getRowCount(),
					h.getColumnCount());

			// (-yk * log(h)) - ((1 - yk) * log(1-h) )
			Matrix costTerm = yk.times(-1).mul(MatrixHelper.log(h)).minus(
					ykOnes.minus(yk).mul(MatrixHelper.log(hOnes.minus(h))));

			if (s == null) {
				s = MatrixFactory.createLike(costTerm);
			}

			s.add(costTerm);

			Matrix delta3 = a3.minus(yk.transpose());

			Matrix a2Ones = MatrixFactory.ones(a2.getRowCount(),
					a2.getColumnCount());

			// delta_2 = ((Theta2)' * delta_3) .* (a2 .* (ones(size(a2))-a2));
			Matrix delta2 = MatrixHelper.elementMultiplication(
					theta2.transpose().mul(delta3),
					(MatrixHelper.elementMultiplication(a2, a2Ones.minus(a2))));

			//to be tested
			delta2 = delta2.getSubmatrix(1, delta2.getRowCount(), 0, delta2.getColumnCount());
			
			theta1Grad.add(delta2.mul(a1));
			theta2Grad.add(delta3.mul(a2.transpose())); 
			
		}

		Matrix J = s.times(1/trainingVectors.size());
		
		
		//Theta1_grad = Theta1_grad ./ m;
		//Theta2_grad = Theta2_grad ./ m;
		theta1Grad = theta1Grad.times(1/trainingVectors.size());
		theta2Grad = theta2Grad.times(1/trainingVectors.size());

		Matrix tmp1;
		Matrix tmp2;
		
		
		//Theta1_grad(:,2:end) = Theta1_grad(:,2:end) + ((lambda/m) * Theta1(:,2:end) );		
		tmp1 = theta1Grad.getSubmatrix(1, theta1Grad.getRowCount(), 0, theta1Grad.getColumnCount());
		tmp2 = theta1.getSubmatrix(1, theta1.getRowCount(), 0, theta1.getColumnCount());		
		tmp1.add(tmp2.times(lambda/trainingVectors.size()));
		
		theta1Grad.setSubmatrix(tmp1, 1, theta1Grad.getRowCount());
		

		//Theta2_grad(:,2:end) = Theta2_grad(:,2:end) + ((lambda/m) * Theta2(:,2:end) );
		tmp1 = theta2Grad.getSubmatrix(1, theta2Grad.getRowCount(), 0, theta2Grad.getColumnCount());
		tmp2 = theta2.getSubmatrix(1, theta2.getRowCount(), 0, theta2.getColumnCount());		
		tmp1.add(tmp2.times(lambda/trainingVectors.size()));
		
		theta2Grad.setSubmatrix(tmp1, 1, theta2Grad.getRowCount());
		
		
		//drop bias terms
		tmp1 = theta1.getSubmatrix(1, theta1.getRowCount(), 0, theta1.getColumnCount());	
		tmp2 = theta2.getSubmatrix(1, theta2.getRowCount(), 0, theta2.getColumnCount());		
		return null;
	}

}
