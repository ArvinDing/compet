package camp;

import java.io.*;
import java.util.*;

public class ronald {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());
		boolean[][] neigh = new boolean[n][n];
		int[] neighbors = new int[n];
		for (int i = 0; i < m; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			neigh[start][end] = true;
			neigh[end][start]=true;
			neighbors[start]++;
			neighbors[end]++;
		}
		

	}
}
