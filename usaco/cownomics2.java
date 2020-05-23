
import java.io.*;
import java.util.*;

public class cownomics2 {
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
		String[][] dp = new String[cows][genomes];
		String[][] notDp = new String[cows][genomes];
		for (int i = 0; i < cows; i++)
			for (int k = 0; k < genomes; k++) {
				dp[i][k] = "";
				notDp[i][k] = "";
			}
		outerloop: {
			for (int i = 1; i < genomes; i++) {
				for (int o = 0; o < genomes - i + 1; o++) {
					Set<String> repeats = new HashSet<String>();
					Set<String> spots = new HashSet<String>();
					Set<String> noSpots = new HashSet<String>();
					for (int k = 0; k < cows; k++) {
						dp[k][o] += spotty[k][o + i - 1];
						notDp[k][o] += notSpotty[k][o + i - 1];
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

}