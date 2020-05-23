package camp;

import java.io.*;
import java.util.*;

public class dostava {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(read.nextToken());
		int c = Integer.parseInt(read.nextToken());
		int[][] info = new int[r][c];
		for (int i = 0; i < r; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < c; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		int[][] prefix = new int[r][c];
		int[] total = new int[r];
		for (int i = 0; i < r; i++) {
			int sum = 0;
			for (int k = 0; k < c; k++) {
				sum += info[i][k];
				prefix[i][k] = sum;
			}
			total[i] = sum;
		}
		int d = Integer.parseInt(in.readLine());
		int previousX = 0;
		int previousY = 0;
		LinkedList<int[]>[] neigh = new LinkedList[2 * r];
		for (int i = 0; i < r; i++) {
			if (neigh[i] == null)
				neigh[i] = new LinkedList<int[]>();
			if (neigh[i + r] == null)
				neigh[i + r] = new LinkedList<int[]>();
			neigh[i].add(new int[] { i + r, total[i] });
			neigh[i + r].add(new int[] { i, total[i] });
			if (i != r - 1) {
				neigh[i].add(new int[] { i + 1, info[i + 1][0] });
				neigh[i + r].add(new int[] { i + r + 1, info[i + r + 1][c - 1] });
			}
		}
	
		for (int i = 0; i < d; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken()) - 1;
			int y = Integer.parseInt(read.nextToken()) - 1;
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>();
		}
	}
	
}
