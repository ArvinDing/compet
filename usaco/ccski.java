
import java.io.*;
import java.util.*;

public class ccski {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter out = new PrintWriter(new File("ccski.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int y = Integer.parseInt(read.nextToken());
		int x = Integer.parseInt(read.nextToken());
		int[][] info = new int[y][x];
		for (int i = 0; i < y; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < x; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		boolean[][] waypoints = new boolean[y][x];
		for (int i = 0; i < y; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < x; k++) {
				waypoints[i][k] = (Integer.parseInt(read.nextToken()) == 1);
			}
		}
		long[][] cost = new long[y][x];
		boolean[][] done = new boolean[y][x];
		outer: for (int i = 0; i < y; i++) {
			for (int k = 0; k < x; k++) {
				if (waypoints[i][k]) {
					PriorityQueue<thing> queue = new PriorityQueue<thing>();
					queue.add(new thing(i, k, 0));
					while (!queue.isEmpty()) {
						thing curr = queue.poll();
						int currY = curr.y;
						int currX = curr.x;
						if (done[currY][currX])
							continue;
						done[currY][currX] = true;
						cost[currY][currX] = curr.cost;
						if (currY - 1 >= 0 && !done[currY - 1][currX]) {
							queue.add(new thing(currY - 1, currX,
									Math.max(curr.cost, Math.abs(info[currY - 1][currX] - info[currY][currX]))));
						}
						if (currX - 1 >= 0 && !done[currY][currX - 1]) {
							queue.add(new thing(currY, currX - 1,
									Math.max(curr.cost, Math.abs(info[currY][currX - 1] - info[currY][currX]))));
						}
						if (currY + 1 < y && !done[currY + 1][currX]) {
							queue.add(new thing(currY + 1, currX,
									Math.max(curr.cost, Math.abs(info[currY + 1][currX] - info[currY][currX]))));
						}
						if (currX + 1 < x && !done[currY][currX + 1]) {
							queue.add(new thing(currY, currX + 1,
									Math.max(curr.cost, Math.abs(info[currY][currX + 1] - info[currY][currX]))));
						}
					}
					break outer;
				}
			}
		}
		long max = Long.MIN_VALUE;
		for (int i = 0; i < y; i++) {
			for (int k = 0; k < x; k++) {
				if (waypoints[i][k]) {
					max = Math.max(max, cost[i][k]);
				}
			}
		}

		out.println(max);
		out.close();
		in.close();

	}

	private static class thing implements Comparable<thing> {
		int cost;
		int y;
		int x;

		private thing(int y, int x, int cost) {
			this.cost = cost;
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(thing o) {
			return cost - o.cost;
		}

	}
}
