import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class lightson2 {
	private static int[][] dp;
	private static int rooms;
	private static List[][] info;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lightson.out"));
		StringTokenizer a = new StringTokenizer(in.readLine());
		rooms = Integer.parseInt(a.nextToken());
		int lines = Integer.parseInt(a.nextToken());
		info = new List[rooms + 1][rooms + 1];
		dp = new int[rooms + 1][rooms + 1];
		for (int i = 0; i < rooms + 1; i++) {
			for (int k = 0; k < rooms + 1; k++) {
				info[i][k] = new ArrayList<Position>();
			}
		}
		for (int i = 0; i < lines; i++) {
			a = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(a.nextToken());
			int y = Integer.parseInt(a.nextToken());
			int x1 = Integer.parseInt(a.nextToken());
			int y1 = Integer.parseInt(a.nextToken());
			info[x][y].add(new Position(x1, y1));
		}
		boolean[][] save = new boolean[rooms + 1][rooms + 1];
		save[1][1] = true;
		boolean[][] done = new boolean[rooms + 1][rooms + 1];
		out.println(answer(1, 1, save, done, 0));
		in.close();
		out.close();
	}

	private static int answer(int x, int y, boolean[][] save, boolean[][] done, int curr) {

		for (int i = 0; i < info[x][y].size(); i++) {
			Position j = (Position) info[x][y].get(i);
			if (!save[j.x][j.y]) {
				curr++;
				save[j.x][j.y] = true;
			}
		}
		System.out.println(x + " " + y);
		if (dp[x][y] != 0)
			return dp[x][y];
		int max = 0;
		done[x][y] = true;
		if (x < rooms && save[x + 1][y] && !done[x + 1][y]) {
			max = Math.max(max, answer(x + 1, y, save, done, curr));
		}
		if (x > 1 && save[x - 1][y] && !done[x - 1][y]) {
			max = Math.max(max, answer(x - 1, y, save, done, curr));
		}
		if (y > 1 && save[x][y - 1] && !done[x][y - 1]) {
			max = Math.max(max, answer(x, y - 1, save, done, curr));
		}
		if (y < rooms && save[x][y + 1] && !done[x][y + 1]) {
			max = Math.max(max, answer(x, y + 1, save, done, curr));
		}

		dp[x][y] = max + curr;
		return max + curr;
	}

	public static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
