
import java.io.*;
import java.util.*;

public class odometer {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("odometer.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		long start = Long.parseLong(read.nextToken()) - 1;
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
				long minus = solve(startStr, a, b);
			//	System.out.println(minus + " " + a + " " + b);
				first -= minus;
				minus = solve(endStr, a, b);
			//	System.out.println(minus + " " + a + " " + b);
				second -= minus;
			}
		}
		out.println(second - first);
		in.close();
		out.close();
	}

	private static long solve(String end, int current, int dupe) {
		int length = end.length();
		long[][][][] dp;

		dp = new long[length + 1][((length + 1) / 2) + 1][2][2];

		// 3rd 0-1 0=not-under, 1= under
		// 4th 1= leading zero

		dp[0][((length + 1) / 2)][0][1] = 1;

		for (int digits = 0; digits < length; digits++) {
			int important = (int) end.charAt(digits) - 48;
			for (int use = 0; use <= 9; use++) {
				if (dupe != -1) {
					if (use != 0 && use != current && use != dupe) {
						continue;
					}
				}
				int add = 0;
				if (use == current) {
					add = -1;
				}
				if (digits == 0 && use > important)
					break;
				int special = 0;
				if ((length + digits) % 2 == 1)
					special = -1;

				if (use == 0) {
					for (int i = 0; i <= ((length + 1) / 2); i++) {
						if (i == 0 && add == -1 && dupe != -1) {
							continue;
						}
						if (use < important) {
							if (dupe == -1||current==0)
								dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][0];
							dp[digits + 1][Math.max(0, i + special)][1][1] += dp[digits][i][0][1];
						} else if (use == important) {
							if (dupe == -1||current==0)
								dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][0];
							dp[digits + 1][Math.max(0, i + special)][0][1] += dp[digits][i][0][1];
						}
						if (dupe == -1||current==0)
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][0];
						dp[digits + 1][Math.max(0, i + special)][1][1] += dp[digits][i][1][1];

					}
				} else {
					for (int i = 0; i <= ((length + 1) / 2); i++) {

						if (i == 0 && add == -1 && dupe != -1) {
							continue;
						}
						if (use < important) {
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][0];
							if (dupe == -1 || (length - digits) % 2 == 0)
								dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][0][1];
						} else if (use == important) {
							dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][0];
							if (dupe == -1 || (length - digits) % 2 == 0)
								dp[digits + 1][Math.max(0, i + add)][0][0] += dp[digits][i][0][1];
						}
						dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][0];
						if (dupe == -1 || (length - digits) % 2 == 0)
							dp[digits + 1][Math.max(0, i + add)][1][0] += dp[digits][i][1][1];
					}

				}
			}
		}
//		for (int ci = 0; ci <= length; ci++) {
//			for (int rr = 0; rr <= ((length + 1) / 2); rr++) {
//				for (int ul = 0; ul < 2; ul++) {
//					for (int i0 = 0; i0 < 2; i0++) {
//						System.out.printf("(%d,%d,%s,%s):%d\n", ci, rr, ul == 1 ? "T" : "F", i0 == 1 ? "T" : "F",
//								dp[ci][rr][ul][i0]);
//					}
//				}
//			}
//		}
		return dp[length][0][0][0] + dp[length][0][1][0];

	}
}