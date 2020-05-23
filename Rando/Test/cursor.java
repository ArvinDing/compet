package Test;

import java.io.*;
import java.util.*;

public class cursor {
	static int[] states = new int[1 << 26];
	static int[][] info = new int[5][5];
	static int herman = 0;
	static int alex = 0;

	public static void main(String[] args) throws InterruptedException {
		recursion(0, 0);
	}

	private static void recursion(int r, int c) {
		if (r == 5) {
			eval();
		}
		for (int i = 0; i < 2; i++) {
			info[r][c] = i;
			if (c == 4) {
				recursion(r + 1, 0);
			} else {
				recursion(r, c + 1);
			}
		}
	}

	private static void eval() {
		boolean[][] done = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (!done[i][k]) {
					LinkedList<int[]> queue = new LinkedList<int[]>();
					queue.add(new int[] { i, k });
					done[i][k] = true;
					while (!queue.isEmpty()) {

					}
				}
			}
		}
	}
}
