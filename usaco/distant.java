
import java.io.*;
import java.util.*;

public class distant {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("distant.in"));
		PrintWriter out = new PrintWriter(new FileWriter("distant.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int a = Integer.parseInt(read.nextToken());
		int b = Integer.parseInt(read.nextToken());
		boolean[][] info = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int k = 0; k < n; k++) {
				info[i][k] = line.charAt(k) == '(';
			}
		}
		int max = 0;
		for (int startY = 0; startY < n; startY++) {
			for (int startX = 0; startX < n; startX++) {
				boolean[][] done = new boolean[n][n];
				int[][] added = new int[n][n];
				PriorityQueue<thing> queue = new PriorityQueue<thing>();
				queue.add(new thing(0, startY, startX));
				while (!queue.isEmpty()) {
					thing curr = queue.poll();
					int x = curr.x;
					int y = curr.y;
					int cost = curr.cost;
					done[y][x] = true;
					if (x - 1 >= 0 && !done[y][x - 1]) {
						int add = info[y][x] == info[y][x - 1] ? a : b;
						if (added[y][x - 1] != 0) {
							if (added[y][x - 1] > cost + add) {
								queue.remove(new thing(added[y][x - 1], y, x - 1));
								queue.add(new thing(cost + add, y, x - 1));
								added[y][x - 1] = cost + add;
							}
						} else {
							queue.add(new thing(cost + add, y, x - 1));
							added[y][x - 1] = cost + add;
						}
					}
					if (y - 1 >= 0 && !done[y - 1][x]) {
						int add = info[y][x] == info[y - 1][x] ? a : b;
						if (added[y - 1][x] != 0) {
							if (added[y - 1][x] > cost + add) {
								queue.remove(new thing(added[y - 1][x], y - 1, x));
								queue.add(new thing(cost + add, y - 1, x));
								added[y - 1][x] = cost + add;
							}
						} else {
							queue.add(new thing(cost + add, y - 1, x));
							added[y - 1][x] = cost + add;
						}
					}
					if (x + 1 < n && !done[y][x + 1]) {
						int add = info[y][x] == info[y][x + 1] ? a : b;
						if (added[y][x + 1] != 0) {
							if (added[y][x + 1] > cost + add) {
								queue.remove(new thing(added[y][x + 1], y, x + 1));
								queue.add(new thing(cost + add, y, x + 1));
								added[y][x + 1] = cost + add;
							}
						} else {
							queue.add(new thing(cost + add, y, x + 1));
							added[y][x + 1] = cost + add;
						}
					}
					if (y + 1 < n && !done[y + 1][x]) {
						int add = info[y][x] == info[y + 1][x] ? a : b;
						if (added[y + 1][x] != 0) {
							if (added[y + 1][x] > cost + add) {
								queue.remove(new thing(added[y + 1][x], y + 1, x));
								queue.add(new thing(cost + add, y + 1, x));
								added[y + 1][x] = cost + add;
							}
						} else {
							queue.add(new thing(cost + add, y + 1, x));
							added[y + 1][x] = cost + add;
						}
					}
					max = Math.max(cost, max);
				}
			}
		}
		out.println(max);
		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		int x;
		int y;
		int cost;

		private thing(int cost, int y, int x) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(thing o) {
			return cost - o.cost;
		}

		@Override
		public boolean equals(Object obj) {
			thing a = (thing) obj;
			return x == a.x && y == a.y;
		}
	}
}