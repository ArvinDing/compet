import java.io.*;
import java.util.*;

public class minTrip {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int pastures = Integer.parseInt(read.nextToken());
		int colors = Integer.parseInt(read.nextToken());
		int queries = Integer.parseInt(read.nextToken());
		int[] cInfo = new int[pastures];

		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < pastures; i++) {
			cInfo[i] = Integer.parseInt(read.nextToken());
		}
		int[][] info = new int[pastures][pastures];
		int[][] answer = new int[pastures][colors + 1];
		boolean[][] done = new boolean[pastures][colors + 1];
		for (int i = 0; i < pastures; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < pastures; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[3] - arg1[3];
			}

		});

		queue.add(new int[] { 0, 1, -1, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int position = curr[0];
			int color = curr[1];
			int previous = curr[2];
			int maxEdge = curr[3];
			if (color > colors) {
				continue;
			}
			if (done[position][color])
				continue;
			done[position][color] = true;
			answer[position][color] = maxEdge;
			for (int i = 0; i < pastures; i++) {
				if (i != position && i != previous) {
					queue.add(new int[] { i, color + ((cInfo[position] == cInfo[i]) ? 0 : 1), position,
							Math.max(maxEdge, info[position][i]) });
				}
			}
		}
		for (int i = 0; i < queries; i++) {
			read = new StringTokenizer(in.readLine());
			int position = Integer.parseInt(read.nextToken()) - 1;
			int color = Integer.parseInt(read.nextToken());
			int min = Integer.MAX_VALUE;
			for (int k = color; k >= 0; k--) {
				if (done[position][k]) {
					min = Math.min(min, answer[position][k]);
				}
			}
			if (min == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else
				System.out.println(min);
		}
		in.close();

	}
}
