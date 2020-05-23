
import java.io.*;
import java.util.*;

public class nodepairs {
	private static LinkedList<int[]>[] connections;
	private static int k;
	private static int[][] better;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));

		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		connections = new LinkedList[n];
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int val = Integer.parseInt(read.nextToken());
			if (connections[a] == null)
				connections[a] = new LinkedList<int[]>();
			connections[a].add(new int[] { b, val });
			if (connections[b] == null)
				connections[b] = new LinkedList<int[]>();
			connections[b].add(new int[] { a, val });
		}
		better = new int[n][2];
		for (int i = 0; i < n; i++) {
			better[i][0] = -1;
			better[i][1] = -1;
		}
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, -1 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int prev = curr[1];
			for (int[] a : connections[curr[0]]) {
				if (a[0] != prev) {
					better[curr[0]][a[1]] = a[0];
					queue.add(new int[] { a[0], curr[0] });
				}
			}
		}

		System.out.println(dp(0, 0, -1, -1, 0)/2);
		in.close();
	}

	private static int dp(int l, int r, int prevL, int prevR, int differences) {

		int cnt = (differences == k) ? 1 : 0;
		if (better[l][0] != -1 && better[r][0] != -1) {
			cnt += dp(better[l][0], better[r][0], l, r, differences);
		}
		if (better[l][1] != -1 && better[r][1] != -1) {
			cnt += dp(better[l][1], better[r][1], l, r, differences);
		}
		if (differences != k) {
			if (better[l][0] != -1 && better[r][1] != -1) {
				cnt += dp(better[l][0], better[r][1], l, r, differences + 1);
			}
			if (better[l][1] != -1 && better[r][0] != -1) {
				cnt += dp(better[l][1], better[r][0], l, r, differences + 1);
			}
		}
		return cnt;
	}
}
