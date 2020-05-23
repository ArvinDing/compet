
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class hshoe {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hshoe.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hshoe.out")));
		int dimensions = Integer.parseInt(in.readLine());
		boolean[][] info = new boolean[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			String b = in.readLine();
			for (int k = 0; k < dimensions; k++) {
				info[i][k] = b.charAt(k) == '(';
			}
		}
		LinkedList<position> queue = new LinkedList<position>();
		int max = 0;
		if (info[0][0] == false) {
			out.println(0);
			out.close();
			in.close();
		} else {
			boolean[][] temp = new boolean[dimensions][dimensions];
			temp[0][0] = true;
			queue.add(new position(0, 0, temp, 1, 0));
			while (!queue.isEmpty()) {

				position thing = queue.poll();
				int x = thing.x;
				int y = thing.y;

				boolean[][] done = thing.done;
				int close = thing.close;
				int open = thing.open;
				System.out.println(x + " " + y + "--- " + open + " " + close);
				boolean where = false;
				for (int i = -1; i <= 1; i++) {
					for (int k = -1; k <= 1; k++) {

						if (i != 0 && k != 0)
							continue;
						if (i == 0 && k == 0)
							continue;
						if (x + i >= 0 && x + i < dimensions && y + k >= 0 && y + k < dimensions) {
							if (done[x + i][y + k])
								continue;

							if (close != 0 && info[x + i][y + k]) {
								continue;
							}

							boolean[][] copy = new boolean[done.length][done[0].length];
							for (int z = 0; z < done.length; z++) {
								for (int a = 0; a < done[0].length; a++) {
									copy[z][a] = done[z][a];
								}
							}
							copy[x + i][y + k] = true;
							where = true;
							if (info[x + i][y + k]) {
								queue.add(new position(x + i, y + k, copy, open + 1, close));
							} else {
								queue.add(new position(x + i, y + k, copy, open, close + 1));
							}
						}
					}
				}
				
					if (open == close) {
						max = Math.max(max, open + close);
					}
				

			}
			out.println(max);
			in.close();
			out.close();
		}
	}

	public static class position {
		int x;
		int y;
		boolean[][] done;
		int open;
		int close;

		position(int x, int y, boolean[][] done, int open, int close) {
			this.x = x;
			this.y = y;
			this.done = done;
			this.open = open;
			this.close = close;
		}
	}
}