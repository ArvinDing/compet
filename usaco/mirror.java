
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class mirror {
	private static boolean[][] info;
	private static int[][] directionMap = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static int maxHeight;
	private static int maxWidth;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mirror.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mirror.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		maxHeight = Integer.parseInt(read.nextToken());
		maxWidth = Integer.parseInt(read.nextToken());
		info = new boolean[maxHeight][maxWidth];
		for (int i = maxHeight-1; i >=0; i--) {
			String curr = in.readLine();
			for (int k = 0; k < maxWidth; k++) {
				info[i][k] = (curr.charAt(k) == '/');
			}
		}
		int max = 0;
		for (int i = 0; i < maxWidth; i++) {
			max = Math.max(solve(-1, i, 0), max);		
			max = Math.max(solve(maxHeight, i, 2), max);
		}
		for (int i = 0; i < maxHeight; i++) {
			max = Math.max(solve(i, -1, 1), max);
			max = Math.max(solve(i, maxWidth, 3), max);
		}
		out.println(max);
		in.close();
		out.close();
	}

	private static int solve(int height, int width, int direction) {
		int mirrors = 0;
		while (true) {
			height += directionMap[direction][0];
			width += directionMap[direction][1];
			if (height < 0 || height > maxHeight - 1 || width < 0 || width > maxWidth - 1) {
				return mirrors;
			}

			if (info[height][width]) {
				switch (direction) {
				case 0:
					direction = 1;
					break;
				case 3:
					direction = 2;
					break;
				case 1:
					direction = 0;
					break;
				case 2:
					direction = 3;
					break;
				}

			} else {
				switch (direction) {
				case 0:
					direction = 3;
					break;
				case 3:
					direction = 0;
					break;
				case 1:
					direction = 2;
					break;
				case 2:
					direction = 1;
					break;
				}

			}

			mirrors++;
		}

	}
}