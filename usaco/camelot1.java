/*
 ID: six.sal2
 PROB: camelot
 LANG: JAVA
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class camelot1 {
	private static int R, C;
	private static PrintWriter out;
	public static void main(String[] argv) throws IOException {
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("camelot.in"));
		out = new PrintWriter(new FileWriter("camelot.out"));
		String[] str = in.readLine().split("\\s");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		str = in.readLine().split("\\s");
		Position king = new Position(Integer.parseInt(str[1]) - 1, str[0].charAt(0) - 'A');
		// Add all the knights to the ArrayList;
		String strline = null;
		ArrayList<Position> knights = new ArrayList<Position>();
		while ((strline = in.readLine()) != null) {
			str = strline.split("\\s");
			for (int i = 0; i < str.length; i += 2) {
				knights.add(new Position(Integer.parseInt(str[i + 1]) - 1, str[i].charAt(0) - 'A'));
			}
		}
		in.close();
		buildNeighbors(R, C);
		calculateShortestKM();
		out.println(getShortestSteps(king, knights));
		out.close();
		System.out.println(System.currentTimeMillis() - time);
	}

	private static int getShortestSteps(Position king, ArrayList<Position> knights) {
		int krS = Math.max(0, king.r - 2), krE = Math.min(R, king.r + 2),
				kcS = Math.max(0, king.c - 2), kcE = Math.min(C, king.c + 2);
		int minSteps = Integer.MAX_VALUE;
		for (int i = 0; i < R; i ++) {
			dest:
			for (int j = 0; j < C; j ++) {
				int knightSteps = 0;
				for (Position pos : knights) {
					if (shortestKM[pos.r][pos.c][i][j] == Integer.MAX_VALUE) {
						continue dest;
					}
					knightSteps += shortestKM[pos.r][pos.c][i][j];
				}
				int kingExtra = Math.max(Math.abs(king.r - i), Math.abs(king.c - j));
				
				for (int l = krS; l < krE; l ++) {
					for (int m = kcS; m< kcE; m ++) {
						int kingMs = Math.max(Math.abs(king.r - l), Math.abs(king.c - m));
						int knMs = kingExtra;
						for (Position pos : knights) {
							if (shortestKM[pos.r][pos.c][l][m] == Integer.MAX_VALUE
									|| shortestKM[l][m][i][j] == Integer.MAX_VALUE) {
								continue;
							}
							int tmp = shortestKM[pos.r][pos.c][l][m] + shortestKM[l][m][i][j] - shortestKM[pos.r][pos.c][i][j];
							if (tmp < knMs) {
								knMs = tmp;
							}
						}
						int sumM = kingMs + knMs;
						if (sumM < kingExtra) {
							kingExtra = sumM;
							System.out.println("   --" + l + ", " + m + "---" + sumM);
						}
					}
				}
				int sum = kingExtra + knightSteps;
				if (sum < minSteps) {
					minSteps = sum;
					System.out.println("-T-" + i + ", " + j + "---" + sum);
				}
			}
		}
		return minSteps;
	}

	private static int[][][][] neighbors = new int[R][C][][];

	private static void buildNeighbors(int R, int C) {
		neighbors = new int[R][C][][];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ArrayList<int[]> list = new ArrayList<int[]>();
				for (int l = 0; l < dr.length; l++) {
					int r = i + dr[l];
					int c = j + dc[l];
					if (r >= 0 && r < R && c >= 0 && c < C) {
						list.add(new int[] { r, c });
					}
				}
				neighbors[i][j] = list.toArray(new int[0][]);
			}
		}
	}

	// Knight's move;
	private static int[] dr = { -2, -1, 1, 2, 2,  1, -1, -2 };
	private static int[] dc = {  1,  2, 2, 1, -1, -2, -2, -1 };
	private static int[][][][] shortestKM;

	private static void calculateShortestKM() {
		shortestKM = new int[R][C][R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < R; k++) {
					Arrays.fill(shortestKM[i][j][k], Integer.MAX_VALUE);
				}
				dijk(i, j);
			}
		}
	}

	private static void dijk(int i, int j) {
		int[][] dists = shortestKM[i][j];
		boolean[][] finishFlgs = new boolean[R][C];
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>();
		dists[i][j] = 0;
		queue.offer(new QueueNode(i, j, 0));
		while (queue.size() > 0) {
			QueueNode current = queue.poll();
			if (finishFlgs[current.r][current.c]) {
				continue;
			}
			finishFlgs[current.r][current.c] = true;
			for (int[] nei : neighbors[current.r][current.c]) {
				if (!finishFlgs[nei[0]][nei[1]]) {
					if (current.dist + 1 < dists[nei[0]][nei[1]]) {
						dists[nei[0]][nei[1]] = current.dist + 1;
						queue.offer(new QueueNode(nei[0], nei[1], dists[nei[0]][nei[1]]));
					}
				}
			}
		}
	}

	private static class QueueNode implements Comparable<QueueNode> {
		int dist, r, c;
		QueueNode(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(QueueNode o) {
			return dist - o.dist;
		}
	}
	
	private static class Position {
		public int r;
		public int c;

		public Position(int row, int colum) {
			this.r = row;
			this.c = colum;
		}

		public String toString() {
			return String.format("(%d, %d)", r, c);
		}
	}
}
