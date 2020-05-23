package save;

/*
ID: dingarv1
LANG: JAVA
TASK: vans
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class vans {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("vans.in"));
		PrintWriter out = new PrintWriter(new File("vans.out"));
		int n = Integer.parseInt(in.readLine()) - 1;
		BigInteger[][] dp = new BigInteger[n][7];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < 7; k++) {
				dp[i][k] = BigInteger.ZERO;
			}
		}
		int[][] connection = new int[7][7];
		connection[0] = new int[] { 1, 1, 0, 0, 0, 0, 0 };
		connection[1] = new int[] { 0, 0, 1, 0, 1, 0, 0 };
		connection[2] = new int[] { 2, 2, 0, 1, 0, 1, 0 };
		connection[3] = new int[] { 0, 0, 1, 0, 0, 0, 0 };
		connection[4] = new int[] { 0, 0, 1, 0, 1, 0, 0 };
		connection[5] = new int[] { 0, 0, 0, 0, 0, 1, 0 };
		connection[6] = new int[] { 0, 0, 1, 0, 0, 0, 1 };
		if (n == 0)
			out.println(0);
		else {
			dp[0][2] = BigInteger.ONE;
			dp[0][6] = BigInteger.ONE;
			for (int i = 0; i < n - 1; i++) {
				for (int a = 0; a < 7; a++) {
					for (int b = 0; b < 7; b++) {
						dp[i + 1][b] = dp[i + 1][b].add(dp[i][a].multiply(BigInteger.valueOf(connection[a][b])));
					}
				}
			}

			out.println((dp[n - 1][5].add(dp[n - 1][2])).multiply(BigInteger.valueOf(2)));
		}
		out.close();
		in.close();
	}
}