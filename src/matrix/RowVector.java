package matrix;


/**
 * Created by Bacho on 3/4/15.
 */
public class RowVector extends Matrix {
	RowVector(int[] vector) {
		super(new int[][]{vector});
	}

	RowVector(int size) {
		super(1, size);
	}

	public int getAt(int index) {
		return super.getAt(0, index);
	}

	public RowVector multipleToMatrix(Matrix matrix) throws Exception {
		return new RowVector(super.multipleToMatrix(matrix).getRow(0));
	}

	public RowVector xor(Matrix matrix) throws Exception {
		int size = getSize();
		if (size != matrix.getSize()) {
			throw new Exception("Matrix's aren't compatible!");
		}
		int[] res = new int[size];
		for (int i = 0; i < size; i++) {
			int v = getAt(0, i);
			if (v == 1) {
				for (int j = 0; j < size; j++) {
					res[j] ^= matrix.getAt(i, j);
				}
			}
		}
		return new RowVector(res);
	}
}
