package camp;

import java.io.*;
import java.util.*;

public class svemir {
	private static int[] disjoint;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			int z = Integer.parseInt(read.nextToken());
			info[i] = new int[] { x, y, z, i };
		}
		LinkedList<int[]> edges = new LinkedList<int[]>();
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		for (int i = 1; i < n; i++) {
			edges.add(new int[] { info[i][0] - info[i - 1][0], info[i][3], info[i - 1][3] });
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		for (int i = 1; i < n; i++) {
			edges.add(new int[] { info[i][1] - info[i - 1][1], info[i][3], info[i - 1][3] });
		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[2] - arg1[2];
			}
		});
		for (int i = 1; i < n; i++) {
			edges.add(new int[] { info[i][2] - info[i - 1][2], info[i][3], info[i - 1][3] });
		}
		Collections.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		int total=0;
		disjoint=new int [n];
		for (int i = 0; i < n; i++)
			disjoint[i] = i;
		for (int[] loop : edges) {
			int cost = loop[0];
			int a = loop[1];
			int b = loop[2];
			int aP = getParent(a);
			int bP = getParent(b);
			if (aP != bP) {
				disjoint[aP] = bP;
				total+=cost;
			}
		}
		System.out.println(total);
	}

	private static int getParent(int i) {
		int parent = i;
		while (disjoint[parent] != parent) {
			parent = disjoint[parent];
		}
		while (disjoint[i] != i) {
			int old = disjoint[i];
			disjoint[i] = parent;
			i = old;
		}
		return parent;

	}
}
