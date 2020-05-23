
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class cownomics7 {
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
		int endValue = 1;
		int[] pow4 = new int[genomes];
		pow4[0] = 1;
		for (int i = 1; i < genomes; i++) {
			pow4[i] = pow4[i - 1] * 4;
			pow4[i] %= prime;
		}
		outer: for (int o = 0; o < genomes; o++) {
			endValue = Math.max(endValue, o + 1);
			if (o != 0) {
				for (int k = 0; k < cows; k++) {
					roll[k][endValue - 1] -= thing[k][o - 1] * pow4[endValue - o - 1];
					roll[k][endValue - 1] %= prime;
					noRoll[k][endValue - 1] -= notThing[k][o - 1] * pow4[endValue - o - 1];
					noRoll[k][endValue - 1] %= prime;
				}
			}
			for (int end = endValue; end <= genomes; end++) {
				Set<Long> spots = new HashSet<Long>();
				Set<Long> noSpots = new HashSet<Long>();
				for (int k = 0; k < cows; k++) {
					roll[k][end] = ((4 * roll[k][end - 1]) + thing[k][end - 1]) % prime;
					noRoll[k][end] = ((4 * noRoll[k][end - 1]) + notThing[k][end - 1]) % prime;
					spots.add(roll[k][end]);
					noSpots.add(noRoll[k][end]);
				}
				Set<Long> repeats = new HashSet<Long>();
				repeats.addAll(spots);
				repeats.addAll(noSpots);

				if (repeats.size() == spots.size() + noSpots.size()) {
					minAns = Math.min(end - o, minAns);
					endValue = end;
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
		for (int i = 0; i    < cows; i++) {
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