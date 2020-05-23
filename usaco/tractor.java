
import java.io.*;
import java.util.*;

public class tractor {
	private static int[][] info;
	private static int lines;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("tractor.in"));
		PrintWriter out = new PrintWriter(new FileWriter("tractor.out"));
		lines = Integer.parseInt(in.readLine());
		info = new int[lines][lines];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < lines; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}

		TreeSet<Integer> possible = new TreeSet<Integer>();
		for (int i = 0; i < lines; i++) {
			for (int k = 0; k < lines; k++) {
				if (i != 0)
					possible.add(Math.abs(info[i][k] - info[i - 1][k]));
				if (k != 0)
					possible.add(Math.abs(info[i][k] - info[i][k - 1]));
			}
		}
		Object[] easier = possible.toArray();
		int small = 0;
		int big = easier.length - 1;
		if (test((int) easier[0])) {
			out.println(easier[0]);
			in.close();
			out.close();
		} else {
			while (big > small + 1) {
				System.out.println(small + " " + big);

				int mid = (big + small) / 2;
				if (test((int) easier[mid])) {
					big = mid;
				} else {
					small = mid;
				}
			}
			out.println(easier[big]);
			in.close();
			out.close();
		}
	}

	private static boolean test(int tractorCost) {
		int best = 0;
		int previous = 0;
		boolean[][] done = new boolean[lines][lines];
		for (int i = 0; i < lines; i++) {
			for (int k = 0; k < lines; k++) {
				if (!done[i][k]) {
					LinkedList<Integer> x = new LinkedList<Integer>();
					LinkedList<Integer> y = new LinkedList<Integer>();
					x.add(i);
					y.add(k);
					done[i][k] = true;
					int curr = 0;
					while (!x.isEmpty()) {
						int currX = x.poll();
						int currY = y.poll();
						curr++;
						if (currX - 1 >= 0 && !done[currX - 1][currY]
								&& Math.abs(info[currX][currY] - info[currX - 1][currY]) <= tractorCost) {
							x.add(currX - 1);
							y.add(currY);
							done[currX - 1][currY] = true;
						}
						if (currY - 1 >= 0 && !done[currX][currY - 1]
								&& Math.abs(info[currX][currY] - info[currX][currY - 1]) <= tractorCost) {
							x.add(currX);
							y.add(currY - 1);
							done[currX][currY - 1] = true;
						}
						if (currX + 1 < lines && !done[currX + 1][currY]
								&& Math.abs(info[currX][currY] - info[currX + 1][currY]) <= tractorCost) {
							x.add(currX + 1);
							y.add(currY);
							done[currX + 1][currY] = true;
						}
						if (currY + 1 < lines && !done[currX][currY + 1]
								&& Math.abs(info[currX][currY] - info[currX][currY + 1]) <= tractorCost) {
							x.add(currX);
							y.add(currY + 1);
							done[currX][currY + 1] = true;
						}
					}
					best = Math.max(curr , best);
		
				}
			}
		}
		return best >= (lines * lines / 2);
	}
}
