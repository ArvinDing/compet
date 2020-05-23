
import java.io.*;
import java.util.*;

public class msched2 {
	private static int[] dp;
	private static List<Integer>[] reverse;
	private static int[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new File("msched.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int constraints = Integer.parseInt(read.nextToken());
		info = new int[cows];
		for (int i = 0; i < cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}

		reverse = new List[cows];
		dp = new int[cows];
		for (int i = 0; i < constraints; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;

			if (reverse[end] == null)
				reverse[end] = new ArrayList<Integer>();
			reverse[end].add(start);
		}
		for (int i = 0; i < cows; i++) {
			if (reverse[i] == null) {
				dp[i] = info[i];
			}
		}
		int max = 0;
		for (int i = 0; i < cows; i++) {
			max = Math.max(recursion(i), max);
		}
		out.print(max);
		in.close();
		out.close();
	}

	private static int recursion(int end) {
		if (dp[end] == 0) {
			int max = 0;
			for (int a : reverse[end]) {
				max = Math.max(recursion(a), max);
			}
			dp[end] = max + info[end];
			return max + info[end];
		}
		return dp[end];
	}

}
