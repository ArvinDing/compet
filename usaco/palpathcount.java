
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;

public class palpathcount {
	private static char[][] info;
	private static int square;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new File("palpath.out"));

		square = Integer.parseInt(in.readLine());
		info = new char[square][square];
		for (int i = 0; i < square; i++) {
			String a = in.readLine();
			for (int k = 0; k < square; k++) {
				info[i][k] = a.charAt(k);
			}
		}
		int cnt = 0;
		HashSet<String>[][] done = ans();
		info = mirrorShape(info);
		HashSet<String>[][] done1 = ans();
		HashSet<String> answer = new HashSet<String>();
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				if (i + k == square - 1) {
					for (String a : done[i][k]) {
						if (done1[i][k].contains(a))
							answer.add(a);

					}
				}
			}
		}
		out.println(answer.size());
		in.close();
		out.close();
	}

	private static char[][] mirrorShape(char[][] info) {
		char[][] copy = new char[info.length][info[0].length];
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info[0].length; k++) {
				copy[k][i] = info[info.length - i - 1][info[0].length - k - 1];
			}
		}
		return copy;
	}

	private static HashSet<String>[][] ans() {
		HashSet<String>[][] done = new HashSet[square][square];
		LinkedList<position> queue = new LinkedList<position>();
		for (int i = 0; i < square; i++) {
			for (int k = 0; k < square; k++) {
				done[i][k] = new HashSet<String>();
			}
		}
		queue.add(new position(0, 0, "" + info[0][0]));
		while (!queue.isEmpty()) {
			position a = queue.poll();
			String path = a.path;
			int x = a.x;
			int y = a.y;
			done[x][y].add(path);
			if (x + y < square - 1) {
				queue.add(new position(x, y + 1, path + info[x][y + 1]));
				queue.add(new position(x + 1, y, path + info[x + 1][y]));
			}
		}
		return done;
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