package camp;

import java.io.*;
import java.util.*;

public class simplify {
	private static int[] disjoint;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("simplify.in"));
		PrintWriter out = new PrintWriter(new File("simplify.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		int[][] edges = new int[m][3];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			int val = Integer.parseInt(read.nextToken());
			edges[i] = new int[] { val, a, b };
		}
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		disjoint = new int[n];
		for (int i = 0; i < n; i++)
			disjoint[i] = i;
		int possible = 1;
		for (int i = 0; i < m; i++) {
			int val = edges[i][0];
			List<int[]> curr = new ArrayList<int[]>();
			curr.add(new int[] { edges[i][1], edges[i][2] });
			for (int k = i + 1; k < m; k++) {
				if (edges[k][0] != val)
					break;
				else
					curr.add(new int[] { edges[k][1], edges[k][2] });
			}
			TreeMap<Integer, Integer> keys = new TreeMap<Integer, Integer>();
			int size=0;
			for (int[] loop : curr) {
				int parent = getParent(loop[0]);
				int parent1 = getParent(loop[1]);
				if (parent != parent1) {
					
					if (!keys.containsKey(parent)) {
						keys.put(parent, 0);
						size++;
					}
					keys.put(parent, keys.get(parent) + 1);
					if (!keys.containsKey(parent1)) {
						keys.put(parent1, 0);
						size++;
					}
					keys.put(parent1, keys.get(parent1) + 1);
				}
			}
			int []number=new int [size];
			int idx=0;
			for(int key:keys.keySet()) {
				number[idx]=key;
						idx++;
			}
			int twoes = 0;
			for (int value : keys.values()) {
				if(value==2)
					twoes++;
			}
			if(twoes==3&&size==3) {
				possible*=3;
				disjoint[number[0]]=number[1];
				disjoint[number[2]]=number[1];
			}else {
				
			}

		}
		out.println(possible);
		out.close();
		in.close();
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
