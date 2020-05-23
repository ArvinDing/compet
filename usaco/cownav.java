
import java.io.*;
import java.util.*;

public class cownav {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
		int square = Integer.parseInt(in.readLine());
		boolean[][] block = new boolean[square][square];
		for (int i = square - 1; i >= 0; i--) {
			String a = in.readLine();
			for (int k = 0; k < square; k++) {
				block[i][k] = a.charAt(k) == 'H';
			}
		}

		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		queue.add(new int[] { 0, square - 1, square - 1, 0 });
		queue.add(new int[] { 0, square - 1, square - 1, 1 });
		queue.add(new int[] { 0, square - 1, square - 1, 2 });
		queue.add(new int[] { 0, square - 1, square - 1, 3 });
		boolean[][][] done = new boolean[square][square][4];
		int[][][] save = new int[square][square][4];
		int[][] directions = new int[4][2];
		directions[0] = new int[] { 1, 0 };
		directions[1] = new int[] { 0, 1 };
		directions[2] = new int[] { -1, 0 };
		directions[3] = new int[] { 0, -1 };
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (done[curr[1]][curr[2]][curr[3]])
				continue;
			done[curr[1]][curr[2]][curr[3]] = true;
			save[curr[1]][curr[2]][curr[3]] = curr[0];

			int newY = curr[1] + directions[(curr[3] + 2) % 4][0];
			int newX = curr[2] + directions[(curr[3] + 2) % 4][1];

			if ((0 <= newY && 0 <= newX) && (newY < square && newX < square) && !block[newY][newX]
					&& !done[newY][newX][curr[3]]) {
				queue.add(new int[] { curr[0] + 1, newY, newX, curr[3] });
			}
			if (!done[curr[1]][curr[2]][((curr[3] + 1) % 4)])
				queue.add(new int[] { curr[0] + 1, curr[1], curr[2], (curr[3] + 1) % 4 });
			if (!done[curr[1]][curr[2]][(((curr[3] - 1) + 4) % 4)])
				queue.add(new int[] { curr[0] + 1, curr[1], curr[2], (((curr[3] - 1) + 4) % 4) });

		}
		queue.add(new int[] { 0, 0, 0, 0, 0, 0 });

		// 0-up,1-right, 2-down,3-left
		boolean[][][][][] done1 = new boolean[square][square][square][square][4];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int y = curr[1];
			int x = curr[2];
			int y1 = curr[3];
			int x1 = curr[4];
			int xyDirection = curr[5];
			if (done1[y][x][y1][x1][xyDirection])
				continue;
	//		System.out.println(y + " " + x + "| " + curr[0]);

			done1[y][x][y1][x1][xyDirection] = true;
			if (x == square - 1 && y == square - 1 && x1 == square - 1 && y1 == square - 1) {
				out.println(curr[0]);
				break;
			}
			if (x == square - 1 && y == square - 1) {
				queue.add(new int[] { curr[0] + save[y1][x1][xyDirection + 1], square - 1, square - 1, square - 1,
						square - 1, 0 });
				continue;
			} else if (x1 == square - 1 && y1 == square - 1) {
				queue.add(new int[] { curr[0] + save[y][x][xyDirection], square - 1, square - 1, square - 1, square - 1,
						0 });
				continue;
			}
			int newY = y + directions[xyDirection][0];
			int newX = x + directions[xyDirection][1];
			int newY1 = y1 + directions[(xyDirection + 1) % 4][0];
			int newX1 = x1 + directions[(xyDirection + 1) % 4][1];
			boolean fowardxy = (0 <= newY && 0 <= newX) && (newY < square && newX < square) && !block[newY][newX];
			boolean fowardxy1 = (0 <= newY1 && 0 <= newX1) && (newY1 < square && newX1 < square)
					&& !block[newY1][newX1];
			if (fowardxy && fowardxy1) {
				if (!done1[newY][newX][newY1][newX1][xyDirection])
					queue.add(new int[] { curr[0] + 1, newY, newX, newY1, newX1, xyDirection });
			} else if (fowardxy) {
				if (!done1[newY][newX][y1][x1][xyDirection])
					queue.add(new int[] { curr[0] + 1, newY, newX, y1, x1, xyDirection });
			} else if (fowardxy1) {
				if (!done1[y][x][newY1][newX1][xyDirection])
					queue.add(new int[] { curr[0] + 1, y, x, newY1, newX1, xyDirection });
			}
			if (!done1[y][x][y1][x1][(xyDirection + 1) % 4])
				queue.add(new int[] { curr[0] + 1, y, x, y1, x1, (xyDirection + 1) % 4 });
			if (!done1[y][x][y1][x1][((xyDirection - 1) + 4) % 4])
				queue.add(new int[] { curr[0] + 1, y, x, y1, x1, ((xyDirection - 1) + 4) % 4 });
		}
		in.close();
		out.close();
	}

}