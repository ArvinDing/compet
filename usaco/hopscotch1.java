
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class hopscotch1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new File("hopscotch.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(read.nextToken());
		int width = Integer.parseInt(read.nextToken());
		boolean[][] info = new boolean[height][width];
		for (int r = 0; r < height; r++) {
			String a = in.readLine();
			for (int c = 0; c < width; c++) {
				info[r][c] = a.charAt(c) == 'B';
			}
		}
		int[][] answer = new int[height][width];
		PriorityQueue<Position> queue = new PriorityQueue<Position>();
		answer[0][0] = 1;
		queue.add(new Position(0, 0));
		int previousR = -1;
		int previousC = -1;
		while (!queue.isEmpty()) {
			Position a = queue.poll();
			int r = a.r;
			int c = a.c;
			if (previousR == r && previousC == c)
				continue;
			previousR = r;
			previousC = c;
		
			for (int i = r + 1; i < height; i++) {
				for (int k = c + 1; k < width; k++) {
					if (info[r][c] != info[i][k]) {
						queue.offer(new Position(i, k));
						answer[i][k] += answer[r][c];
					}
				}
			}
		}
		out.println(answer[height - 1][width - 1]);
		in.close();
		out.close();
	}

	private static class Position implements Comparable<Position> {
		int r;
		int c;

		Position(int x, int y) {
			this.r = x;
			this.c = y;
		}

		@Override
		public int compareTo(Position o) {
			if (r == o.r) {
				return (c - o.c);
			}
			return (r - o.r);
		}

	}
}