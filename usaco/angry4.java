
import java.io.*;
import java.util.*;

public class angry4 {
	private static int[][] dp;
	private static int[] info;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new File("angry.out"));
		n = Integer.parseInt(in.readLine());
		info = new int[n];
		double print = Integer.MAX_VALUE;
		dp = new int[n][2];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(info);
		for (int i = 0; i < n; i++) {
			print = Math.min(Math.max(recursion(i, -1), recursion(i, 1)), print);

		}
		for (int i = 0; i < n - 1; i++) {
			double cost = (info[i + 1] - info[i]) / 2d;
			cost = Math.max(cost, 1 + Math.max(recursion(i, -1), recursion(i + 1, 1)));
			print = Math.min(cost, print);

		}

		out.printf("%.1f", print);

		out.close();
		in.close();
	}

	private static int recursion(int index, int direction) {
		//System.out.println(index + " " + direction);
		if (index == n - 1 && direction == 1)
			return 0;
		if (index == 0 && direction == -1)
			return 0;
		if (dp[index][(direction == -1) ? 0 : 1] != 0)
			return dp[index][(direction == -1) ? 0 : 1];
		
		int answer = Math.max(Math.abs(info[index] - info[index + direction]),
				1 + recursion(index + direction , direction));
		dp[index][(direction == -1) ? 0 : 1] = answer;
		return answer;
	}
}