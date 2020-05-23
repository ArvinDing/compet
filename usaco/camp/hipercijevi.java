
package camp;

import java.io.*;
import java.util.*;

public class hipercijevi {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		LinkedList<Integer>[] contained = new LinkedList[n];
		LinkedList<Integer>[] insideNode = new LinkedList[m];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			insideNode[i] = new LinkedList<Integer>();
			for (int z = 0; z < k; z++) {
				int curr = Integer.parseInt(read.nextToken()) - 1;
				if (contained[curr] == null)
					contained[curr] = new LinkedList<Integer>();
				contained[curr].add(i);
				insideNode[i].add(curr);
			}
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		boolean[] visitedReal = new boolean[n];
		boolean[] visitedNew = new boolean[m];
		queue.add(new int[] { 0, 1 });
		visitedReal[0] = true;
		outer: {
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				if (curr[0] == n - 1) {
					System.out.println(curr[1]);
					break outer;
				}
				if (contained[curr[0]] != null)
					for (int nodes : contained[curr[0]]) {
						if (!visitedNew[nodes]) {
							if (insideNode[nodes] != null)
								for (int real : insideNode[nodes]) {
									if (!visitedReal[real]) {
										queue.add(new int[] { real, curr[1] + 1 });
										visitedReal[real] = true;
									}
								}
							visitedNew[nodes] = true;
						}
					}

			}
			System.out.println(-1);
		}
		in.close();
	}
}
