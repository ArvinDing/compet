import java.io.*;
import java.util.*;

public class cave2 {
	static boolean[][] info, done;
	static long mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cave.in"));
		PrintWriter out = new PrintWriter(new PrintWriter("cave.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		info = new boolean[n][m];
		done = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int k = 0; k < m; k++) {
				info[i][k] = (line.charAt(k) == '#');
			}
		}
		long ans = 1;
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				if (!info[i][k] && !done[i][k]) {
					LinkedList<int[]> queue = new LinkedList<int[]>();
					queue.add(new int[] { i, k });

					int min = Integer.MAX_VALUE;
					int max = Integer.MIN_VALUE;
					LinkedList<Integer> bottom = new LinkedList<Integer>();
					done[i][k] = true;
					while (!queue.isEmpty()) {

						int[] curr = queue.poll();
						int r = curr[0];
						int c = curr[1];
						if (curr[1] == 2) {
							System.out.println();
						}
						min = Math.min(curr[0], min);
						if (curr[0] > max) {
							max = curr[0];
							bottom = new LinkedList<Integer>();
							bottom.add(curr[1]);
						} else if (curr[0] == max) {
							bottom.add(curr[1]);
						}
						for (int rAdd = -1; rAdd <= 1; rAdd++) {
							for (int cAdd = -1; cAdd <= 1; cAdd++) {
								if (rAdd + cAdd == 1 || rAdd + cAdd == -1) {
									if (0 <= c + cAdd && c + cAdd < m && 0 <= r + rAdd && r + rAdd < n
											&& !info[r + rAdd][c + cAdd] && !done[r + rAdd][c + cAdd]) {
										queue.add(new int[] { r + rAdd, c + cAdd });
										done[r + rAdd][c + cAdd] = true;
									}
								}
							}
						}
					}
					int cnt=0;
					int previous = 1000000;
					for (int a : bottom) {
						if (a != previous+1) {
							cnt++;
						}
						previous=a;
					}
					int height = max - min + 1;
					long total = height - 1 + pow2mod(cnt);
					ans = ans * total % mod;
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}

	static long pow2mod(int power) {
		if (power == 0)
			return 1;
		return pow2mod(power - 1) * 2 % mod;
	}
}
