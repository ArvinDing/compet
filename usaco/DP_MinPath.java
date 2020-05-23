
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_MinPath {
	public static int minCost(int[][] costs) {
		// TODO: implement this
		PriorityQueue<position> queue = new PriorityQueue<position>();
		int[][] dp = new int[costs.length][costs[0].length];
		queue.add(new position(0, 0, costs[0][0]));
		boolean[][] done = new boolean[costs.length][costs[0].length];
		while (true) {
			position a = queue.poll();
			int x = a.x;
			int y = a.y;
			int cost = a.cost;
			if (done[x][y])
				continue;
			done[x][y] = true;
			if (x == dp.length - 1 && y == dp[0].length - 1) {
				return cost;
			}

			if (x < dp.length - 1) {
				queue.add(new position(x + 1, y, cost + costs[x + 1][y]));

			}
			if (y < dp[0].length - 1) {
				queue.add(new position(x, y + 1, cost + costs[x][y + 1]));

			}
			if (x < dp.length - 1 && y < dp[0].length - 1) {
				queue.add(new position(x + 1, y + 1, cost + costs[x + 1][y + 1]));
			}
		}
	}

	public static class position implements Comparable<position> {
		int x;
		int y;
		int cost;

		public position(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(position o) {
			return cost - o.cost;
		}

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {

			int n = Integer.parseInt(in.readLine());
			int[][] costs = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] row = in.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					costs[i][j] = Integer.parseInt(row[j]);
				}
			}
			System.out.println(minCost(costs));
		}
	}
}