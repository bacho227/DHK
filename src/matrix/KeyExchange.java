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
	private Value<Matrix> alicePubKey = new Value<Matrix>("Alice's public key:");
	private Value<RowVector> alicePrKey = new Value<RowVector>("Alice's private key:");
	private Value<Matrix> bobPubKey = new Value<Matrix>("Bob's public key:");
	private Value<RowVector> bobPrKey = new Value<RowVector>("Bob's private key:");
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
		printStream.println(alicePubKey.getExportText());
		printStream.println(alicePrKey.getExportText());
		printStream.println(aliceSharedKey.getExportText());
		printStream.println(bobPubKey.getExportText());
		printStream.println(bobPrKey.getExportText());
		printStream.println(bobSharedKey.getExportText());
		if (aliceSharedKey.getValue().equals(bobSharedKey.getValue())) {
			printStream.println("Alice's shared key and Bob's shared key are equal");
		} else {
			printStream.println("Alice's shared key and Bob's shared key aren't equal");
		}
	}

	public void run() throws Exception {
		alicePrKey.setStart();
		RowVector u1 = base.getValue().multipleToMatrix(alicePubKey.getValue());
		alicePrKey.setValue(u1);
		System.out.println("Calculated Alice's private key in " + alicePrKey.getMillisecond() + " milliseconds!");

		bobPrKey.setStart();
		RowVector u2 = base.getValue().multipleToMatrix(bobPubKey.getValue());
		bobPrKey.setValue(u2);
		System.out.println("Calculated Bobs's private key in " + bobPrKey.getMillisecond() + " milliseconds!");

		aliceSharedKey.setStart();
		RowVector k1 = alicePrKey.getValue().multipleToMatrix(bobPubKey.getValue());
		aliceSharedKey.setValue(k1);
		System.out.println("Calculated Alice's shared key in " + aliceSharedKey.getMillisecond() + " milliseconds!");

		bobSharedKey.setStart();
		RowVector k2 = bobPrKey.getValue().multipleToMatrix(alicePubKey.getValue());
		bobSharedKey.setValue(k2);
		System.out.println("Calculated Bobs's shared key in " + bobSharedKey.getMillisecond() + " milliseconds!");
	}

	public void runXor() throws Exception {
		alicePrKey.setStart();
		RowVector u1 = base.getValue().xor(alicePubKey.getValue());
		alicePrKey.setValue(u1);
		System.out.println("Calculated Alice's private key in " + alicePrKey.getMillisecond() + " milliseconds!");

		bobPrKey.setStart();
		RowVector u2 = base.getValue().xor(bobPubKey.getValue());
		bobPrKey.setValue(u2);
		System.out.println("Calculated Bobs's private key in " + bobPrKey.getMillisecond() + " milliseconds!");

		aliceSharedKey.setStart();
		RowVector k1 = alicePrKey.getValue().xor(bobPubKey.getValue());
		aliceSharedKey.setValue(k1);
		System.out.println("Calculated Alice's shared key in " + aliceSharedKey.getMillisecond() + " milliseconds!");

		bobSharedKey.setStart();
		RowVector k2 = bobPrKey.getValue().xor(alicePubKey.getValue());
		bobSharedKey.setValue(k2);
		System.out.println("Calculated Bobs's shared key in " + bobSharedKey.getMillisecond() + " milliseconds!");
		bobSharedKey.getValue().print();
	}


	public void generateParams() throws Exception {
		base.setStart();
		RowVector v = new RowVector(size);
		v.generateRandom();
		base.setValue(v);
		alicePubKey.setStart();
		Matrix m1 = new Matrix(size);
		m1.generateRandom();
		alicePubKey.setValue(m1);

		bobPubKey.setStart();
		Matrix m2 = new Matrix(size);
		m2.generateRandom();
		bobPubKey.setValue(m2);
	}

}
