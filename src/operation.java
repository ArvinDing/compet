
import java.io.*;
import java.util.*;

public class operation {
	static double[] info = { 5.75, 1.47, 0.04, 29D / 6, 2.4, 0.6 };
	static int n;
	static List<link>[][] dp;
	static boolean[][] done;

	public static void main(String[] args) throws IOException {
		double goal = 216.6;
		n = info.length;
		dp = new List[n][n];
		done = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				dp[i][k] = new ArrayList<link>();
			}
		}
		recur(0,5);
		for (link a : dp[0][5]) {
			if(goal-0.2<a.d&&a.d<goal+0.2)
				System.out.println(a.thing);
		}
	}

	static void recur(int start, int end) {
		if (done[start][end])
			return;
		if (start == end) {
			dp[start][end].add(new link(info[start], info[start] + ""));
			done[start][end] = true;
			return;
		}
		for (int i = start; i < end; i++) {
			recur(start, i);
			recur(i + 1, end);
			for (link a : dp[start][i]) {
				for (link b : dp[i + 1][end]) {
					double aval = a.d;
					double bval = b.d;
					String astr = a.thing;
					String bstr = b.thing;
					dp[start][end].add(new link(aval + bval, "(" + astr + ")+(" + bstr + ")"));
					dp[start][end].add(new link(aval - bval, "(" + astr + ")-(" + bstr + ")"));
					dp[start][end].add(new link(aval * bval, "(" + astr + ")*(" + bstr + ")"));
					dp[start][end].add(new link(aval / bval, "(" + astr + ")/(" + bstr + ")"));
				}
			}
		}
		done[start][end] = true;

	}

	public static class link {
		public link(double d, String thing) {
			this.d = d;
			this.thing = thing;
		}

		Double d;
		String thing;
	}

}
