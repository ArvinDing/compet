
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class escape {
	static int mod = 1000000007;
	static int[] disjoint, size;
	static int[][] sort;
	static int n, k, start, prev;
	static long ways;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("escape.in"));
		PrintWriter out = new PrintWriter(new File("escape.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		sort = new int[n * (k - 1) + (n - 1) * k][4];
		int[][] hor = new int[n][k - 1];
		int[][] ver = new int[k][n - 1];
		// 0,1,2
		// 3,4,5
		// 6,7,8
		// 9,10,11
		// expo(2, 10);
		int idx = 0;
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			for (int j = 0; j < k - 1; j++) {
				hor[i][j] = Integer.parseInt(read.nextToken());
				sort[idx++] = new int[] { hor[i][j], i, j, 0, 0 };
			}
		}
		for (int i = 0; i < k; i++) {
			read = new StringTokenizer(in.readLine());
			for (int j = 0; j < n - 1; j++) {
				ver[i][j] = Integer.parseInt(read.nextToken());
				sort[idx++] = new int[] { ver[i][j], i, j, 1, 0 };
			}
		}
		Arrays.sort(sort, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		size = new int[n * k];
		disjoint = new int[n * k];
		Arrays.fill(disjoint, -1);
		Arrays.fill(size, 1);

		// kruskall
		prev = sort[0][0];
		start = 0;
		ways = 1;

		for (int i = 0; i < sort.length; i++) {
			if (sort[i][0] != prev) {
				update(i);
			}
			int[] temp = convert(sort[i]);
			// System.out.println(temp[0]+" "+temp[1]);
			int a = getParent(temp[0]);
			int b = getParent(temp[1]);

			if (a != b) {
				sort[i][4] = 1;
				if (size[a] < size[b]) {
					disjoint[a] = b;
					size[b] += size[a];
					size[a] = 0;
				} else {
					disjoint[b] = a;
					size[a] += size[b];
					size[b] = 0;
				}
			}
		}
		update(sort.length);
		out.println(ways);
		out.close();
		in.close();
	}

	static void update(int i) {
		HashMap<Integer, Integer> total = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> used = new HashMap<Integer, Integer>();

		for (int j = start; j < i; j++) {
			int[] temp = convert(sort[j]);
			int a = getParent(temp[0]);
			if (sort[j][4] == 1) {
				if (!used.containsKey(a)) {
					used.put(a, 1);
				} else {
					used.put(a, used.get(a) + 1);
				}
			}
			if (!total.containsKey(a)) {
				total.put(a, 1);
			} else {
				total.put(a, total.get(a) + 1);
			}
		}
		for (Entry<Integer, Integer> a : total.entrySet()) {
			if (!used.containsKey(a.getKey()))
				continue;
			ways = mult(ways, choose(a.getValue(), used.get(a.getKey())));

		}
		start = i;
		if (i != sort.length)
			prev = sort[i][0];
	}

	static long choose(int n, int k) {
		long temp = 1;
		for (int i = n - k + 1; i <= n; i++) {
			temp = mult(temp, i);
		}
		long denom = 1;
		for (int i = 1; i <= k; i++) {
			denom = mult(denom, i);
		}
		return mult(temp, modularInv(denom));
	}

	static long expo(long base, long exp) {
		if (exp == 0)
			return 1;

		long t = expo(base, exp / 2);
		t = mult(t, t);
		if (exp % 2 == 0)
			return t;
		else
			return mult(base, t);
	}

	static long modularInv(long denom) {
		// find denom^(p-2)
		return expo(denom, mod - 2);
	}

	static int[] convert(int[] info) {
		if (info[3] == 0) {
			return new int[] { info[1] * k + info[2], info[1] * k + info[2] + 1 };
		} else {
			return new int[] { info[2] * k + info[1], info[2] * k + info[1] + k };
		}
	}

	static int getParent(int idx) {
		int temp = idx;
		while (disjoint[temp] != -1) {
			temp = disjoint[temp];
		}
		int imp = temp;
		temp = idx;
		while (disjoint[temp] != -1) {
			int next = disjoint[temp];
			disjoint[temp] = imp;
			temp = next;
		}
		return imp;

	}

	static long mult(long a, long b) {
		return (a * b) % mod;
	}
}
