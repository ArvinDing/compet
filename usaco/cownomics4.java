
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class cownomics4 {
	private static BufferedReader in;
	private static int cows;
	private static int genomes;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new File("cownomics.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		cows = Integer.parseInt(read.nextToken());
		genomes = Integer.parseInt(read.nextToken());
		char[][] spotty = read();
		char[][] notSpotty = read();
		int[][] thing = convert(spotty);
		int[][] notThing = convert(notSpotty);
		String[][] dp = new String[cows][genomes];
		String[][] notDp = new String[cows][genomes];
		long[][] roll = new long[cows][genomes];
		long[][] noRoll = new long[cows][genomes];
		long prime = BigInteger.probablePrime(31, new Random()).longValue();
		for (int i = 0; i < cows; i++)
			for (int k = 0; k < genomes; k++) {
				dp[i][k] = "";
				notDp[i][k] = "";
			}
		outerloop: {
			for (int i = 1; i < genomes; i++) {
				for (int o = 0; o < genomes - i + 1; o++) {

					Set<Long> spotsI = new HashSet<Long>();
					Set<Long> noSpotsI = new HashSet<Long>();
					for (int k = 0; k < cows; k++) {
						roll[k][o] = ((4 * roll[k][o]) + thing[k][o + i - 1]) % prime;
						noRoll[k][o] = ((4 * noRoll[k][o]) + notThing[k][o + i - 1]) % prime;
						dp[k][o] += spotty[k][o + i - 1];
						notDp[k][o] += notSpotty[k][o + i - 1];

						spotsI.add(roll[k][o]);
						noSpotsI.add(noRoll[k][o]);
					}
					Set<Long> repeatsI = new HashSet<Long>();
					repeatsI.addAll(spotsI);
					repeatsI.addAll(noSpotsI);
					if (repeatsI.size() == spotsI.size() + noSpotsI.size()) {
						Set<String> repeats = new HashSet<String>();
						Set<String> spots = new HashSet<String>();
						Set<String> noSpots = new HashSet<String>();
						for (int k = 0; k < cows; k++) {
							repeats.add(dp[k][o]);
							repeats.add(notDp[k][o]);
							spots.add(dp[k][o]);
							noSpots.add(notDp[k][o]);
						}
						if (repeats.size() == spots.size() + noSpots.size()) {
							out.println(i);
							break outerloop;
						}
					}
				}
			}
			out.println(genomes);
		}
		in.close();
		out.close();
	}

	private static char[][] read() throws IOException {
		char[][] temp = new char[cows][genomes];
		for (int i = 0; i < cows; i++) {
			String curr = in.readLine();
			for (int k = 0; k < genomes; k++) {
				temp[i][k] = curr.charAt(k);
			}
		}
		return temp;
	}

	private static int[][] convert(char[][] dp) {
		int[][] temp = new int[cows][genomes];
		for (int i = 0; i < cows; i++) {
			for (int k = 0; k < genomes; k++) {
				switch (dp[i][k]) {
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