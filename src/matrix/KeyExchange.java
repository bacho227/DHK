package matrix;

import java.text.DecimalFormat;

/**
 * Created by Bacho on 3/4/15.
 */
public class KeyExchange {

	public static void main(String args[]) throws Exception {
		RowVector v = new RowVector(10);//new RowVector(new byte[]{1, 0, 1});

		v.generateRandom();
		Matrix m = new Matrix(10);//new Matrix(new byte[][]{new byte[]{1, 1, 0}, new byte[]{0, 1, 1}, new byte[]{1, 1, 1}});
		m.generateRandom();
		long t1 = System.nanoTime();
		RowVector res = v.multipleToMatrix1(m);
		long t2 = System.nanoTime();

		DecimalFormat df = new DecimalFormat("0.000000");
		double diff = (double) (t2 - t1) / 1000000;
		System.out.println(df.format(diff));
	}

}
