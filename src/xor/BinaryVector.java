package xor;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Bacho on 3/17/15.
 */
public class BinaryVector {
	private final int size;
	private final BigInteger value;

	public BinaryVector(int size) {
		this.size = size;
		value = new BigInteger(size, new Random());
	}

	public BinaryVector(int size, BigInteger value) {
		this.size = size;
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public boolean testAt(int n) {
		return value.testBit(n);
	}
}
