
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class hopscotch3 {
	private static final long mod = 1000000007;
	private static long[][] bit;
	private static int width;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new File("hopscotch.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(read.nextToken());
		width = Integer.parseInt(read.nextToken());
		int numbers = Integer.parseInt(read.nextToken());
		int[][] info = new int[height][width];
		for (int i = 0; i < height; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < width; k++) {
				info[i][k] = Integer.parseInt(read.nextToken()) - 1;
			}
		}
		int[] cnts = new int[numbers];
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				cnts[info[i][k]]++;
			}
		}
		long[][] dp = new long[height][width];
		dp[0][0] = 1;

		bit = new long[numbers][];
		long[] temp = new long[width];

		for (int i = 1; i < height; i++) {
			long paths = 0;
			for (int k = 1; k < width; k++) {
				if (bit[info[i - 1][k - 1]] == null)
					bit[info[i - 1][k - 1]] = new long[width + 1];
				if (bit[info[i][k]] == null)
					bit[info[i][k]] = new long[width + 1];

				update(k - 1, dp[i - 1][k - 1], info[i - 1][k - 1]);
				temp[k - 1] += dp[i - 1][k - 1];
				temp[k - 1] %= mod;
				paths += temp[k - 1];
				paths %= mod;
				dp[i][k] = paths;
				dp[i][k] -= get(k - 1, info[i][k]);
				dp[i][k] = (dp[i][k] + mod) % mod;
				cnts[info[i-1][k-1]]--;
				if (cnts[info[i-1][k-1]] == 0) {
					bit[info[i-1][k-1]] = null;
				}
			}
		}
		// DataUtil.printArray(dp);
		out.println(dp[height - 1][width - 1] % 1000000007);
		in.close();
		out.close();
	}

	private static void update(int index, long value, int number) {
		index++;
		while (index <= width) {
			bit[number][index] += value;
			bit[number][index] = (bit[number][index]) % mod;
			index += index & (-index);
		}
	}

	private static long get(int index, int number) {
		long sum = 0;
		index++;
		while (index > 0) {
			sum += bit[number][index];
			index = index & (index - 1);
		
				sum %= mod;
		}

		return sum;
	}

}