
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new FileWriter("visitfj.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int cost = Integer.parseInt(read.nextToken());
		int[][][] dp = new int[n][n][3];
		int[][] info = new int[n][n];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < n; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		queue.add(new int[] { 0, 0, 0, 0 });
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int y = curr[0];
			int x = curr[1];
			int currCost = curr[2];
			int turn = curr[3];
			if (turn == 3) {
				currCost += info[y][x];
				turn = 0;
			}
			if (dp[y][x][turn] != 0)
				continue;
			dp[y][x][turn] = currCost;
			if (y + 1 < n && dp[y + 1][x][(turn + 1) % 3] == 0) {
				queue.add(new int[] { y + 1, x, currCost+cost, turn + 1 });
			}
			if (x + 1 < n && dp[y][x + 1][(turn + 1) % 3] == 0) {
				queue.add(new int[] { y, x + 1, currCost+cost, turn + 1 });
			}
			if (x - 1 >= 0 && dp[y][x - 1][(turn + 1) % 3] == 0) {
				queue.add(new int[] { y, x - 1, currCost+cost, turn + 1 });
			}
			if (y - 1 >= 0 && dp[y - 1][x][(turn + 1) % 3] == 0) {
				queue.add(new int[] { y - 1, x, currCost+cost, turn + 1 });
			}
		}
		int min = Integer.MAX_VALUE;
		for (int a : dp[n - 1][n - 1]) {
			min = Math.min(a, min);
		}
		out.println(min);
		in.close();
		out.close();
	}

}
