package camp;

import java.io.*;
import java.util.*;

public class lca {
	private static int[][] edges;
	private static int[] start;
	private static boolean[] visited;
	private static int idx = 0;
	private static int[] path;
	private static int[] rename;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		edges = new int[n - 1][2];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			edges[i][0] = Integer.parseInt(read.nextToken());
			edges[i][1] = Integer.parseInt(read.nextToken());
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0])
					return arg0[1] - arg1[1];
				return arg0[0] - arg1[0];
			}
		});
		start = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			start[edges[i][0]] = i;
		}
		preCompute(n);
		visited = new boolean[2 * n];
		dfs(0);
		int[] firstTime = new int[n];
		Arrays.fill(firstTime, -1);
		for (int i = 0; i < 2 * n; i++) {
			if (firstTime[i] == -1)
				firstTime[path[i]] = i;
		}
		int layers = logBase2[n];
		int[][] info = new int[layers + 1][2 * n];
		for (int i = 0; i < n; i++) {
			info[0][i] = path[i];
		}
		int gap = 1;
		for (int i = 1; i < layers + 1; i++) {
			for (int k = 0; k < n - gap; k++) {
				info[i][k] = Math.min(info[i - 1][k], info[i - 1][k + gap]);
			}
			gap *= 2;
		}

		// query
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			int[] curr = new int[] { firstTime[start], firstTime[end] };
			int size = bitL[curr[1] - curr[0] + 1];
			int imp = logBase2[size];
			Math.max(info[imp][curr[0]], info[imp][curr[1] - size + 1]);
		}

	}

	private static int[] bitL;
	private static int[] logBase2;
	private static int other = 0;

	private static void dfs(int curr) {
		int startE = start[curr];
		visited[curr] = true;
		int save = other;
		rename[other++] = curr;

		for (int i = startE; i < n; i++) {
			if (edges[i][0] != curr)
				break;
			int neigh = edges[i][1];
			if (!visited[neigh]) {
				path[idx++] = save;
				dfs(neigh);
			}
		}

	}

	private static void preCompute(int n) {
		// 2^floor(log2(n))
		bitL = new int[n + 1];
		int temp = 1;
		for (int i = 1; i <= n; i++) {
			if (i >= temp * 2)
				temp *= 2;
			bitL[i] = temp;
		}
		boolean[] points = new boolean[n + 1];
		int add = 1;
		while (add <= n) {
			points[add] = true;
			add = add << 1;
		}
		int[] logBase2 = new int[n + 1];
		int sum = 0;
		for (int i = 0; i <= n; i++) {
			logBase2[i] = sum;
			if (points[i])
				sum++;
		}
	}

}
