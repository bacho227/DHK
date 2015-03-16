package matrix;


import java.io.File;

/**
 * Created by Bacho on 3/16/15.
 */
public class Main {
	public static void main(String args[]) throws Exception {
		KeyExchange simulate = new KeyExchange();
		simulate.generateParams();
		simulate.run();
		simulate.exportData(new File("matrix.txt"));
	}
}
