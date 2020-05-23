package camp;

import java.io.*;
import java.util.*;

public class rblock {
	private static int[] disjoint;
	private static LinkedList<Integer>[] neigh;
	private static int[][] cost;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new File("rblock.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		cost = new int[n][n];
		neigh = new LinkedList[n];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int val = Integer.parseInt(read.nextToken());
			cost[a][b] = val;
			cost[b][a] = val;
			if (neigh[a] == null)
				neigh[a] = new LinkedList<Integer>();
			if (neigh[b] == null)
				neigh[b] = new LinkedList<Integer>();
			neigh[a].add(b);
			neigh[b].add(a);
		}
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		queue.add(new int[] { 0, 0, -1 });
		int[][] path = new int[n][2];
		int idx = 0;
		int best = -1;
		boolean[] visited = new boolean[n];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int current = curr[0];
			if (visited[current])
				continue;
			path[idx] = new int[] { current, curr[2] };
			if (current == n - 1) {
				best = curr[1];
				break;
			}
			visited[current] = true;
			for (int neighbor : neigh[current]) {
				if (!visited[neighbor]) {
					queue.add(new int[] { neighbor, cost[current][neighbor] + curr[1], idx });
				}
			}
			idx++;
		}
		int node = idx;
		int max = Integer.MIN_VALUE;
		while (true) {
			int curr = path[node][0];
			int previousIdx = path[node][1];
			if (previousIdx == -1)
				break;
			int previous = path[previousIdx][0];
			cost[curr][previous] *= 2;
			cost[previous][curr] *= 2;
			max = Math.max(max, shortest() - best);
			cost[curr][previous] /= 2;
			cost[previous][curr] /= 2;
			node = previousIdx;
		}
		out.println(max);
		out.close();
		in.close();

	}

	private static int shortest() {
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		queue.add(new int[] { 0, 0, -1 });
		boolean[] visited = new boolean[n];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int current = curr[0];
			if (visited[current])
				continue;

			if (current == n - 1) {
				return curr[1];
			}
			visited[current] = true;
			for (int neighbor : neigh[current]) {
				if (!visited[neighbor]) {
					queue.add(new int[] { neighbor, cost[current][neighbor] + curr[1], curr[0] });
				}
			}
		}
		return -1;
	}
}
