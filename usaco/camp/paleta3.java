
package camp;

import java.io.*;
import java.util.*;

public class paleta3 {
	private static long[] cycleDp;
	private static long[] kmonetp;
	private static int k;
	private static int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		cycleDp = new long[n + 1];
		kmonetp = new long[n + 1];
		// (k-1)^n;
		LinkedList<Integer>[] neighbor = new LinkedList[n];
		for (int i = 0; i < n; i++)
			neighbor[i] = new LinkedList<Integer>();
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int par = Integer.parseInt(read.nextToken()) - 1;
			neighbor[i].add(par);
			neighbor[par].add(i);

		}
		int[] distance = new int[n];
		int[] done = new int[n];
		long total = 1;
		Arrays.fill(done, -1);
		for (int i = 0; i < n; i++) {
			if (done[i] != -1)
				continue;
			int mult = k;
			LinkedList<int[]> queue = new LinkedList<int[]>();
			queue.add(new int[] { i, -1, 0 });
			while (!queue.isEmpty()) {
				int[] first = queue.poll();
				int idx = first[0];
				int previous = first[1];
				distance[idx] = first[2];
				done[idx] = i;
				if (idx != i)
					mult = (mult * (k - 1)) % mod;
				for (int neigh : neighbor[idx]) {
					if (neigh == previous || neigh == idx)
						continue;
					if (done[neigh] != -1) {
						if (done[neigh] == i) {
							int cycleL = first[2] - distance[neigh] + 1;
							mult -= caculateC(cycleL - 1);
						}
						break;
					}
					queue.add(new int[] { neigh, idx, first[2]+1 });

				}
			}
			total *= mult;
		}
		System.out.println(total);
		in.close();
	}

	private static long caculateKMO(int power) {
		if (power == 0)
			return 1;
		if (kmonetp[power] != 0)
			return kmonetp[power];
		kmonetp[power] = ((k - 1) * (caculateKMO(power - 1))) % mod;
		return kmonetp[power];

	}

	private static long caculateC(int length) {
		if (length == 1)
			return 0;
		if (cycleDp[length] != 0)
			return cycleDp[length];
		cycleDp[length] = ((k * caculateKMO(length - 1)) - caculateC(length - 1)) % mod;
		return cycleDp[length];

	}
}
