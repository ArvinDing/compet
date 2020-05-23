
/*
ID: dingarv1
LANG: JAVA
TASK: camelot
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class camelot {
	private static int xsize;
	private static int ysize;
	private static List<position> knights;
	private static position King;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new File("camelot.out"));
		StringTokenizer a = new StringTokenizer(in.readLine());
		knights = new ArrayList<position>();
		xsize = Integer.parseInt(a.nextToken());
		ysize = Integer.parseInt(a.nextToken());

		StringTokenizer a1 = new StringTokenizer(in.readLine());
		String skip = a1.nextToken();
		King = new position(Integer.parseInt(a1.nextToken()) - 1, ((int) StoC(skip)) - 65, 0);
		String d = in.readLine();
		if (d != null) {
			StringTokenizer c = new StringTokenizer(d);

			while (true) {
				while (c.hasMoreTokens()) {
					String next = c.nextToken();
					knights.add(new position(Integer.parseInt(c.nextToken()) - 1, ((int) StoC(next)) - 65, 0));

				}
				d = in.readLine();
				if (d != null) {
					c = new StringTokenizer(d);
				} else {
					break;
				}
			}
		}
		int solution = solve();
		if (solution == Integer.MAX_VALUE) {
			out.println(0);
		} else {
			out.println(solution);
		}
		in.close();
		out.close();
	}

	private static int solve() {

		int[][][][] info = new int[xsize][ysize][xsize][ysize];

		for (int x = 0; x < xsize; x++) {
			for (int y = 0; y < ysize; y++) {
				info[x][y] = function(x, y);
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < xsize; i++) {
			for (int k = 0; k < ysize; k++) {
				for (int o = -2; o <= 2; o++) {
					for (int l = -2; l <= 2; l++) {

						if ((King.x + o < 0 || King.x + o > xsize - 1 || King.y + l < 0 || King.y + l > ysize - 1)) {
							continue;
						}
						int ternary = (Math.abs(o) > Math.abs(l)) ? Math.abs(o) : Math.abs(l);

						int current = 0;
						for (int w = 0; w < knights.size(); w++) {
							position knight = knights.get(w);

							int x = knight.x;
							int y = knight.y;

							current += info[x][y][i][k];
						}
						if (current == 0&&xsize<3&&ysize<3) {
							if (knights.size() != 0) {
								return (Math.abs(knights.get(0).x - King.x) > Math.abs(knights.get(0).y - King.y))
										? Math.abs(knights.get(0).x - King.x) : Math.abs(knights.get(0).y - King.y);
							}
						}

						int old = current;
						for (int q = 0; q < knights.size(); q++) {
							current = old;
							position knight = knights.get(q);
							int x = knight.x;
							int y = knight.y;
							if (info[x][y][King.x + o][King.y + l] == 0 && !(x == King.x + o && y == King.y + l)
									|| (info[x][y][i][k] == 0 && !(x == King.x && y == King.y))) {
								continue;
							}

							current -= info[x][y][i][k];
							current += info[x][y][King.x + o][King.y + l] + info[King.x + o][King.y + l][i][k]
									+ ternary;
							if (current < min) {
								min = current;
							}
						}
					}
				}
			}
		}

		return min;

	}

	public static int[][] function(int startx, int starty) {
		int[][] info = new int[xsize][ysize];

		PriorityQueue<position> queue = new PriorityQueue<position>();
		int[] xchange = new int[] { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] ychange = new int[] { 1, -1, 2, -2, 2, -2, 1, -1 };
		int[][] distances = new int[31][27];

		queue.add(new position(startx, starty, 0));
		while (!queue.isEmpty()) {
			position a = queue.poll();
			int o = a.x;
			int p = a.y;
			int distance = a.distance;

			info[o][p] = distance;

			for (int i = 0; i < 8; i++) {
				o += xchange[i];
				p += ychange[i];
				if (!(o < 0 || o > xsize - 1 || p < 0 || p > ysize - 1)) {
					if (distances[o][p] == 0) {
						queue.add(new position(o, p, distance + 1));
						distances[o][p] = distance + 1;

					}
					if (distances[o][p] > distance + 1) {
						queue.remove(new position(o, p, distances[o][p]));
						queue.add(new position(o, p, distance + 1));
						distances[o][p] = distance + 1;

					}
				}
				o = a.x;
				p = a.y;
			}
		}
		info[startx][starty] = 0;

		return info;
	}

	public static char StoC(String a) {
		return a.charAt(0);

	}

	public static void printArry(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				System.out.print(a[i][k] + " ");
			}
			System.out.println();
		}
	}

	public static class position implements Comparable {
		int x;
		int y;
		int distance;

		public position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public boolean equals(Object p) {
			return this.x == ((position) p).x && this.y == ((position) p).y;
		}

		public int compareTo(Object o) {

			return distance - ((position) o).distance;
		}
	}
}
