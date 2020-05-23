
import java.io.*;
import java.util.*;

public class guard {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("guard.in"));
		PrintWriter out = new PrintWriter(new File("guard.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int height = Integer.parseInt(read.nextToken());
		int[][] info = new int[cows][3];
		for (int i = 0; i < cows; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < 3; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		int[] dp = new int[(int) (Math.pow(2, cows) + 1)];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = Integer.MAX_VALUE;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		boolean[] done = new boolean[(int) (Math.pow(2, cows ) + 1)];
		int max = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int sum = 0;
			int important = 1;
			for (int i = 0; i < cows; i++) {
				if ((curr & important) == important) {
					sum += info[i][0];
				} else {
					int next = curr + important;
					dp[next] = Math.max(dp[next], Math.min(dp[curr] - info[i][1], info[i][2]));
					if (!done[next]) {
						done[next] = true;
						queue.add(next);
					}
				}
				important = important << 1;
			}
			if (sum >= height) {
				max = Math.max(max, dp[curr]);
			}
		}
		if (max < 0) {
			out.println("Mark is too tall");
		} else {
			out.println(max);
		}
		in.close();
		out.close();
	}
}
