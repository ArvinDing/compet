
/*
ID: dingarv1
LANG: JAVA
TASK: starry
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class starry {
	private static boolean[][] info;
	private static boolean[][] copy;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("starry.in"));
		PrintWriter out = new PrintWriter(new File("starry.out"));
		int W = Integer.parseInt(in.readLine());
		int H = Integer.parseInt(in.readLine());
		info = new boolean[H][W];
		copy = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			String temp = in.readLine();
			for (int k = 0; k < W; k++) {

				info[i][k] = temp.charAt(k) == '1';
				copy[i][k] = temp.charAt(k) == '1';
			}
		}

		List<shapes> figures = new ArrayList<shapes>();
		for (int i = 0; i < H; i++) {
			for (int k = 0; k < W; k++) {
				if (copy[i][k] == true) {
					figures.add(floodfill(i, k));
				}
			}
		}
		ArrayList<List<shapes>> answer = new ArrayList<List<shapes>>();
		while (!figures.isEmpty()) {
			List<shapes> answer1 = new ArrayList<shapes>();
			shapes curr = figures.get(0);
			figures.remove(0);
			answer1.add(curr);
			boolean[][] figure = curr.figure;
			int h = curr.h;
			int w = curr.w;
			List<boolean[][]> all = create8(new shapes(figure, h, w));

			abc: for (Iterator<shapes> it = figures.iterator(); it.hasNext();) {
				shapes temp = it.next();
				for (boolean[][] a : all) {
					if (temp.figure.length == a.length && temp.figure[0].length == a[0].length) {
						if (isSame(a, temp.figure)) {
							answer1.add(temp);
							it.remove();
							continue abc;
						}
					}
				}
			}
			answer.add(answer1);

		}

		char[][] print = new char[H][W];
		for (int i = 0; i < H; i++) {
			for (int k = 0; k < W; k++) {
				print[i][k] = '0';
			}
		}
		for (int i = 0; i < answer.size(); i++) {
			List<shapes> right = answer.get(i);
			char c = (char) (i + 97);
			for (shapes b : right) {
				boolean[][] figure = b.figure;
				int h = b.h;
				int w = b.w;
				for (int z = 0; z < figure.length; z++) {
					for (int k = 0; k < figure[0].length; k++) {
						if (figure[z][k] == true) {
							print[h + z][w + k] = c;
						}
					}
				}
			}
		}
		for (int i = 0; i < print.length; i++) {
			for (int k = 0; k < print[0].length; k++) {

				out.print(print[i][k]);

			}
			out.println();
		}

		in.close();
		out.close();
	}

	private static shapes floodfill(int h, int w) {
		List<Point> answer = new ArrayList<Point>();
		LinkedList<Point> queue = new LinkedList<Point>();
		int minW = Integer.MAX_VALUE;
		int minH = Integer.MAX_VALUE;
		int maxW = Integer.MIN_VALUE;
		int maxH = Integer.MIN_VALUE;

		queue.add(new Point(h, w));
		while (!queue.isEmpty()) {
			Point a = queue.poll();
			int wCoord = a.w;
			int hCoord = a.h;

			minW = Math.min(minW, wCoord);
			minH = Math.min(minH, hCoord);
			maxW = Math.max(maxW, wCoord);
			maxH = Math.max(maxH, hCoord);

			answer.add(a);
			if (!copy[hCoord][wCoord])
				continue;

			copy[hCoord][wCoord] = false;
			for (int i = -1; i <= 1; i++) {
				for (int k = -1; k <= 1; k++) {
					if (i == 0 && k == 0) {
						continue;
					}
					if (hCoord + i == -1 || hCoord + i == info.length || wCoord + k == -1
							|| wCoord + k == info[0].length) {
						continue;
					}
					if (copy[hCoord + i][wCoord + k]) {
						queue.add(new Point(hCoord + i, wCoord + k));
					}

				}
			}
		}
		boolean[][] real = new boolean[(maxH - minH) + 1][(maxW - minW) + 1];
		for (Point a : answer) {
			real[a.h - minH][a.w - minW] = true;
		}
		shapes name = new shapes(real, minH, minW);
		return name;

	}

	private static List<boolean[][]> create8(shapes copy) {
		boolean[][] old = copy.figure;
		int height = copy.h;
		int width = copy.w;
		List<boolean[][]> answer = new ArrayList<boolean[][]>();
		boolean[][] temp = new boolean[width][height];
		answer.add(old);// 1

		temp = rotate90(old);
		answer.add(temp);// 2
		temp = rotate90(temp);
		answer.add(temp);// 3
		temp = rotate90(temp);
		answer.add(temp);// 4
		temp = rotate90(temp);

		temp = reflect(temp);
		answer.add(temp);// 5

		temp = rotate90(temp);
		answer.add(temp);// 6
		temp = rotate90(temp);
		answer.add(temp);// 7
		temp = rotate90(temp);
		answer.add(temp);// 8
		return answer;

	}

	private static class shapes {
		public boolean[][] figure;
		public int h;
		public int w;

		public shapes(boolean[][] figure, int h, int w) {
			this.figure = figure;
			this.h = h;
			this.w = w;
		}

	}

	public static boolean[][] rotate90(boolean[][] a) {
		// (y, -x)
		boolean[][] fort = new boolean[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				fort[k][-1 * i + a.length - 1] = a[i][k];
			}
		}

		return fort;
	}

	public static boolean[][] reflect(boolean[][] a) {
		boolean[][] fort = new boolean[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				fort[i][a[0].length - k - 1] = a[i][k];
			}
		}
		return fort;
	}

	public static boolean isSame(boolean[][] a, boolean[][] b) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				if (a[i][k] != b[i][k]) {
					return false;
				}
			}
		}
		return true;
	}

	private static class Point {
		public int h;
		public int w;

		public Point(int h, int w) {
			this.h = h;
			this.w = w;
		}

	}
}
