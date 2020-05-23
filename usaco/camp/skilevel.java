package camp;

import java.io.*;
import java.util.*;

public class skilevel {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("skilevel.in"));
		PrintWriter out = new PrintWriter(new File("skilevel.out"));
		int m = Integer.parseInt(in.readLine());
		int n = Integer.parseInt(in.readLine());
		int minT = Integer.parseInt(in.readLine());
		boolean[][] start = new boolean[m][n];
		int[][] info = new int[m][n];
		LinkedList<int[]> edges = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < n; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());

			}
		}
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				int index = i * m + k;
				for (int iAdd = -1; iAdd <= 1; iAdd++) {
					for (int kAdd = -1; kAdd <= 1; kAdd++) {
						if ((Math.abs(iAdd) ^ Math.abs(kAdd)) == 1)
							if (0 <= i + iAdd && i + iAdd < m && 0 <= k + kAdd && k + kAdd < n) {
								edges.add(new int[] { Math.abs(info[i][k] - info[i + iAdd][k + kAdd]), index,
										(i + iAdd) * m + k + kAdd });
								edges.add(new int[] { Math.abs(info[i][k] - info[i + iAdd][k + kAdd]),
										(i + iAdd) * m + k + kAdd, index });
							}
					}
				}
			}
		}
		Collections.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		int[] disjointS = new int[m * n];
		int[] size = new int[m * n];
		Arrays.fill(size, 1);
		for (int[] edge : edges) {
			int cost = edge[0];
		}

	}

}
