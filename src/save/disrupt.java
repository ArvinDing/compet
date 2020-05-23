package save;

import java.io.*;
import java.util.*;

public class disrupt {


	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("disrupt.in"));
		PrintWriter out = new PrintWriter(new File("disrupt.out"));
	
		// gonna do bruteforce
		// how do you efficiently find the path between 2 different nodes in a tree
		// multiple times?
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		LinkedList<int[]>[] neighbors = new LinkedList[n];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (neighbors[a] == null)
				neighbors[a] = new LinkedList<int[]>();
			if (neighbors[b] == null)
				neighbors[b] = new LinkedList<int[]>();
			neighbors[a].add(new int[] { b, i });
			neighbors[b].add(new int[] { a, i });
		}
		int[][] extras = new int[m][3];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			extras[i] = new int[] { a, b, cost };
		}
		Arrays.sort(extras, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[2] - arg1[2];
			}
		});
		int[] ans = new int[n - 1];
		Arrays.fill(ans, -1);
		for (int i = 0; i < m; i++) {
			int a = extras[i][0];
			int b = extras[i][1];
			LinkedList<int[]> queue = new LinkedList<int[]>();
			queue.add(new int[] { a, -1, -1 });
			int index = 0;
			List<int[]> save = new ArrayList<int[]>();
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				save.add(curr);
				if (curr[0] == b) {
					int[] current = curr;
					while (current[1] != -1) {
						for (int[] neighbor : neighbors[current[0]]) {
							if (neighbor[0] == current[1]) {
								if (ans[neighbor[1]] == -1)
									ans[neighbor[1]] = extras[i][2];
								break;
							}
						}
						current = save.get(current[2]);
					}
				}
				for (int[] neighbor : neighbors[curr[0]]) {
					if (curr[1] != neighbor[0])
						queue.add(new int[] { neighbor[0], curr[0], index });
				}
				index++;
			}

		}
		for (int curr : ans) {
			out.println(curr);
		}
		out.close();
		in.close();
	}
}