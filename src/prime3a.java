
/*
ID: dingarv1
LANG: JAVA
TASK: prime3
*/

import java.io.*;
import java.util.*;

public class prime3a {
	static boolean[] prefix = new boolean[100000];
	static boolean[] prime = new boolean[100000];
	static LinkedList<int[]> possC;
	static int leftT;

	public static void main(String[] args) throws Exception {
		//long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("prime3.in"));
		PrintWriter out = new PrintWriter(new File("prime3.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int sum = Integer.parseInt(read.nextToken());
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
		possC = new LinkedList<int[]>();

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
				int[] temp = new int[5];
				for (int k = 0; k < 5; k++) {
					hash *= 10;
					temp[k] = a.charAt(k) - '0';
					hash += a.charAt(k) - '0';
					prefix[hash] = true;
				}
				possC.add(temp);
			}
		}
		recursion(0, new int[5], 0, 0);
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

	static void recursion(int c, int[] rH, int ttb, int btt) {
		outer: for (int[] a : possC) {
			if (c == 0 && a[0] != leftT)
				continue;
			for (int i = 0; i < 5; i++) {
				if (!prefix[rH[i] * 10 + a[i]])
					continue outer;
			}
			if (!prefix[ttb * 10 + a[c]] || !prefix[btt * 10 + a[4 - c]])
				continue;
			for (int i = 0; i < 5; i++) {
				rH[i] *= 10;
				rH[i] += a[i];
			}
			if (c == 4) {
				print.add(new int[] { rH[0], rH[1], rH[2], rH[3], rH[4] });
			} else
				recursion(c + 1, rH, ttb * 10 + a[c], btt * 10 + a[4 - c]);
			for (int i = 0; i < 5; i++) {
				rH[i] -= a[i];
				rH[i] /= 10;
			}
		}
	}

}
