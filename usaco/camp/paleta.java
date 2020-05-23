
package camp;

import java.io.*;
import java.util.*;

public class paleta {
	private static int k;
	private static long[] kmoneP;
	private static long[] cycleCnt;
	private static int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		int[] parent = new int[n];
		int[] done = new int[n];
		Arrays.fill(done, -1);
		boolean[] isCycle = new boolean[n + 1];
		cycleCnt = new long[n + 1];
		kmoneP = new long[n + 1];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(read.nextToken()) - 1;
			parent[i] = a;
		}
		kmoneP[0] = 1;
		for (int i = 1; i <= n; i++)
			kmoneP[i] = (kmoneP[i - 1] * (k - 1)) % mod;
		cycleCnt[1] = 0;
		for (int i = 2; i <= n; i++)
			cycleCnt[i] = ((k * kmoneP[i - 1]) - cycleCnt[i - 1] + mod) % mod;
		long total = 1;
		for (int i = 0; i < n; i++) {
			if (done[i]!=-1)
				continue;
			int curr = i;
			Stack<Integer> stack = new Stack<Integer>();
			while (true) {
				if (isCycle[parent[curr]])
					break;
				done[curr] = i;
				stack.push(curr);
				if (done[parent[curr]]==i) {
					if (parent[curr] == curr) {
						isCycle[stack.pop()] = true;
						total = (total * k) % mod;
						break;
					}
					int size = 0;
					while (stack.peek() != parent[curr]) {
						size++;
						int back = stack.pop();
						isCycle[back] = true;
					}
					size++;

					int cPar = stack.pop();
					isCycle[cPar] = true;
					total = (total * cycleCnt[size]) % mod;
					break;
				}

				curr = parent[curr];
			}
		}
		for (int i = 0; i < n; i++) {
			if (!isCycle[i])
				total = (total * (k - 1)) % mod;
		}
		System.out.println(total);
		in.close();
	}
}
