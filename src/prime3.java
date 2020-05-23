
/*
ID: dingarv1
LANG: JAVA
TASK: prime3
*/

import java.io.*;
import java.util.*;

public class prime3 {
	static boolean[] prefix = new boolean[100000];
	static int sum;
	static int leftT;
	static boolean[] prime;
	// static int[][] grid = new int[5][5];

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("prime3.in"));
		PrintWriter out = new PrintWriter(new File("prime3.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		sum = Integer.parseInt(read.nextToken());
		leftT = Integer.parseInt(read.nextToken());
		prime = new boolean[100000];
		Arrays.fill(prime, true);
		for (int i = 2; i < prime.length; i++) {
			if (prime[i]) {
				for (int k = 2 * i; k < prime.length; k += i) {
					prime[k] = false;
				}
			}
		}
		prefix[0] = true;
		for (int i = 10000; i < prime.length; i++) {
			if (prime[i]) {
				String a = i + "";
				int hash = 0;
				int add = 0;
				for (int k = 0; k < 5; k++) {
					add += a.charAt(k) - '0';
				}
				if (add != sum)
					continue;
				for (int k = 0; k < 5; k++) {
					hash *= 10;
					hash += a.charAt(k) - '0';
					prefix[hash] = true;
				}
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		// first 5-horHash,top-bottom dag,bottom-top dag
		// grid[0][0] = leftT;

		recursion(1, 0, new int[] { leftT, 0, 0, 0, 0, leftT, 0 }, leftT, leftT);
		System.out.println(System.currentTimeMillis() - start);
		if (print.isEmpty()) {
			out.println("NONE");
		} else {
			while (!print.isEmpty()) {
				int[] curr = print.poll();
				for (int i = 0; i < 5; i++) {
					out.println(curr[i]);
				}
				if (!print.isEmpty())
					out.println();
			}
		}
		System.out.println(System.currentTimeMillis() - start + " " + cnt);
		in.close();
		out.close();
	}

	static PriorityQueue<int[]> print = new PriorityQueue<int[]>(new Comparator<int[]>() {

		@Override
		public int compare(int[] o1, int[] o2) {
			for (int i = 0; i < 5; i++)
				if (o1[i] < o2[i]) {
					return -1;
				} else if (o2[i] < o1[i])
					return 1;
			return 0;
		}
	});
	private static int cnt;

	private static void recursion(int r, int c, int[] hash, int verH, int totalCs) {
		if (c == 4 && r == 4) {
			for (int i = 0; i < 10; i++) {
				if (prefix[hash[4] * 10 + i] && prefix[hash[5] * 10 + i] && prefix[verH * 10 + i]) {
					hash[4] *= 10;
					hash[4] += i;
					print.add(new int[] { hash[0], hash[1], hash[2], hash[3], hash[4] });
					hash[4] -= i;
					hash[4] /= 10;
				}
			}
			return;
		}
		boolean tbDag = (r == c);
		boolean btDag = (r + c) == 4;
		if (r == 4) {
			int digit = sum - totalCs;
			if (digit >= 10 || digit < 0 || (c == 0 && digit == 0) || !(prefix[hash[r] * 10 + digit])) {
				return;
			}

			hash[4] *= 10;
			hash[4] += digit;
			// grid[c][4] = digit;
			if (tbDag) {
				hash[5] *= 10;
				hash[5] += digit;
			}
			if (btDag) {
				hash[6] *= 10;
				hash[6] += digit;
			}
			if (prefix[10 * verH + digit] && prefix[hash[6]] && prefix[hash[5]]) {
				recursion(0, c + 1, hash, 0, 0);
			}
			if (tbDag) {
				hash[5] -= digit;
				hash[5] /= 10;
			}
			if (btDag) {
				hash[6] -= digit;
				hash[6] /= 10;
			}
			hash[4] -= digit;
			hash[4] /= 10;
			cnt++;
			return;
		}
		for (int i = ((r == 0 || c == 0) ? 1 : 0); i < 10; i++) {
			if (!(prefix[hash[r] * 10 + i]))
				continue;
			hash[r] *= 10;
			hash[r] += i;
			// grid[c][r] = i;
			if (tbDag) {
				hash[5] *= 10;
				hash[5] += i;
			}
			if (btDag) {
				hash[6] *= 10;
				hash[6] += i;
			}
			if (prefix[hash[6]] && prefix[hash[5]])
				recursion(r + 1, c, hash, verH * 10 + i, totalCs + i);
			if (tbDag) {
				hash[5] -= i;
				hash[5] /= 10;
			}
			if (btDag) {
				hash[6] -= i;
				hash[6] /= 10;
			}
			hash[r] -= i;
			hash[r] /= 10;
		}

	}
}
