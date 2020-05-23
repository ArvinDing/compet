import java.io.*;
import java.util.*;

public class two48 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new File("248.out"));
		int n = Integer.parseInt(in.readLine());
		int[] info = new int[n];
		for (int i = 0; i < n; i++)
			info[i] = Integer.parseInt(in.readLine());
		int[][] dp = new int[n][n];
		int max=0;
		for (int length = 0; length < n; length++) {
			for (int i = 0; i + length < n; i++) {
				if (length == 0) {
					dp[i][i] = info[i];
				} else {
					for (int j = i; j < i + length; j++) {
						if (dp[i][j] == dp[j + 1][i + length] && dp[i][j] != 0) {
							dp[i][i + length] = Math.max(dp[i][i + length], dp[i][j] + 1);
						}
					}
				}
				max=Math.max(dp[i][i+length], max);
			}
		}		
		out.println(max);
		out.close();
		in.close();
	}
}