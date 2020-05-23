
package camp;

import java.io.*;
import java.util.*;

public class holmes {
	private static LinkedList<Integer>[] parent;
	private static LinkedList<Integer>[] edges;
	private static boolean[] certainT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int events = Integer.parseInt(read.nextToken());
		int imp = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		edges = new LinkedList[events];
		parent = new LinkedList[events];
		for (int i = 0; i < imp; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (edges[a] == null)
				edges[a] = new LinkedList<Integer>();
			edges[a].add(b);
			if (parent[b] == null)
				parent[b] = new LinkedList<Integer>();
			parent[b].add(a);
		}
		certainT = new boolean[events];
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			updateT(curr);
		}

		for (int i = 0; i < events; i++) {
			if (certainT[i])
				continue;
			boolean[] possibleF = new boolean[events];
			possibleF[i] = true;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(i);

			outer: while (!queue.isEmpty()) {
				int current = queue.poll();
				if (edges[current] != null) {
					out: for (int neigh : edges[current]) {
						if (!possibleF[neigh] && parent[neigh] != null) {
							for (int par : parent[neigh]) {
								if (!possibleF[par])
									continue out;
							}
							possibleF[neigh] = true;
							if (certainT[neigh]) {
								updateT(i);
								break outer;
							}
							queue.add(neigh);
						}
					}
				}
				if (parent[current] != null)
					for (int neigh : parent[current]) {
						if (!possibleF[neigh]) {
							possibleF[neigh] = true;
							if (certainT[neigh]) {
								updateT(i);
								break outer;
							}
							queue.add(neigh);
						}
					}
			}

		}
		boolean first = true;
		for (int i = 0; i < events; i++) {
			if (certainT[i]) {
				if (first) {
					System.out.print(i + 1);
					first = false;
				} else
					System.out.print(" " + (i + 1));
			}
		}
		System.out.println();
		in.close();
	}

	private static void updateT(int curr) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		certainT[curr] = true;
		queue.add(curr);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (parent[current] != null) {
				int par = parent[current].peekFirst();
				if (parent[current].size() == 1 && !certainT[par]) {
					certainT[par] = true;
					queue.add(par);
				}
			}
			if (edges[current] != null)
				for (int neigh : edges[current]) {
					if (!certainT[neigh]) {
						certainT[neigh] = true;
						queue.add(neigh);
					}
				}
		}
	}
}
