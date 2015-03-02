import java.io.File;

/**
 * Created by Bacho on 2/25/15.
 */


public class Main {

	public static void main(String args[]) throws Exception {
		DHKeySimulate dhKeySimulate = new DHKeySimulate();
		if (args.length == 2 && args[0].equals("-gen")) {
			int bitLen = Integer.parseInt(args[1]);
			if (bitLen == 512 || bitLen == 1024 || bitLen == 2048) {
				dhKeySimulate.generateParams(bitLen);
			} else {
				showUsage();
				return;
			}
		} else {
			dhKeySimulate.scanParams();
		}
		dhKeySimulate.run();
		dhKeySimulate.exportData(new File("data.txt"));
	}

	private static void showUsage() {
		System.err.print("DHKeySimulate usage: ");
		System.err.println("-gen bitLen[512, 1024, 2048]");
	}
}
