
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj {

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
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<position> queue = new PriorityQueue<position>();
		queue.add(new position(0, 0, 0));

		while (!queue.isEmpty()) {
			position temp = queue.poll();
			int x = temp.x;
			int y = temp.y;
			int time = temp.time;
		
			visited[x][y] = true;
			if (x == N - 1 && y == N - 1) {
				out.println(time);
				break;
			}
			if (3 >= Math.abs((N - 1) - x) + Math.abs((N - 1) - y)) {
				if (Math.abs((N - 1) - x) + Math.abs((N - 1) - y) == 3) {
					queue.add(new position(N - 1, N - 1,
							time + Math.abs((N - 1) - x) * T + Math.abs((N - 1) - y) * T + grid[N - 1][N - 1]));

				} else {
					queue.add(new position(N - 1, N - 1, time + Math.abs((N - 1) - x) * T + Math.abs((N - 1) - y) * T));
				}
			}
			for (int i = -3; i <= 3; i++) {
				for (int k = -3; k <= 3; k++) {
					if (x + i < 0 || x + i >= N || y + k < 0 || y + k >= N) {
						continue;
					}
					if (Math.abs(i) + Math.abs(k) != 3) {
						continue;
					}
					if (visited[x + i][y + k]) {
						continue;
					}

					queue.add(new position(x + i, y + k, time + 3 * T + grid[x + i][y + k]));

				}
			}
			for (int i = -1; i <= 1; i++) {

				if (x + i == -1 || x + i == N) {
					continue;
				}

				if (visited[x + i][y]) {
					continue;
				}

				queue.add(new position(x + i, y, time + 3 * T + grid[x + i][y]));

			}
			for (int k = -1; k <= 1; k++) {
				if (y + k == -1 || y + k == N) {
					continue;
				}
				if (visited[x][y + k]) {
					continue;
				}
				queue.add(new position(x, y + k, time + 3 * T + grid[x][y + k]));

			}

		}

		in.close();
		out.close();

	}

	private static class position implements Comparable {
		int x;
		int y;
		int time;

		public position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;

		}

		public int compareTo(Object o) {

			return time - ((position) o).time;
		}
	}

}
