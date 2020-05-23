
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class cownomics9 {
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
		long[] roll = new long[cows];
		long[] noRoll = new long[cows];
		long prime = BigInteger.probablePrime(31, new Random()).longValue();
		int minAns = genomes;
		int endValue = 1;

		outer: for (int o = 0; o < genomes; o++) {
			endValue = Math.max(endValue, o + 1);
			roll = new long[cows];
			noRoll = new long[cows];
			for (int i = o; i < endValue - 1; i++) {
				for (int k = 0; k < cows; k++) {
					roll[k] = ((4 * roll[k]) + thing[k][i]) % prime;
					noRoll[k] = ((4 * noRoll[k]) + notThing[k][i]) % prime;
				}
			}
			for (int end = endValue; end <= genomes; end++) {
				Set<Long> spots = new HashSet<Long>();
				Set<Long> noSpots = new HashSet<Long>();
				for (int k = 0; k < cows; k++) {
					roll[k] = ((4 * roll[k]) + thing[k][end - 1]) % prime;
					noRoll[k] = ((4 * noRoll[k]) + notThing[k][end - 1]) % prime;
					spots.add(roll[k]);
					noSpots.add(noRoll[k]);
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

			break;

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