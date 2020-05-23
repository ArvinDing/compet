
import java.io.*;
import java.util.*;

public class hps1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int lines = Integer.parseInt(read.nextToken());
		int mChanges = Integer.parseInt(read.nextToken());
		int[] info = new int[lines];
		for (int i = 0; i < lines; i++) {
			String curr = in.readLine();
			if (curr.equals("P")) {
				info[i] = 0;
			} else if (curr.equals("H")) {
				info[i] = 1;
			} else {
				info[i] = 2;

			}
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		queue.add(new int[] { 0, 0, 0, 0 });
		queue.add(new int[] { 0, 0, 0, 1 });
		queue.add(new int[] { 0, 0, 0, 2 });
		int[][][] dp = new int[lines][mChanges + 1][3];
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (curr[1] == lines) {
				out.println(lines - curr[0]);
				break;
			}
			if (dp[curr[1]][curr[2]][curr[3]] != 0)
				continue;
			dp[curr[1]][curr[2]][curr[3]] = curr[0];
			if (curr[3] != info[curr[1]]) {
				curr[0]++;
			}
			queue.add(new int[] { curr[0], curr[1] + 1, curr[2], curr[3] });
			if (curr[2] != mChanges) {
				queue.add(new int[] { curr[0], curr[1] + 1, curr[2] + 1, (curr[3] + 1) % 3 });
				queue.add(new int[] { curr[0], curr[1] + 1, curr[2] + 1, (curr[3] + 2) % 3 });
			}
			
		}

		in.close();
		out.close();
	}

}