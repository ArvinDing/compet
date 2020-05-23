import java.io.*;
import java.util.*;

public class sirni {
	private static int[] disjointS;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("sirni.in"));
		PrintWriter out = new PrintWriter(new File("sirni.out"));
		int n = Integer.parseInt(in.readLine());
		int[] info = new int[n];
		int maxP = 10000001;
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(info);
		LinkedList<Integer>[] closest = new LinkedList[n];
		int[] distance = new int[n];
		for (int i = 0; i < n; i++)
			distance[i] = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int mult = 1;
			int idx = i + 1;
			boolean updated = true;
			while (mult * info[i] < maxP) {
				while (idx != n && info[idx] < mult * info[i]) {
					idx++;
					updated = true;
				}
				if (idx == n)
					break;
				if (distance[idx] > info[idx] - (mult * info[i])) {
					distance[idx] = info[idx] - (mult * info[i]);
					LinkedList<Integer> temp = new LinkedList<Integer>();
					temp.add(i);
					closest[idx] = temp;
				} else if (distance[idx] == info[idx] - (mult * info[i])) {
					closest[idx].add(i);
				}
				if (distance[idx] == 0)
					mult++;
				if (updated)
					while ((mult + 1) * info[i] < info[idx])
						mult++;
				else
					while ((mult) * info[i] < info[idx])
						mult++;
				updated = false;

			}
		}
		LinkedList<int[]> edges = new LinkedList<int[]>();
		for (int i = 0; i < n; i++) {
			if (closest[i] != null)
				for (int neighbors : closest[i]) {
					edges.add(new int[] { distance[i], i, neighbors });
				}
		}
		Collections.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		disjointS = new int[n];
		for (int i = 0; i < n; i++)
			disjointS[i] = i;
		int edgesC = 0;
		int cost = 0;
		while (!edges.isEmpty()) {
			int[] curr = edges.poll();
			int a = curr[1];
			int b = curr[2];
			int aP = getParent(a);
			int bP = getParent(b);
			if (aP != bP) {
				edgesC++;
				cost += curr[0];
				disjointS[aP] = bP;
			}
			if (edgesC == n - 1)
				break;
		}
		out.println(cost);
		out.close();
		in.close();
	}

	private static int getParent(int idx) {
		int parent = idx;
		while (disjointS[parent] != parent) {
			parent = disjointS[parent];
		}
		while (disjointS[idx] != idx) {
			int old = disjointS[idx];
			disjointS[idx] = parent;
			idx = old;
		}
		return parent;
	}
}
