
import java.io.*;
import java.util.*;

public class barnpainting2 {
	private static long[][] dp;
	private static int divide = 1000000007;
	private static List<Integer>[] info;
	private static int[] concrete;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter(new File("barnpainting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int barns = Integer.parseInt(read.nextToken());
		int facts = Integer.parseInt(read.nextToken());
		info = new List[barns];
		concrete = new int[barns];
		Arrays.fill(concrete, -1);
		dp = new long[barns][3];
		for (int i = 0; i < barns; i++) {
			info[i] = new ArrayList<Integer>();
			Arrays.fill(dp[i], -1);
		}
		for (int i = 0; i < barns - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			info[a].add(b);
			info[b].add(a);
		}
		for (int i = 0; i < facts; i++) {
			read = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(read.nextToken()) - 1;
			int value = Integer.parseInt(read.nextToken()) - 1;
			dp[index][(value + 1) % 3] = 0;
			dp[index][(value + 2) % 3] = 0;
		}
		out.println((recursion(0, 0, -1) + recursion(0, 1, -1) + recursion(0, 2, -1)) % divide);
		in.close();
		out.close();
	}

	private static long recursion(int node, int value, int previous) {
		if (dp[node][value] >= 0) {
			return dp[node][value];
		}
		long possible = 1;
		for (int neighbors : info[node]) {
			if (neighbors != previous) {
				long sum = 0;
				sum += recursion(neighbors, (value + 1) % 3, node);
				sum += recursion(neighbors, (value + 2) % 3, node);
				possible *= sum;
				possible %= divide;
			}
		}

		dp[node][value] = possible;
		return possible;
	}
}
