import java.io.*;
import java.util.*;

public class disruption {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("disrupt.in"));
		PrintWriter out = new PrintWriter(new File("disrupt.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		LinkedList<int[]>[] connections = new LinkedList[n];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(read.nextToken()) - 1;
			int q = Integer.parseInt(read.nextToken()) - 1;
			if (connections[p] == null)
				connections[p] = new LinkedList<int[]>();
			if (connections[q] == null)
				connections[q] = new LinkedList<int[]>();
			connections[p].add(new int[] { q, i });
			connections[q].add(new int[] { p, i });
		}
		int[][] replace = new int[m][3];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(read.nextToken()) - 1;
			int q = Integer.parseInt(read.nextToken()) - 1;
			int r = Integer.parseInt(read.nextToken());
			replace[i] = new int[] { p, q, r };
		}
		Arrays.sort(replace, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[2] - arg1[2];
			}
		});
		LinkedList<int[]> queue = new LinkedList<int[]>();
		int[] parent = new int[n];
		queue.add(new int[] { 0, 0, -1 });
		int[] depth = new int[n];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			depth[curr[0]] = curr[1];
			parent[curr[0]] = curr[2];
			for (int[] neigh : connections[curr[0]]) {
				if (neigh[0] != curr[2])
					queue.add(new int[] { neigh[0], curr[1] + 1, curr[0] });
			}
		}

		int[] ans = new int[n - 1];
		Arrays.fill(ans, -1);
		for (int i = 0; i < m; i++) {
			int a = replace[i][0];
			int b = replace[i][1];
			int cost = replace[i][2];
			while (a != b) {
				if (depth[a] < depth[b]) {
					b = parent[b];
				} else {
					a = parent[a];
				}
			}
			int lca = a;
			a = replace[i][0];
			b = replace[i][1];
			while (a != b) {
				if (depth[a] < depth[b]) {
					int old = b;
					b = parent[b];
					Iterator<int[]> itr = connections[old].iterator();
					while (itr.hasNext()) {
						int[] neigh = itr.next();
						if (neigh[0] == b) {
							ans[neigh[1]] = cost;
							itr.remove();
							break;
						}
					}
					parent[old] = lca;
				} else {
					int old = a;
					a = parent[a];
					Iterator<int[]> itr = connections[old].iterator();
					while (itr.hasNext()) {
						int[] neigh = itr.next();
						if (neigh[0] == a) {
							ans[neigh[1]] = cost;
							itr.remove();
							break;
						}
					}
					parent[old] = lca;
				}
			}
		}
		for (int i = 0; i < n - 1; i++) {
			out.println(ans[i]);
		}
		out.close();
		in.close();
	}
}
