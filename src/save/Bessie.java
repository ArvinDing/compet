package save;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Bessie {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int m = Integer.parseInt(in.readLine());
		in.readLine();
		int[][] info = new int[m][m];
		for (int i = 0; i < m; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < m; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		int test = Integer.parseInt(in.readLine());
		int[] t = new int[test];
		for (int i = 0; i < test; i++) {
			t[i] = Integer.parseInt(in.readLine());
		}
		System.out.println(countGroups(info, t));
	}

	static String mergeStrings(String a, String b) {
		String ans = "";
		int i;
		for (i = 0; i < a.length() && i < b.length(); i++) {
			ans += a.charAt(i);
			ans += b.charAt(i);
		}
		if (i < a.length())
			ans += a.substring(i, a.length());
		if (i < b.length())
			ans += b.substring(i, b.length());
		return ans;
	}

	static int[] countGroups(int[][] m, int[] t) {
		boolean done[][] = new boolean[m.length][m.length];
		int[] sizes = new int[m.length * m.length + 1];
		for (int i = 0; i < m.length; i++) {
			for (int k = 0; k < m.length; k++) {
				if (done[i][k])
					continue;
				if (m[i][k] == 1) {
					LinkedList<int[]> queue = new LinkedList<int[]>();
					queue.add(new int[] { i, k });
					done[i][k] = true;
					int size = 0;
					while (!queue.isEmpty()) {

						int[] info = queue.poll();

						size++;
						for (int rC = -1; rC <= 1; rC++) {
							for (int cC = -1; cC <= 1; cC++) {
								if (Math.abs(rC) == Math.abs(cC))
									continue;
								if (info[0] + rC >= 0 && info[0] + rC < m.length && info[1] + cC >= 0
										&& info[1] + cC < m.length) {
									if (!done[info[0] + rC][info[1] + cC] && m[info[0] + rC][info[1] + cC] == 1) {
										queue.add(new int[] { info[0] + rC, info[1] + cC });
										done[info[0] + rC][info[1] + cC] = true;
									}
								}
							}
						}
					}
					sizes[size]++;
				}
			}
		}
		int[] ans = new int[t.length];
		for (int i = 0; i < t.length; i++) {
			ans[i] = sizes[t[i]];
		}
		return ans;
	}

	static int numberOfPairs(int[] a, long k) {
		TreeSet<Long> contained = new TreeSet<Long>();

		int cntHalf = 0;
		for (int curr : a) {
			contained.add((long) curr);
			if (k % 2 == 0 && curr == k / 2) {
				cntHalf++;
			}
		}
		int cnt = 0;
		for (long curr : contained) {
			if (contained.contains(k - curr))
				cnt++;
		}
		if (cntHalf >= 2) {
			return (cnt / 2) + 1;
		}
		return cnt / 2;
	}

}
