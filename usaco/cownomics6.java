
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class cownomics6 {
	private static BufferedReader in;
	private static int cows;
	private static int genomes;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new File("cownomics.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		cows = Integer.parseInt(read.nextToken());
		genomes = Integer.parseInt(read.nextToken());

		int[][] thing = read();
		int[][] notThing = read();
		long[][] roll = new long[cows][genomes + 1];
		long[][] noRoll = new long[cows][genomes + 1];
		long prime = BigInteger.probablePrime(31, new Random()).longValue();

		int minAns = Integer.MAX_VALUE;
		int startEndValue = 1;

		outer: for (int o = 0; o < genomes; o++) {
			if (o != 0) {
				for (int k = 0; k < cows; k++) {
					roll[k][startEndValue - 1] -= thing[k][o - 1] * Math.pow(4, startEndValue - o - 1);
					roll[k][startEndValue - 1] %= prime;
					noRoll[k][startEndValue - 1] -= notThing[k][o - 1] * Math.pow(4, startEndValue - o - 1);
					noRoll[k][startEndValue - 1] %= prime;
				}
			}
			for (int len = startEndValue - o; len + o <= genomes; len++) {
				Set<Long> spots = new HashSet<Long>();
				Set<Long> noSpots = new HashSet<Long>();
				for (int k = 0; k < cows; k++) {
					roll[k][o + len] = ((4 * roll[k][o + len - 1]) + thing[k][o + len - 1]) % prime;
					noRoll[k][o + len] = ((4 * noRoll[k][o + len - 1]) + notThing[k][o + len - 1]) % prime;
					spots.add(roll[k][o + len]);
					noSpots.add(noRoll[k][o + len]);
				}
				Set<Long> repeats = new HashSet<Long>();
				repeats.addAll(spots);
				repeats.addAll(noSpots);

				if (repeats.size() == spots.size() + noSpots.size()) {
					minAns = Math.min(len, minAns);
					startEndValue = o + len;
					continue outer;
				}
			}

		}

		out.println(minAns);
		in.close();
		out.close();

	}

	private static int[][] read() throws IOException {
		int[][] temp = new int[cows][genomes];
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				switch (curr.charAt(k)) {
				case 'A':
					temp[i][k] = 0;
					break;
				case 'C':
					temp[i][k] = 1;
					break;
				case 'G':
					temp[i][k] = 2;
					break;
				case 'T':
					temp[i][k] = 3;
					break;
				}
			}
		}
		return temp;
	}

}