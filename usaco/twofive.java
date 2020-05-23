
/*
ID: dingarv1
LANG: JAVA
TASK: twofive
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class twofive {
	private static int[][][][][] dp = new int[5][5][5][5][5];
	private static char[] link = "ABCDEFGHIJKLMNOPQRSTUVWXY".toCharArray();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new File("twofive.out"));
		String possible = in.readLine();
		dp[4][4][4][4][4] = 1;

		if (possible.equals("N")) {
			out.println(solveN(Integer.parseInt(in.readLine())));
		} else {
			out.println(solveW(in.readLine()));
		}

		out.println();
		in.close();
		out.close();

	}

	private static int solveN(int index) {
		char[][] info = new char[5][5];
		int[] check = new int[5];
		int important = -1;
		for (int i = 0; i < 5; i++) {
			check[i] = -1;
			for (int k = 0; k < 5; k++) {
				check[i]++;
				int save = calc(check[0], check[1], check[2], check[3], check[4]);
				if (save < index) {
					important = i * 5 + k;
				}

			}
		}
		return important;
	}

	private static int calc(int a, int b, int c, int d, int e) {
		if (dp[a][b][c][d][e] != 0)
			return dp[a][b][c][d][e];
		int sum = 0;
		if (a + 1 < 5)
			sum += calc(a + 1, b, c, d, e);
		if (b < a)
			sum += calc(a, b + 1, c, d, e);
		if (c < b)
			sum += calc(a, b, c + 1, d, e);
		if (d < c)
			sum += calc(a, b, c, d + 1, e);
		if (e < d)
			sum += calc(a, b, c, d, e + 1);
		dp[a][b][c][d][e] = sum;
		return sum;

	}

	private static int solveW(String i) {
		return 0;
	}
}
