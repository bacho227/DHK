package xor;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Bacho on 3/17/15.
 */
public class BinaryMatrix {
	private final int size;
	private final BigInteger[] value;

	public BinaryMatrix(BigInteger[] value) {
		this.size = value.length;
		this.value = value;
	}

	public BinaryMatrix(int size) {
		this.size = size;
		this.value = new BigInteger[size];
	}

	public void generateRandom() {
		for (int i = 0; i < size; i++) {
			value[i] = new BigInteger(size, new Random());
		}
	}

	public BigInteger getRow(int n) {
		return value[n];
	}

	public int getSize() {
		return size;
	}
}
