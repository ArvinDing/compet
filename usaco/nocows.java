
/*
ID: dingarv1
LANG: JAVA
TASK: nocows
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class nocows {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new File("nocows.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][K + 1];

		for (int k = 1; k <= K; k++) {
			dp[1][k] = 1;

			for (int n = 1; n <= N; n += 2)
				for (int i = 1; i <= n - 2; i++)
					dp[n][k] = (dp[n][k] + dp[i][k - 1] * dp[n - 1 - i][k - 1]) % 9901;
		}
		for (int k = 1; k <= K; k++) {
			for (int n = 1; n <= N; n += 2) {
				System.out.print((dp[n][k] - dp[n][k - 1] + 9901) % 9901+" ");
			}
			System.out.println();
		}
		out.println((dp[N][K] - dp[N][K - 1] + 9901) % 9901);

		in.close();
		out.close();
	}
}
