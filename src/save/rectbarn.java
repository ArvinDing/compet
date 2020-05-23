package save;

/*
ID: dingarv1
LANG: JAVA
TASK: rectbarn
*/

import java.util.*;
import java.io.*;

public class rectbarn {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rectbarn.in"));
		PrintWriter out = new PrintWriter(new File("rectbarn.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(read.nextToken()), c = Integer.parseInt(read.nextToken()),
				obstacles = Integer.parseInt(read.nextToken());
		TreeSet<Integer>[] info = new TreeSet[r];
		for (int i = 0; i < obstacles; i++) {
			read = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(read.nextToken()) - 1;
			if (row >= r)
				continue;
			if (info[row] == null)
				info[row] = new TreeSet<Integer>();
			int col = Integer.parseInt(read.nextToken()) - 1;
			if (col >= c)
				continue;
			info[row].add(col);

		}
		TreeSet<int[]> queue = new TreeSet<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0])
					return arg0[1] - arg1[1];
				return arg1[0] - arg0[0];
			}
		});

		int max = -1;
		int i = 0;
		boolean[] done = new boolean[r];
		while (i < c) {

			for (int k = 0; k < r; k++) {
				if (!done[k]) {
					done[k] = true;
					if (info[k] != null && !info[k].isEmpty()) {
						queue.add(new int[] { info[k].first(), k });
					} else {
						queue.add(new int[] { c, k });
					}
				}
			}
			int closest = queue.last()[0];
			if (closest == c) {
				max = Math.max(max, r * (closest - i));
				break;
			}
			int[] size = new int[r];
			Arrays.fill(size, 1);
			int[] disjoint = new int[r];
			Arrays.fill(disjoint, -1);

			Iterator<int[]> itr = queue.iterator();

			while (itr.hasNext()) {
				int[] curr = itr.next();
				int sizeIdx = curr[1];
				disjoint[curr[1]] = curr[1];
				if (curr[1] != r - 1 && disjoint[curr[1] + 1] != -1) {
					int parent = disjoint[curr[1] + 1];
					while (parent != disjoint[parent]) {
						parent = disjoint[parent];
					}
					int loop = disjoint[curr[1] + 1];
					while (loop != disjoint[loop]) {
						loop = disjoint[loop];
						disjoint[loop] = parent;
					}
					size[parent] += size[curr[1]];
					disjoint[curr[1]] = parent;
					sizeIdx = parent;

				}
				if (curr[1] != 0 && disjoint[curr[1] - 1] != -1) {
					int parent = disjoint[curr[1] - 1];
					while (parent != disjoint[parent]) {
						parent = disjoint[parent];
					}
					int loop = disjoint[curr[1] - 1];
					while (loop != disjoint[loop]) {
						loop = disjoint[loop];
						disjoint[loop] = parent;
					}
					int a = disjoint[curr[1]];
					if (size[a] > size[parent]) {
						size[a] += size[parent];
						disjoint[parent] = a;
						sizeIdx = a;
					} else {
						size[parent] += size[a];
						disjoint[a] = parent;
						sizeIdx = parent;
					}

				}
				// if (i == 1) {
				// System.out.println(curr[1]);
				// for (int z = 0; z < r; z++) {
				// System.out.print(size[z] + " ");
				// }
				// System.out.println();
				// }
				max = Math.max(max, size[sizeIdx] * (curr[0] - i));
				if (closest == curr[0]) {
					// System.out.println(curr[1]);
					itr.remove();
					info[curr[1]].pollFirst();
					done[curr[1]] = false;
				}
			}
			i += ((closest - i) + 1);

		}
		out.println(max);
		out.close();
		in.close();
	}
}
