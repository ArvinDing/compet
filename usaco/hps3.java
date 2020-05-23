
import java.io.*;
import java.util.*;

public class hps3 {
	private static int[][][] dp;
	private static int[] info;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int mChanges = Integer.parseInt(read.nextToken());
		info = new int[lines];
		dp = new int[lines][mChanges + 1][3];
		for (int i = 0; i < lines; i++) {
			String curr = in.readLine();
			if (curr.equals("P")) {
				info[i] = 0;
			} else if (curr.equals("H")) {
				info[i] = 1;
			} else {
				info[i] = 2;

			}
		}
		out.println(Math.max(Math.max(recursion(lines - 1, mChanges, 0), recursion(lines - 1, mChanges, 1)),
				recursion(lines - 1, mChanges, 2)));

		in.close();
		out.close();
	}

	private static int recursion(int position, int changes, int curr) {
		if (dp[position][changes][curr] != 0)
			return dp[position][changes][curr];
		int max = 0;
		if (position != 0) {
			max = recursion(position - 1, changes, curr);
			if (changes != 0) {
				max = Math.max(max, recursion(position - 1, changes - 1, (curr + 1) % 3));
				max = Math.max(max, recursion(position - 1, changes - 1, (curr + 2) % 3));
			}
		}
		if (curr == info[position])
			max++;
		dp[position][changes][curr] = max;
		return max;
	}

}