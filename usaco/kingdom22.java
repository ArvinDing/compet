import java.util.*;
import java.io.*;

public class kingdom22 {
	private static long[][][] dp;

	private static List<Integer>[] info;
	private final static int mod = 1000000007;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int cities = Integer.parseInt(in.readLine());

		info = new List[cities];
		dp = new long[cities][2][2];
		for (int i = 0; i < cities - 1; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (info[a] == null)
				info[a] = new ArrayList<Integer>();
			if (info[b] == null)
				info[b] = new ArrayList<Integer>();
			info[a].add(b);
			info[b].add(a);
		}
		System.out.println(2 * (recursion(0, 0, 1, -1)) % mod);
		in.close();
	}

	private static long recursion(int curr, int state, int parentS, int parent) {

		if (dp[curr][state][parentS] != 0)
			return dp[curr][state][parentS];

		long[] only = new long[2];
		only[0] = 1;
		only[1] = 1;
		long total = 1;
		if (curr != 0 && info[curr].size() == 1) {
			dp[curr][state][parentS] = (parentS == state) ? 1 : 0;
			return dp[curr][state][parentS];
		}
		for (int children : info[curr]) {
			if (children != parent) {
				long child0 = recursion(children, 0, state, curr);
				long child1 = recursion(children, 1, state, curr);
				only[0] = (only[0] * child0) % mod;
				only[1] = (only[1] * child1) % mod;
				total = (total * (child0 + child1)) % mod;
			}
		}

		if (parentS != state) {
			total = (total - only[parentS] + mod) % mod;
		}
		dp[curr][state][parentS] = total;
		return total;
	}
}
