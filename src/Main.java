import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import java.math.BigInteger;
import java.security.*;

/**
 * Created by Bacho on 2/25/15.
 */
public class Main {
	public static void main(String argv[]) throws Exception {
		DHParameterSpec dhParameterSpec = getDHParameters(new BigInteger("9223372036854775808"), new BigInteger("5"));
//		DHParameterSpec dhParameterSpec = getDHParameters();
		System.out.print("g = " + dhParameterSpec.getG() + "\np = " + dhParameterSpec.getP() + "\n");
		BigInteger alicePrKey = new BigInteger("6");
		BigInteger alicePubKey = dhParameterSpec.getG().modPow(alicePrKey, dhParameterSpec.getP());
		System.out.println(alicePubKey);

		BigInteger bobPrKey = new BigInteger("15");
		BigInteger bobPubKey = dhParameterSpec.getG().modPow(bobPrKey, dhParameterSpec.getP());
		System.out.println(bobPubKey);

		BigInteger sharedKeyAlice = bobPubKey.modPow(alicePrKey, dhParameterSpec.getP());
		BigInteger sharedKeyBob = alicePubKey.modPow(bobPrKey, dhParameterSpec.getP());
		System.out.println(sharedKeyAlice);
		System.out.println(sharedKeyBob);
		System.out.println(sharedKeyAlice.equals(sharedKeyBob));
	}

	private static DHParameterSpec getDHParameters() throws Exception {
		// Some central authority creates new DH parameters
		System.out.println("Creating Diffie-Hellman parameters (takes VERY long) ...");
		AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
		paramGen.init(2048);
		AlgorithmParameters params = paramGen.generateParameters();
		return params.getParameterSpec(DHParameterSpec.class);
	}

	private static DHParameterSpec getDHParameters(BigInteger p, BigInteger g) throws Exception {
		return new DHParameterSpec(p, g);
	}
}
