package camp;

import java.io.*;
import java.util.*;

public class art {
	private static int[][] bound;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art.in"));
		PrintWriter out = new PrintWriter(new File("art.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][n];
		bound = new int[n * n][4];
		for (int i = 0; i < n * n; i++) {
			bound[i][1] = Integer.MAX_VALUE;
			bound[i][3] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < n; k++) {
				info[i][k] = Integer.parseInt(read.nextToken() + "") - 1;
				if (info[i][k] == -1)
					continue;
				bound[info[i][k]][0] = Math.max(bound[info[i][k]][0], k);
				bound[info[i][k]][1] = Math.min(bound[info[i][k]][1], k);
				bound[info[i][k]][2] = Math.max(bound[info[i][k]][2], i);
				bound[info[i][k]][3] = Math.min(bound[info[i][k]][3], i);
			}
		}
		int colU=0;
		boolean[] onTop = new boolean[n * n];
		LinkedList<int[]> yQ = new LinkedList<int[]>();
		for (int i = 0; i < n * n; i++) {
			if (bound[i][1] != Integer.MAX_VALUE) {
				colU++;
				yQ.add(new int[] { bound[i][3], i });
				yQ.add(new int[] { 1 + bound[i][2], i });
			}
		}
		Collections.sort(yQ, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[0] - arg1[0];
			}
		});
		int ans = n * n;
		boolean[] done = new boolean[n * n];
		int[] changes = new int[n];
		for (int i = 0; i < n; i++) {

			while (!yQ.isEmpty() && yQ.peek()[0] == i) {
				int imp = yQ.poll()[1];
				if (done[imp]) {
					changes[bound[imp][1]]--;
					if (bound[imp][0] + 1 < n)
						changes[bound[imp][0] + 1]++;
				} else {
					changes[bound[imp][1]]++;
					if (bound[imp][0] + 1 < n)
						changes[bound[imp][0] + 1]--;
					done[imp] = true;
				}

			}
			int overlapping = 0;  
			for (int k = 0; k < n; k++) {
				overlapping += changes[k];
				if (overlapping > 1) {
					if (!onTop[info[i][k]])
						ans--;
					onTop[info[i][k]] = true;
				}
			}
		}
		if (colU==1)
			ans--;
		out.println(ans);
		out.close();
		in.close();
	}
}
