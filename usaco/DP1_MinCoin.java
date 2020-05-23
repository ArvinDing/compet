
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP1_MinCoin {
	private static int[] dp, cs;
	private static final int MAX = Integer.MAX_VALUE / 2;

	public static int minCoin(int[] coins, int sum) {
		PriorityQueue<Integer> solution = new PriorityQueue<Integer>();
		int[] dp = new int[10001];

		Arrays.sort(coins);
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int a : coins) {
			dp[a] = 1;
			solution.add(a);
		}
		while (true) {
			int coin = solution.poll();
			System.out.println(coin + " " + dp[coin]);
			if (coin > sum) {
				return -1;
			}
			if (coin == sum) {
				return dp[coin];
			}
			for (int a : coins) {
				if (coin + a > sum) {
					break;
				}
				if (coin + a <= 10001) {
					if (dp[coin + a] > dp[coin] + 1) {
						dp[coin + a] = dp[coin] + 1;
						solution.add(coin + a);
					}

				}
			}
		}
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			String[] line = in.readLine().split(" ");
			int[] coins = new int[Integer.parseInt(line[0])];
			int sum = Integer.parseInt(line[1]);
			for (int i = 0; i < coins.length; i++) {
				coins[i] = Integer.parseInt(in.readLine());
			}
			System.out.println(minCoin(coins, sum));
		}
	}
}