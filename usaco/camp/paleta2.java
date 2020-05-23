
package camp;

import java.io.*;
import java.util.*;

public class paleta2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int colors = Integer.parseInt(read.nextToken());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			info[i][0] = -1;
			info[i][1] = -1;
		}
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(read.nextToken()) - 1;
			info[i][0] = curr;
			info[curr][1] = i;
		}
		int[] val = new int[n];
		boolean[] done = new boolean[n];
		boolean[] added = new boolean[n];
		long total = 1;
		for (int i = 0; i < n; i++) {
			if (done[i])
				continue;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(i);
			added[i] = false;
			int length = 0;
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				int restrictions = 0;
				length++;
				done[curr] = true;
				for (int z = 0; z < 2; z++) {
					if (z == 1 && info[curr][0] == info[curr][1])
						continue;
					int neigh = info[curr][z];
					if (neigh == curr || neigh == -1)
						continue;
					if (!done[neigh]) {
						if (!added[neigh]) {
							queue.add(neigh);
							added[neigh] = true;
						}
					} else {
						restrictions++;
					}
				}
				val[curr] = colors - restrictions;
				total = (total * (colors - restrictions)) % 1000000007;
				if (restrictions == 2) {
					long add = colors;
					if (length == 3)
						add = 0;
					else {
						for (int l = 0; l < length - 3; l++) {
							add = (add * (colors - 1)) % 1000000007;
						}
					}
					total = (total + (add * (colors - 1))) % 1000000007;
				}
			}
		}
		if (total <= 0)
			total = 0;
		System.out.println(total);
		in.close();
	}
}
