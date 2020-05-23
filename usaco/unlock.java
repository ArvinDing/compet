
import java.io.*;
import java.util.*;

public class unlock {
	private static int[][] boxes;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("unlock.in"));
		PrintWriter out = new PrintWriter(new File("unlock.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int[] thing = new int[3];
		thing[0] = Integer.parseInt(read.nextToken());
		thing[1] = Integer.parseInt(read.nextToken());
		thing[2] = Integer.parseInt(read.nextToken());
		boolean[][][] info = new boolean[3][11][11];
		boxes = new int[3][4];
		for (int i = 0; i < 3; i++) {
			for (int k = 2; k <= 3; k++) {
				boxes[i][k] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int a = 0; a < thing[i]; a++) {
				read = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(read.nextToken());
				int y = Integer.parseInt(read.nextToken());
				info[i][y + 1][x + 1] = true;
				boxes[i][0] = Math.max(boxes[i][0], x + 1);
				boxes[i][1] = Math.max(boxes[i][1], y + 1);
				boxes[i][2] = Math.min(boxes[i][2], x);
				boxes[i][3] = Math.min(boxes[i][3], y);
			}

		}

		int[][][][] added = new int[21][21][21][21];
		LinkedList<thing> queue = new LinkedList<thing>();

		queue.add(new thing(new int[] { 0, 0, 0, 0 }));
		added[10][10][10][10] = 0;
		reallyOuter: {
			while (!queue.isEmpty()) {
				thing curr = queue.poll();
				int[] temp = new int[4];
				temp[0] = curr.x;
				temp[1] = curr.y;
				temp[2] = curr.x1;
				temp[3] = curr.y1;
				int moves = added[curr.x + 10][curr.y + 10][curr.x1 + 10][curr.y1 + 10];

				if (!check(temp, info))
					continue;
				// System.out.println(temp[0] + " " + temp[1] + " " + temp[2] +
				// " " + temp[3] + " " + moves);
				if (!inBounding(new int[] { 0, 0, temp[0], temp[1] }, 0, 1) && !inBounding(temp, 1, 2)
						&& !inBounding(new int[] { 0, 0, temp[2], temp[3] }, 0, 2)) {
					out.println(moves);
					break reallyOuter;
				}
				if (Math.abs(temp[0]) == 10 || Math.abs(temp[1]) == 10 || Math.abs(temp[2]) == 10
						|| Math.abs(temp[3]) == 10)
					continue;
				
				for (int i = -1; i <= 1; i += 2) {
					for (int k = 0; k < 4; k++) {
						temp[k] += i;
						if (added[temp[0] + 10][temp[1] + 10][temp[2] + 10][temp[3] + 10] == 0
								&& !(temp[0] == 0 && temp[1] == 0 && temp[2] == 0 && temp[3] == 0)) {
							queue.add(new thing(temp));
							added[temp[0] + 10][temp[1] + 10][temp[2] + 10][temp[3] + 10] = moves + 1;
						}
						temp[k] -= i;
					}
				}
				for (int i = -1; i <= 1; i += 2) {
					for (int k = 0; k < 2; k++) {
						temp[k] += i;
						temp[k + 2] += i;
						if (added[temp[0] + 10][temp[1] + 10][temp[2] + 10][temp[3] + 10] == 0
								&& !(temp[0] == 0 && temp[1] == 0 && temp[2] == 0 && temp[3] == 0)) {
							queue.add(new thing(temp));
							added[temp[0] + 10][temp[1] + 10][temp[2] + 10][temp[3] + 10] = moves + 1;
						}
						temp[k] -= i;
						temp[k + 2] -= i;
					}
				}
			}

			out.println(-1);
		}
		in.close();
		out.close();
	}

	private static boolean inBounding(int[] changes, int i, int j) {
		// fix this part
		// maxX,maxY,minX,minY
		// x,y,x1,y1
		int iMinX = boxes[i][2] + changes[0];
		int iMaxX = boxes[i][0] + changes[0];
		int iMinY = boxes[i][3] + changes[1];
		int iMaxY = boxes[i][1] + changes[1];
		int jMinX = boxes[j][2] + changes[2];
		int jMaxX = boxes[j][0] + changes[2];
		int jMinY = boxes[j][3] + changes[3];
		int jMaxY = boxes[j][1] + changes[3];
		if ((jMinX < iMinX && iMinX < jMaxX) || (jMinX < iMaxX && iMaxX < jMaxX) || (jMinX <= iMinX && iMaxX <= jMaxX)
				|| (iMinX <= jMinX && jMaxX <= iMaxX)) {
			if ((jMinY < iMinY && iMinY < jMaxY) || (jMinY < iMaxY && iMaxY < jMaxY)
					|| (jMinY <= iMinY && iMaxY <= jMaxY) || (iMinY <= jMinY && jMaxY <= iMaxY)) {
				return true;
			}
		}
		return false;
	}

	private static boolean check(int[] temp, boolean[][][] info) {
		boolean[][] test = new boolean[40][40];
		for (int i = 0; i < 3; i++) {
			for (int y = -1; y < 10; y++) {
				for (int x = -1; x < 10; x++) {
					if (info[i][y + 1][x + 1]) {
						if (i == 0) {
							test[y + 20][x + 20] = true;
						} else if (i == 1) {
							if (test[y + 20 + temp[1]][x + 20 + temp[0]])
								return false;
							test[y + 20 + temp[1]][x + 20 + temp[0]] = true;
						} else if (i == 2) {
							if (test[y + 20 + temp[3]][x + 20 + temp[2]])
								return false;
							test[y + 20 + temp[3]][x + 20 + temp[2]] = true;
						}
					}
				}
			}

		}
		return true;
	}

	private static class thing {
		int x;
		int y;
		int x1;
		int y1;

		private thing(int[] assign) {
			this.x = assign[0];
			this.y = assign[1];
			this.x1 = assign[2];
			this.y1 = assign[3];

		}

	}
}