
/*
ID: dingarv1
LANG: JAVA
TASK: kimbits
*/

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class kimbits {
	private static long[][] dp = new long[33][33];

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new File("kimbits.out"));
		int n = in.nextInt();
		int l = in.nextInt();
		long i = in.nextLong();
		intialize();

		out.println(recursion(i, n, l, ""));
		in.close();
		out.close();

	}

	public static String recursion(long i, int n, int m, String a) {
		if (n == 0) {
			return a;
		}
		if (i <= dp[n - 1][m]) {

			return recursion(i, n-1, m, a + "0");
		}
		return recursion(i-dp[n - 1][m], n-1, m-1, a + "1");
	}

	public static void intialize() {
		for (int i = 0; i < 33; i++) {
			dp[0][i] = 1;
			dp[i][0] = 1;
		}
		for (int i = 1; i < 33; i++) {
			for (int k = 1; k < 33; k++) {
				dp[i][k] = dp[i - 1][k] + dp[i - 1][k - 1];
			}
		}
	}
}
