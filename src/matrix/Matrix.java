package matrix;

import java.util.Random;

/**
 * Created by Bacho on 3/4/15.
 */
public class Matrix {
	private final int column;
	private final int row;
	private final int[][] value;

	Matrix(int[][] matrix) {
		this.row = matrix.length;
		this.column = matrix[0].length;
		value = matrix;
	}

	Matrix(int row, int column) {
		this.row = row;
		this.column = column;
		value = new int[row][column];
	}

	Matrix(int size) {
		this(size, size);
	}

	public void generateRandom() {
		Random randomGenerator = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				value[i][j] = (int) randomGenerator.nextInt(2);
			}
		}
	}

	public int[] getRow(int row) {
		return value[row];
	}

	public int getAt(int row, int column) {
		return value[row][column];
	}


	public Matrix multipleToMatrix(Matrix matrix) throws Exception {
		if (this.column != matrix.row) {
			throw new Exception("Matrix's aren't compatible!");
		}
		int[][] res = new int[this.row][this.column];
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < matrix.column; j++) {
				for (int z = 0; z < matrix.row; z++) {
					res[i][j] += (getAt(i, z) * matrix.getAt(z, j));
				}
			}
		}
		return new Matrix(res);
	}

	public void print() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(getAt(i, j) + " ");
			}
			System.out.print("\n");
		}
	}
//	public int[] multipleToMatrix(Matrix matrix) {
//		int[][] res = multipleToMatrix(matrix);
//	}

}
