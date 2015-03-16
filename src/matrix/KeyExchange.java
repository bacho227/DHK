package matrix;

import dhk.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Bacho on 3/4/15.
 */
public class KeyExchange {
	private static final int size = 1000;
	private Value<RowVector> base = new Value<RowVector>("Base:");
	private Value<Matrix> alicePrKey = new Value<Matrix>("Alice's private key:");
	private Value<RowVector> alicePubKey = new Value<RowVector>("Alice's public key:");
	private Value<Matrix> bobPrKey = new Value<Matrix>("Bob's private key:");
	private Value<RowVector> bobPubKey = new Value<RowVector>("Bob's public key:");
	private Value<RowVector> aliceSharedKey = new Value<RowVector>("Alice's shared key:");
	private Value<RowVector> bobSharedKey = new Value<RowVector>("Bob's shared key:");

	public void exportData() throws FileNotFoundException {
		exportData(null);
	}

	public void exportData(File file) throws FileNotFoundException {
		PrintStream printStream;
		if (file != null) {
			printStream = new PrintStream(file);
		} else {
			printStream = System.out;
		}
		printStream.println(base.getExportText());
		printStream.println(alicePrKey.getExportText());
		printStream.println(alicePubKey.getExportText());
		printStream.println(aliceSharedKey.getExportText());
		printStream.println(bobPrKey.getExportText());
		printStream.println(bobPubKey.getExportText());
		printStream.println(bobSharedKey.getExportText());
		if (aliceSharedKey.getValue().equals(bobSharedKey.getValue())) {
			printStream.println("Alice's shared key and Bob's shared key are equal");
		} else {
			printStream.println("Alice's shared key and Bob's shared key aren't equal");
		}
	}

//	public static void main(String args[]) throws Exception {
//		RowVector v = new RowVector(1000);//new RowVector(new byte[]{1, 0, 1});e
//
//		v.generateRandom();
//		Matrix m = new Matrix(1000);//new Matrix(new byte[][]{new byte[]{1, 1, 0}, new byte[]{0, 1, 1}, new byte[]{1, 1, 1}});
//		m.generateRandom();
//		long t1 = System.nanoTime();
//		RowVector res = v.multipleToMatrix(m);
//		long t2 = System.nanoTime();
//
//		DecimalFormat df = new DecimalFormat("0.000000");
//		double diff = (double) (t2 - t1) / 1000000;
//		System.out.println(df.format(diff));
//		res.print();
//	}

	public void run() throws Exception {

		alicePubKey.setStart();
		RowVector u1 = base.getValue().multipleToMatrix(alicePrKey.getValue());
		alicePubKey.setValue(u1);
		System.out.println("Calculated Alice's public key in " + alicePubKey.getMillisecond() + " milliseconds!");

		bobPubKey.setStart();
		RowVector u2 = base.getValue().multipleToMatrix(bobPrKey.getValue());
		bobPubKey.setValue(u2);
		System.out.println("Calculated Bobs's public key in " + bobPubKey.getMillisecond() + " milliseconds!");

		aliceSharedKey.setStart();
		RowVector k1 = alicePubKey.getValue().multipleToMatrix(bobPrKey.getValue());
		aliceSharedKey.setValue(k1);
		System.out.println("Calculated Alice's shared key in " + aliceSharedKey.getMillisecond() + " milliseconds!");

		bobSharedKey.setStart();
		RowVector k2 = bobPubKey.getValue().multipleToMatrix(alicePrKey.getValue());
		bobSharedKey.setValue(k2);
		System.out.println("Calculated Bobs's shared key in " + bobSharedKey.getMillisecond() + " milliseconds!");
	}

	public void generateParams() throws Exception {
		base.setStart();
		RowVector v = new RowVector(size);
		v.generateRandom();
		base.setValue(v);
		alicePrKey.setStart();
		Matrix m1 = new Matrix(size);
		m1.generateRandom();
		alicePrKey.setValue(m1);

		bobPrKey.setStart();
		Matrix m2 = new Matrix(size);
		m2.generateRandom();
		bobPrKey.setValue(m2);
	}

}
