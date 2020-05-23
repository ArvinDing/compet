
import java.io.*;
import java.util.*;

public class irrigation2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("irrigation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int least = Integer.parseInt(read.nextToken());
		int[][] info = new int[n][2];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>();
		int[] min = new int[n];
		Arrays.fill(min, Integer.MAX_VALUE);
		int answer = 0;
		outer: {
			for (int i = 0; i < n; i++) {
				int minCurr = Integer.MAX_VALUE;
				int index = 0;
				for (int k = 0; k < n; k++) {
					if (min[k] == 0)
						continue;
					if (min[k] < minCurr) {
						minCurr = min[k];
						index = k;
					}
				}
				if (minCurr == Integer.MAX_VALUE) {
					if (min[index] == 0) {
						out.println(-1);
						break outer;
					}
					minCurr = 0;
				}
				min[index] = 0;
				answer += minCurr;
				for (int k = 0; k < n; k++) {
					if (min[k] == 0)
						continue;
					int cost = ((info[k][0] - info[index][0]) * (info[k][0] - info[index][0]))
							+ ((info[k][1] - info[index][1]) * (info[k][1] - info[index][1]));
					if (cost >= least) {
						min[k] = Math.min(min[k], cost);
					}
				}

			}
			out.print(answer);
		}
		out.close();
		in.close();
	}

}