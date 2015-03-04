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

	public RowVector multipleToMatrix1(Matrix matrix) throws Exception {
		return new RowVector(super.multipleToMatrix(matrix).getRow(0));
	}
}
