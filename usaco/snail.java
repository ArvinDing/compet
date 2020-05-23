
/*
ID: dingarv1
LANG: JAVA
TASK: snail
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class snail {
	private static boolean[][] info;
	private static boolean[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("snail.in"));
		PrintWriter out = new PrintWriter(new File("snail.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int square = Integer.parseInt(read.nextToken());
		info = new boolean[square][square];
		int temp = Integer.parseInt(read.nextToken());
		for (int i = 0; i < temp; i++) {
			String now = in.readLine();
			info[Integer.parseInt(now.substring(1, now.length())) - 1][now.charAt(0) - 65] = true;
		}
		Stack<Point> stack = new Stack<Point>();
		grid = new boolean[square][square];
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				grid[i][k] = info[i][k];
			}
		}
		ArrayList<int[]> list = new ArrayList<int[]>();
		stack.add(new Point(0, 0, 0, 0, 0, list));
		int answer = 0;
		while (!stack.isEmpty()) {
			Point a = stack.pop();
			int x = a.x;
			int y = a.y;
			int length = a.length;
			int directionX = a.directionX;
			int directionY = a.directionY;
			ArrayList<int[]> moves = a.moves;
			boolean flag = true;
			while ((directionX != 0 || directionY != 0) && isBlank(x + directionX, y + directionY)) {
				length++;
				grid[x][y] = true;
				moves.add(new int[] { x, y });
				x += directionX;
				y += directionY;
			}
			if (!(isDone(x + directionX, y + directionY))) {
				for (int i = -1; i <= 1; i++) {
					for (int k = -1; k <= 1; k++) {
						if (i == 0 && k == 0) {
							continue;
						}
						if (i == 0 || k == 0) {
							if (isBlank(x + i, y + k)) {
								ArrayList<int[]> copy = new ArrayList<int[]>(moves);
								Point point = new Point(x, y, i, k, length, copy);
								stack.add(point);
								flag = false;
							}
						}
					}
				}
			}
			if (flag) {
				grid[x][y] = true;
				moves.add(new int[] { x, y });
				answer = Math.max(answer, length + 1);
				if (stack.size() >= 1) {
					int len = stack.peek().length + 1;
					for (int i = len; i < moves.size(); i++) {
						grid[moves.get(i)[0]][moves.get(i)[1]] = false;
					}

				}

			}
		}
		out.println(answer);
		out.close();
		in.close();

	}

	private static boolean isBlank(int x, int y) {
		if (x >= 0 && y >= 0 && x < info.length && y < info.length) {
			if (!grid[x][y]) {
				return true;
			}
		}
		return false;

	}

	private static boolean isDone(int x, int y) {
		if (x >= 0 && y >= 0 && x < info.length && y < info.length) {
			if (grid[x][y] && !info[x][y]) {
				return true;
			}
		}
		return false;

	}

	private static class Point {
		int x;
		int y;
		int directionX;
		int directionY;
		int length;
		ArrayList<int[]> moves;

		public Point(int x, int y, int directionX, int directionY, int length, ArrayList<int[]> moves) {
			this.x = x;
			this.y = y;
			this.directionX = directionX;
			this.directionY = directionY;
			this.length = length;
			this.moves = moves;

		}

	}
}