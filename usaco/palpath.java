
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class palpath {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new File("palpath.out"));
		LinkedList<position> queue = new LinkedList<position>();

		int square = Integer.parseInt(in.readLine());
		char[][] info = new char[square][square];
		for (int i = 0; i < square; i++) {
			String a = in.readLine();
			for (int k = 0; k < square; k++) {
				info[i][k] = a.charAt(k);
			}
		}
		int palindromeLen = square + square - 1;
		int mid = 1 + (palindromeLen / 2);
		HashSet<String> solution = new HashSet<String>();
		HashSet<String>[][] done = new HashSet[square][square];
		for (int i = 0; i < square; i++) {
			for (int k = 0; k < square; k++) {
				done[i][k] = new HashSet<String>();
			}
		}
		queue.add(new position(0, 0, "" + info[0][0]));
		while (!queue.isEmpty()) {
			position curr = queue.poll();
			int x = curr.x;
			int y = curr.y;

			String path = curr.path;
			int oldlen = done[x][y].size();
			done[x][y].add(path);
			if (done[x][y].size() == oldlen) {
				continue;
			}
//			System.out.println(x + " " + y + " " + path);

			if (y == square - 1 && x == square - 1) {
				solution.add(path);
			}
			if (x + 1 < square) {
				if (path.length() + 1 > mid) {
					if (path.charAt(palindromeLen - (path.length() + 1)) == info[y][x + 1]) {
						queue.add(new position(x + 1, y, path + info[y][x + 1]));
					}
				} else {
					queue.add(new position(x + 1, y, path + info[y][x + 1]));
				}
			}
			if (y + 1 < square) {
				if (path.length() + 1 > mid) {
					if (path.charAt(palindromeLen - (path.length() + 1)) == info[y + 1][x]) {
						queue.add(new position(x, y + 1, path + info[y + 1][x]));
					}
				} else {
					queue.add(new position(x, y + 1, path + info[y + 1][x]));
				}

			}

		}
		out.println(solution.size());
		in.close();
		out.close();
	}

	public static class position {
		int x;
		int y;
		String path;

		public position(int x, int y, String path) {
			this.x = x;
			this.y = y;
			this.path = path;
		}

	}
}