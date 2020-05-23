
import java.io.*;
import java.util.*;

public class palpath1 {
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new File("palpath.out"));
		int square = Integer.parseInt(in.readLine());
		dp = new int[square][square];
		char[][] info = new char[square][square];
		for (int i = 0; i < square; i++) {
			String line = in.readLine();
			for (int k = 0; k < square; k++) {
				info[i][k] = line.charAt(k);
			}
		}
		for (int i = 0; i < square; i++) {
			dp[i][i] = 1;
		}

		for (int i = 0; i < square; i++) {
			int[][] next = new int[square][square];
			for (int first = 0; first < square - i; first++) {
				for (int second = i; second < square; second++) {
					int y = first;
					int x = ((square - 1) - i) - y;
					int y1 = second;
					int x1 = ((square - 1) + i) - y1;
					int curr = dp[first][second];

					if (y - 1 >= 0) {
						if (y1 + 1 < square && info[y1 + 1][x1] == info[y - 1][x]) {
							next[first - 1][second + 1] += curr;
							next[first - 1][second + 1] %= 1000000007;
						}
						if (x1 + 1 < square && info[y1][x1 + 1] == info[y - 1][x]) {
							next[first - 1][second] += curr;
							next[first - 1][second] %= 1000000007;
						}
					}
					if (x - 1 >= 0) {
						if (y1 + 1 < square && info[y1 + 1][x1] == info[y][x - 1]) {
							next[first][second + 1] += curr;
							next[first][second + 1] %= 1000000007;
						}
						if (x1 + 1 < square && info[y1][x1 + 1] == info[y][x - 1]) {
							next[first][second] += curr;
							next[first][second] %= 1000000007;
						}
					}
				}
			}
			if (i != square - 1)
				for (int y = 0; y < square; y++) {
					for (int x = 0; x < square; x++) {
						dp[y][x] = next[y][x];
					}
				}
		}
		out.println(dp[0][square - 1]);
		out.close();
		in.close();
	}

}