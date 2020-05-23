
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class cownomics5 {
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
		long[][] roll = new long[cows][genomes];
		long[][] noRoll = new long[cows][genomes];
		long prime = BigInteger.probablePrime(31, new Random()).longValue();
		outerloop: {
			for (int i = 1; i < genomes; i++) {
				for (int o = 0; o < genomes - i + 1; o++) {
					Set<Long> spots = new HashSet<Long>();
					Set<Long> noSpots = new HashSet<Long>();
					for (int k = 0; k < cows; k++) {
						roll[k][o] = roll[k][o] == -1 ? -1 : ((4 * roll[k][o]) + thing[k][o + i - 1]) % prime;
						noRoll[k][o] = noRoll[k][o] == -1 ? -1 : ((4 * noRoll[k][o]) + notThing[k][o + i - 1]) % prime;
						if (roll[k][o] != -1)
							spots.add(roll[k][o]);
						if (noRoll[k][o] != -1)
							noSpots.add(noRoll[k][o]);
					}
				
					int cnt = 0;
					for (int k = 0; k < cows; k++) {
						if (noRoll[k][o] == -1) {
							cnt++;
							continue;
						}
						if (!spots.contains(noRoll[k][o])) {
							cnt++;
							noRoll[k][o] = -1;
						}
					}

					if (cnt == cows) {
						out.println(i);
						break outerloop;
					}
					
					for (int k = 0; k < cows; k++) {
						if (roll[k][o] == -1)
							continue;
						if (!noSpots.contains(roll[k][o])) {
							roll[k][o] = -1;
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