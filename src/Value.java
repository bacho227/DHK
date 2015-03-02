import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Created by Bacho on 2/25/15.
 */

public class Value {
	private static int maxTextLength;
	private String text;
	private BigInteger value;
	private long start;
	private long end;

	public Value(String text) {
		this.text = text;
		if (text.length() > maxTextLength) {
			maxTextLength = text.length();
		}
	}

	public String getMillisecond() {
		DecimalFormat df = new DecimalFormat("0.000000");
		double diff = (double) (end - start) / 1000000;
		return df.format(diff);
	}

	public String getExportText() {
		String text = "[" + getMillisecond() + " millisecond] " + this.text;
		int diff = 30 + maxTextLength - text.length();
		while (diff-- > 0)
			text += " ";
		return text + " " + this.value;
	}

	public void setStart() {
		this.start = System.nanoTime();
	}

	public void setValue(BigInteger value) {
		this.value = value;
		this.end = System.nanoTime();
	}

	public String getText() {
		return text;
	}

	public BigInteger getValue() {
		return value;
	}
}
