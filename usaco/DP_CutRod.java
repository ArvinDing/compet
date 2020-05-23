
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_CutRod {
	public static int maxPrice(int[] prices, int tLen) {
		int[][] dp = new int[prices.length + 1][tLen + 1];

		for (int i = 1; i < prices.length + 1; i++) {
			for (int k = 0; k < tLen + 1; k++) {

				if (k - i >= 0) {
					dp[i][k] = Math.max(dp[i - 1][k], dp[i][k - i] + prices[i - 1]);
				} else {
					dp[i][k] = dp[i - 1][k];
				}

			}
		}
		return dp[prices.length][tLen];
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

			String[] line = in.readLine().split(" ");
			int n = Integer.parseInt(line[0]), tLen = Integer.parseInt(line[1]);
			int[] prices = new int[n];
			for (int i = 0; i < n; i++) {
				prices[i] = Integer.parseInt(in.readLine());
			}
			System.out.println(maxPrice(prices, tLen));
		}
	}
}