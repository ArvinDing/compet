
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj1 {

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
		boolean[][][] visited = new boolean[N][N][3];
		PriorityQueue<position> queue = new PriorityQueue<position>();
		queue.add(new position(0, 0, 0, 0));
		
		while (!queue.isEmpty()) {
			position temp = queue.poll();
			int x = temp.x;
			int y = temp.y;

			
			int time = temp.time;

			int state = temp.state;
			visited[x][y][state] = true;
			if (x == N - 1 && y == N - 1) {
				out.println(time);
				break;
			}
			for (int i = -1; i <= 1; i++) {

				if (x + i == -1 || x + i == N) {
					continue;
				}

				if (visited[x + i][y][state]) {
					continue;
				}
				if (state == 2) {
					queue.add(new position(x + i, y, time + T + grid[x + i][y], 0));
				} else {

					queue.add(new position(x + i, y, time + T, state + 1));
				}

			}
			for (int k = -1; k <= 1; k++) {
				if (y + k == -1 || y + k == N) {
					continue;
				}
				if (visited[x][y + k][state]) {
					continue;
				}
				if (state == 2) {
					queue.add(new position(x, y + k, time + T + grid[x][y + k], 0));
				} else {

					queue.add(new position(x, y + k, time + T, state + 1));
				}
			}
		}
		
		in.close();
		out.close();

	}

	private static class position implements Comparable {
		int x;
		int y;
		int time;
		int state;

		public position(int x, int y, int time, int state) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.state = state;
		}

		public int compareTo(Object o) {

			return time - ((position) o).time;
		}
	}

}
