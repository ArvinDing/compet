
import java.io.*;
import java.util.*;

public class odometer4 {

	public static void main1(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("odometer.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(read.nextToken()) - 1;
		String end = read.nextToken();
		String startStr = "" + start;
		String endStr = "" + end;
		long first = 0;
		long second = 0;
		for (int i = 0; i <= 9; i++) {
			first += solve(startStr, i, -1);
			second += solve(endStr, i, -1);
		}

		for (int a = 0; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {

				first -= solve(startStr, a, b);
				second -= solve(endStr, a, b);
			}
		}
		out.println(second - first);
		in.close();
		out.close();
	}

	public static void main(String[] argv) throws IOException {
		for (int i = 1; i <= 9; i++) {
			System.out.println(i + ":" + solve("111", i, -1));
		}
	}

	private static long solve(String end, int current, int dupe) {
		int length = end.length();

		long[][][][] dp = new long[length + 1][((length + 1) / 2) + 1][2][2];
		// 3rd 0-1 0=not-under, 1= not-under, be careful when 1
		// 4th 1= leading zero
		dp[0][((length + 1) / 2)][0][1] = 1;
		for (int digits = 0; digits < length; digits++) {
			int important = (int) end.charAt(digits) - 48;
			for (int use = 0; use <= 9; use++) {
				boolean beCareful = false;
				if (digits == length - 1) {
					beCareful = use > important;
				} else if (digits == 0 && use > important)
					break;
				int add = 0;
				if (use == current)
					add = -1;
				int special = 0;
				if ((length + digits) % 2 == 1)
					special = -1;
				boolean flag = false;
				if (digits == 0) {
					flag = use == important;
				}
				if (use == 0) {
					for (int i = 0; i <= ((length + 1) / 2); i++) {
						if (!flag) {
							dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][0];
							dp[digits + 1][Math.max(0, i + special)][0][1] += dp[digits][i][0][1];

						} else {
							if (!beCareful) {
								dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][0];
								dp[digits + 1][Math.max(0, i + special)][1][1] += dp[digits][i][0][1];
							}
						}
						if (!beCareful) {
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][0];
							dp[digits + 1][Math.max(0, i + special)][1][1] += dp[digits][i][1][1];
						}
					}
				} else {
					for (int i = 0; i <= ((length + 1) / 2); i++) {
						if (!flag) {
							dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][0];
							dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][1];
						} else {
							if (!beCareful) {
								dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][0];
								dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][1];
							}
						}
						if (!beCareful) {
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][0];
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][1];
						}
					}
				}
			}
		}

		return dp[length][0][0][0] + dp[length][0][1][0];

	}

}