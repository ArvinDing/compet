
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfjR {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new FileWriter("visitfj.out"));
		StringTokenizer a = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(a.nextToken());
		int T = Integer.parseInt(a.nextToken());
		int[][] grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer b = new StringTokenizer(in.readLine());
			for (int k = 0; k < N; k++) {
				grid[i][k] = Integer.parseInt(b.nextToken());
			}

		}
		boolean[][][] done = new boolean[N][N][3];
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		queue.add(new int[] { 0, 0, 0, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cost = curr[0];
			int i = curr[1];
			int k = curr[2];
			int threeCyc = curr[3];
			if (done[i][k][threeCyc])
				continue;
			done[i][k][threeCyc] = true;
			if (i == N - 1 && k == N - 1) {
				out.println(cost);
				break;
			}
			for (int iAdd = -1; iAdd <= 1; iAdd++) {
				for (int kAdd = -1; kAdd <= 1; kAdd++) {
					if (iAdd == 0 ^ kAdd == 0) {
						if (0 <= i + iAdd && i + iAdd < N && 0 <= k + kAdd && k + kAdd < N)
							if (threeCyc == 2)
								queue.add(new int[] { T + cost + grid[i + iAdd][k + kAdd], i + iAdd, k + kAdd, 0 });
							else
								queue.add(new int[] { T + cost, i + iAdd, k + kAdd, threeCyc + 1 });
					}
				}
			}
		}
		out.close();
		in.close();
	}
}
