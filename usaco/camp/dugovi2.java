
package camp;

import java.io.*;
import java.util.*;

public class dugovi2 {
	private static int[][] neighbor;
	private static boolean[] done;
	private static int[] children;
	private static int[] value;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		children = new int[n];
		done = new boolean[n];
		neighbor = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int parent = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			neighbor[i] = new int[] { parent, cost };
			children[parent]++;
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (children[i] == 0 && !done[i]) {
				sum += neighbor[i][1];
				payNoChild(i, neighbor[i][1]);
			}
		}
		for (int i = 0; i < n; i++) {
			if (done[i])
				continue;
			int[] curr = new int[] { i, 0 };
			int min = Integer.MAX_VALUE;
			int total = 0;
			boolean first = true;
			while (first || curr[0] != i) {
				first = false;
				int index = curr[0];
				done[index] = true;
				int previousVal = curr[1];
				total += previousVal;
				min = Math.min(min, neighbor[index][1] - previousVal);
				curr = new int[] { neighbor[index][0], neighbor[index][1] };
			}
			min = Math.min(min, neighbor[curr[0]][1] - curr[1]);
			sum += total + min;
		}
		System.out.println(sum);
		in.close();
	}

	private static void payNoChild(int index, int money) {
		if (neighbor[index][1] - money <= 0) {
			done[index] = true;
			if (!done[neighbor[index][0]])
				payNoChild(neighbor[index][0], Math.min(money, neighbor[index][1]));
			children[neighbor[index][0]]--;
			done[index] = true;
		} else {
			neighbor[index][1] -= money;
		}

	}
}
