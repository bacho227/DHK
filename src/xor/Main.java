package xor;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by Bacho on 3/17/15.
 */
public class Main {
	public static void main(String args[]) throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(2);
		binaryMatrix.generateRandom();
		BinaryVector binaryVector = new BinaryVector(2);
		BigInteger base = new BigInteger("00000000000000000000000");
		long start = System.nanoTime();
		for (int i = 0; i < binaryVector.getSize(); i++) {
			if (binaryVector.testAt(i)) {
//				System.out.println(binaryMatrix.getRow(i).toString(2));
				base = base.xor(binaryMatrix.getRow(i));
			}
		}
		DecimalFormat df = new DecimalFormat("0.000000");
		double diff = (double) (System.nanoTime() - start) / 1000000;
		System.out.println(df.format(diff));
		System.out.println(base.toString(2));
	}
}
