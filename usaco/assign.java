
import java.io.*;
import java.util.*;

public class assign {
	private static int[][] info;
	private static int cows;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("assign.in"));
		PrintWriter out = new PrintWriter(new FileWriter("assign.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		cows = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		info = new int[cows + 1][cows + 1];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			boolean same = read.nextToken().equals("S");
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			info[a][b] = same ? 1 : -1;
			info[b][a] = same ? 1 : -1;
		}
		int[] data = new int[cows + 1];
		data[1] = 1;
		out.println(3 * recursion(data, 2));
		out.close();
		in.close();
	}

	private static int recursion(int[] data, int index) {
		if (index == data.length) {
			return 1;
		}
		boolean[] possible = new boolean[4];
		Arrays.fill(possible, true);
		for (int i = 1; i < cows + 1; i++) {

			if (data[i] != 0) {
				if (info[i][index] == 1) {
					if (possible[data[i]]) {
						Arrays.fill(possible, false);
						possible[data[i]] = true;
					} else {
						Arrays.fill(possible, false);
					}

				} else if (info[i][index] == -1) {
					possible[data[i]] = false;
				}

			}

		}
		int sum = 0;
		for (int i = 1; i < 4; i++) {
			if (possible[i]) {
				int[] copy = Arrays.copyOf(data, data.length);
				copy[index] = i;
				sum += recursion(copy, index + 1);
			}
		}
		return sum;
	}
}
