
import java.io.*;
import java.util.*;

public class haywire {
	private static int cows;
	private static int[][] info;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("haywire.in"));
		PrintWriter out = new PrintWriter(new FileWriter("haywire.out"));
		cows = Integer.parseInt(in.readLine());
		info = new int[cows][3];
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken()) - 1;
			info[i][1] = Integer.parseInt(read.nextToken()) - 1;
			info[i][2] = Integer.parseInt(read.nextToken()) - 1;
		}
		Arrays.fill(previousCostArry, Integer.MAX_VALUE);
		out.println(recursion(0, 0, 0));
		in.close();
		out.close();

	}

	private static int dp[] = new int[4097];
	private static int previousCostArry[] = new int[4097];

	private static int recursion(int binaryIndex, int cost, int previousCost) {
		if (previousCostArry[binaryIndex] <= previousCost && dp[binaryIndex] != 0)
			return dp[binaryIndex];

		boolean[] done = new boolean[cows];
		int[] recordNDN = new int[cows];

		int doneCnt = 0;
		for (int i = 0; i < cows; i++) {
			if ((binaryIndex & (1 << i)) != 0) {
				done[i] = true;
				doneCnt++;
			}
		}
		if (doneCnt == cows - 1) {
			return cost;
		}
		for (int i = 0; i < cows; i++) {
			if (done[i]) {
				if (!done[info[i][0]]) {
					recordNDN[info[i][0]]++;
				}
				if (!done[info[i][1]]) {
					recordNDN[info[i][1]]++;
				}
				if (!done[info[i][2]]) {
					recordNDN[info[i][2]]++;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int more = 0;

		for (int i = 0; i < cows; i++) {

			if (!done[i]) {
				if (!done[info[i][0]]) {
					more++;
				}
				if (!done[info[i][1]]) {
					more++;
				}
				if (!done[info[i][2]]) {
					more++;
				}
				min = Math.min(
						recursion(binaryIndex + (1 << i), cost + (cost - previousCost) + more - recordNDN[i], cost),
						min);
			}
			more = 0;
		}
		dp[binaryIndex] = min;
		previousCostArry[binaryIndex] = previousCost;
		return min;

	}

}
