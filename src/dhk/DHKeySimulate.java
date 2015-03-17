package dhk;

import javax.crypto.spec.DHParameterSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Bacho on 2/25/15.
 * Taken from example http://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html#DH2Ex
 */

public class DHKeySimulate {
	private Value<BigInteger> base = new Value<BigInteger>("Base:");
	private Value<BigInteger> mod = new Value<BigInteger>("Mod:");
	private Value<BigInteger> alicePrKey = new Value<BigInteger>("Alice's private key:");
	private Value<BigInteger> alicePubKey = new Value<BigInteger>("Alice's public key:");
	private Value<BigInteger> bobPrKey = new Value<BigInteger>("Bob's private key:");
	private Value<BigInteger> bobPubKey = new Value<BigInteger>("Bob's public key:");
	private Value<BigInteger> aliceSharedKey = new Value<BigInteger>("Alice's shared key:");
	private Value<BigInteger> bobSharedKey = new Value<BigInteger>("Bob's shared key:");

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
		printStream.println(mod.getExportText());
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

	public void run() throws Exception {

		alicePubKey.setStart();
		BigInteger temp1 = base.getValue().modPow(alicePrKey.getValue(), mod.getValue());
		alicePubKey.setValue(temp1);
		System.out.println("Calculated Alice's public key in " + alicePubKey.getMillisecond() + " milliseconds!");

		bobPubKey.setStart();
		BigInteger temp2 = base.getValue().modPow(bobPrKey.getValue(), mod.getValue());
		bobPubKey.setValue(temp2);
		System.out.println("Calculated Bobs's public key in " + bobPubKey.getMillisecond() + " milliseconds!");

		aliceSharedKey.setStart();
		BigInteger temp3 = bobPubKey.getValue().modPow(alicePrKey.getValue(), mod.getValue());
		aliceSharedKey.setValue(temp3);
		System.out.println("Calculated Alice's shared key in " + aliceSharedKey.getMillisecond() + " milliseconds!");

		bobSharedKey.setStart();
		BigInteger temp4 = alicePubKey.getValue().modPow(bobPrKey.getValue(), mod.getValue());
		bobSharedKey.setValue(temp4);
		System.out.println("Calculated Bobs's shared key in " + bobSharedKey.getMillisecond() + " milliseconds!");
	}


	public void scanParams() throws Exception {
		Scanner scanner = new Scanner(System.in);
		base.setStart();
		System.out.println("Enter " + base.getText());
		base.setValue(scanner.nextBigInteger());

		mod.setStart();
		System.out.println("Enter " + mod.getText());
		mod.setValue(scanner.nextBigInteger());

		alicePrKey.setStart();
		System.out.println("Enter " + alicePrKey.getText());
		alicePrKey.setValue(scanner.nextBigInteger());

		bobPrKey.setStart();
		System.out.println("Enter " + bobPrKey.getText());
		bobPrKey.setValue(scanner.nextBigInteger());
	}

	public void generateParams(int bitLen) throws Exception {
		base.setStart();
		mod.setStart();
		AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
		paramGen.init(bitLen);
		AlgorithmParameters params = paramGen.generateParameters();
		DHParameterSpec dhParameterSpec = params.getParameterSpec(DHParameterSpec.class);
		base.setValue(dhParameterSpec.getG());
		mod.setValue(dhParameterSpec.getP());

		alicePrKey.setStart();
		alicePrKey.setValue(new BigInteger(dhParameterSpec.getL(), new Random()));

		bobPrKey.setStart();
		bobPrKey.setValue(new BigInteger(dhParameterSpec.getL(), new Random()));
	}

}
