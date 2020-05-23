
import java.io.*;
import java.util.*;

public class gravity {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gravity.in"));
		PrintWriter out = new PrintWriter(new FileWriter("gravity.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		int startY = 0;
		int startX = 0;
		int endY = 0;
		int endX = 0;
		boolean[][] info = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int k = 0; k < m; k++) {
				if (line.charAt(k) == 'C') {
					startY = i;
					startX = k;
				} else if (line.charAt(k) == 'D') {
					endY = i;
					endX = k;
				} else {
					info[i][k] = line.charAt(k) == '#';
				}
			}
		}

		boolean[][][] done = new boolean[n][m][2];
		int[][] added = new int[n][m];
		PriorityQueue<thing> queue = new PriorityQueue<thing>();
		queue.add(new thing(0, startY, startX, 0));
		outer: {
			while (!queue.isEmpty()) {
				thing curr = queue.poll();
				int x = curr.x;
				int y = curr.y;
				int cost = curr.cost;
				int gravity = curr.gravity;

				if (gravity == 0) {
					while (y < n - 1) {
						if (!info[y + 1][x]) {
							if (endY == y && endX == x) {
								out.println(cost);
								break outer;
							}
							y++;
						} else
							break;
					}
					if (endY == y && endX == x) {
						out.println(cost);
						break outer;
					}
					if (y == n - 1 || (y == n - 2 && !info[y + 1][x]))
						continue;
				} else {
					while (y >= 1) {
						if (!info[y - 1][x]) {
							if (endY == y && endX == x) {
								out.println(cost);
								break outer;
							}
							y--;
						} else
							break;
					}
					if (endY == y && endX == x) {
						out.println(cost);
						break outer;
					}
					if (y == 0 || (y == 1 && !info[0][x]))
						continue;
				}
				if (endY == y && endX == x) {
					out.println(cost);
					break outer;
				}
				if (done[y][x][gravity])
					continue;
				done[y][x][gravity] = true;
				// System.out.println(y + " " + x + " " + gravity + " " + cost);
				if (gravity == 0 && !done[y][x][1])
					queue.add(new thing(cost + 1, y, x, 1));
				else if (gravity == 1 && !done[y][x][0])
					queue.add(new thing(cost + 1, y, x, 0));

				if (x - 1 >= 0 && !done[y][x - 1][gravity] && !info[y][x - 1]) {
					if (added[y][x - 1] != 0) {
						if (added[y][x - 1] > cost) {
							queue.remove(new thing(added[y][x - 1], y, x - 1, gravity));
							queue.add(new thing(cost, y, x - 1, gravity));
							added[y][x - 1] = cost;
						}
					} else {
						queue.add(new thing(cost, y, x - 1, gravity));
						added[y][x - 1] = cost;
					}
				}

				if (x + 1 < m && !done[y][x + 1][gravity] && !info[y][x + 1]) {
					if (added[y][x + 1] != 0) {
						if (added[y][x + 1] > cost) {
							queue.remove(new thing(added[y][x + 1], y, x + 1, gravity));
							queue.add(new thing(cost, y, x + 1, gravity));
							added[y][x + 1] = cost;
						}
					} else {
						queue.add(new thing(cost, y, x + 1, gravity));
						added[y][x + 1] = cost;
					}
				}
			}
			out.println(-1);
		}
		in.close();
		out.close();
	}

	private static class thing implements Comparable<thing> {
		int x;
		int y;
		int cost;
		int gravity;

		private thing(int cost, int y, int x, int gravity) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.gravity = gravity;
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