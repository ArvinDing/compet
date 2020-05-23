import java.io.*;
import java.util.*;

public class cownavR {
	private static boolean[][] info;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new File("cownav.out"));
		n = Integer.parseInt(in.readLine());
		info = new boolean[n][n];
		for (int i = n-1;i>=0; i--) {
			char[] read = in.readLine().toCharArray();
			for (int k = 0; k < n; k++) {
				if (read[k] == 'H')
					info[i][k] = true;
			}
		}
		int[][] directions = new int[4][2];
		directions[0] = new int[] { 1, 0 };
		directions[1] = new int[] { 0, -1 };
		directions[2] = new int[] { -1, 0 };
		directions[3] = new int[] { 0, 1 };
		LinkedList<int[]> queue = new LinkedList<int[]>();
		boolean[][][][][] done = new boolean[n][n][n][n][4];
		queue.add(new int[] { 0, 0, 0, 0, 0, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int y = curr[0];
			int x = curr[1];
			int otherY = curr[2];
			int otherX = curr[3];
			int direction = curr[4];
			int cost = curr[5];
			if (y == n - 1 && x == n - 1 && otherY == n - 1 && otherX == n - 1) {
				out.println(cost);
				break;
			}
			int otherDirection = (4 + (direction - 1)) % 4;
			if (done[y][x][otherY][otherX][direction])
				continue;
			done[y][x][otherY][otherX][direction] = true;
			if (!done[y][x][otherY][otherX][(4 + (direction + 1)) % 4])
				queue.add(new int[] { y, x, otherY, otherX, (4 + (direction + 1)) % 4, cost + 1 });
			if (!done[y][x][otherY][otherX][(4 + (direction - 1)) % 4])
				queue.add(new int[] { y, x, otherY, otherX, (4 + (direction - 1)) % 4, cost + 1 });

			int changeY = y + directions[direction][0];
			int changeX = x + directions[direction][1];
			int changeOY = otherY + directions[otherDirection][0];
			int changeOX = otherX + directions[otherDirection][1];
			if (y == n - 1 && x == n - 1) {
				changeY = n - 1;
				changeX = n - 1;
			} else {
				changeY = modify(y, changeY, changeX, 0);
				changeX = modify(x, changeY, changeX, 1);
			}
			if (otherY == n - 1 && otherX == n - 1) {
				changeOY = n - 1;
				changeOX = n - 1;
			} else {
				changeOY = modify(otherY, changeOY, changeOX, 0);
				changeOX = modify(otherX, changeOY, changeOX, 1);
			}
			if (!done[changeY][changeX][changeOY][changeOX][direction])
				queue.add(new int[] { changeY, changeX, changeOY, changeOX, direction, cost + 1 });
		}
		out.close();
		in.close();
	}

	private static int modify(int original, int changeY, int changeX, int state) {
		if (changeY == -1 || changeY == n)
			return original;
		if (changeX == -1 || changeX == n)
			return original;
		if (info[changeY][changeX])
			return original;
		if (state == 0)
			return changeY;
		return changeX;
	}
}
