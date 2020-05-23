
import java.io.*;
import java.util.*;

public class valleys {
	static boolean[][] done;
	static int n;
	static PriorityQueue<int[]> neighbors;
	static int[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("valleys.in"));
		PrintWriter out = new PrintWriter(new File("valleys.out"));
		n = Integer.parseInt(in.readLine());
		info = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < n; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		int sum = 0;
		for (int sI = 0; sI < n; sI++) {
			for (int sK = 0; sK < n; sK++) {
				neighbors = new PriorityQueue<int[]>(new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						return o1[0] - o2[0];
					}
				});
				done = new boolean[n][n];
				int doneCnt = 1;
				done[sI][sK] = true;
				int max = Integer.MIN_VALUE;
				max = Math.max(max, info[sI][sK]);
				if (addNeigh(sI, sK, max)) {
					sum++;
				}
				while (!neighbors.isEmpty()) {
					int[] curr = neighbors.poll();
					if (done[curr[1]][curr[2]])
						continue;
					done[curr[1]][curr[2]] = true;
					max = Math.max(max, info[curr[1]][curr[2]]);
					doneCnt++;
					if (addNeigh(curr[1], curr[2], max) && check() == n * n - doneCnt) {
						sum++;
						System.out.println(doneCnt);
					}
				}
				System.out.println(sum);
			}
		}
		out.println(sum);
		in.close();
		out.close();
	}

	static int[] first() {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				if (!done[i][k])
					return new int[] { i, k };
			}
		}
		return null;
	}

	static int check() {
		int[] a = first();
		if (a == null)
			return 0;
		int cnt = 1;
		boolean[][] tempDone = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				tempDone[i][k] = done[i][k];
			}
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(a);
		tempDone[a[0]][a[1]] = true;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int i = curr[0];
			int k = curr[1];

			for (int iAdd = -1; iAdd <= 1; iAdd++) {
				for (int kAdd = -1; kAdd <= 1; kAdd++) {
					if (iAdd == 0 & kAdd == 0)
						continue;
					if (0 <= i + iAdd && i + iAdd < n) {
						if (0 <= k + kAdd && k + kAdd < n) {
							if (!tempDone[i + iAdd][k + kAdd]) {
								queue.add(new int[] { i + iAdd, k + kAdd });
								tempDone[i + iAdd][k + kAdd] = true;
								cnt++;
								// System.out.println(i + iAdd + "");
							}
						}
					}
				}
			}
		}
		return cnt;
	}

	static boolean addNeigh(int i, int k, int max) {

		int min = Integer.MAX_VALUE;
		if (i + 1 < n && !done[i + 1][k]) {
			min = Math.min(min, info[i + 1][k]);
			neighbors.add(new int[] { info[i + 1][k], i + 1, k });
		}
		if (i - 1 >= 0 && !done[i - 1][k]) {
			min = Math.min(min, info[i - 1][k]);
			neighbors.add(new int[] { info[i - 1][k], i - 1, k });

		}
		if (k + 1 < n && !done[i][k + 1]) {
			min = Math.min(min, info[i][k + 1]);
			neighbors.add(new int[] { info[i][k + 1], i, k + 1 });

		}
		if (k - 1 >= 0 && !done[i][k - 1]) {
			min = Math.min(min, info[i][k - 1]);
			neighbors.add(new int[] { info[i][k - 1], i, k - 1 });
		}
		return min > max;
	}
}
