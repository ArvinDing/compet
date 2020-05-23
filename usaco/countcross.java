
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class countcross {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new File("countcross.out"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int dimensions = Integer.parseInt(line.nextToken());
		int cowLine = Integer.parseInt(line.nextToken());
		int roads = Integer.parseInt(line.nextToken());
		boolean[][] fence = new boolean[2 * (dimensions + 1)][2 * (dimensions + 1)];
		for (int i = 0; i < roads; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(read.nextToken());
			int y1 = Integer.parseInt(read.nextToken());
			int x2 = Integer.parseInt(read.nextToken());
			int y2 = Integer.parseInt(read.nextToken());
			fence[y1 + y2 - 1][x1 + x2 - 1] = true;
		}
		boolean[][] cows = new boolean[2 * (dimensions + 1)][2 * (dimensions + 1)];
		for (int i = 0; i < cowLine; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			cows[2 * y - 1][2 * x - 1] = true;
		}
		// for (int i = 2 * (dimensions + 1) - 1; i >= 0; i--) {
		//
		// for (int k = 0; k < 2 * (dimensions + 1); k++) {
		// if (fence[i][k])
		// System.out.print('F');
		// else if (cows[i][k])
		// System.out.print('C');
		// else
		// System.out.print('.');
		// }
		// System.out.println();
		// }

		boolean done[][] = new boolean[2 * (dimensions + 1)][2 * (dimensions + 1)];
		int answer = 0;
		int cow = 0;
		for (int i = 0; i < 2 * (dimensions + 1); i++) {
			for (int k = 0; k < 2 * (dimensions + 1); k++) {
				if (cows[i][k] && !done[i][k]) {
					cow = 0;
					LinkedList<Integer> x = new LinkedList<Integer>();
					LinkedList<Integer> y = new LinkedList<Integer>();
					y.add(i);
					x.add(k);
					done[i][k] = true;
					while (!x.isEmpty()) {
						int currX = x.poll();
						int currY = y.poll();
						if (cows[currY][currX])
							cow++;
						if (currX + 2 < 2 * (dimensions) && !fence[currY][currX + 1] && !done[currY][currX + 2]) {
							x.add(currX + 2);
							y.add(currY);
							done[currY][currX + 2] = true;
						}
						if (currY + 2 < 2 * (dimensions) && !fence[currY + 1][currX] && !done[currY + 2][currX]) {
							x.add(currX);
							y.add(currY + 2);
							done[currY + 2][currX] = true;
						}
						if (currX - 2 >= 0 && !fence[currY][currX - 1] && !done[currY][currX - 2]) {
							x.add(currX - 2);
							y.add(currY);
							done[currY][currX - 2] = true;
						}
						if (currY - 2 >= 0 && !fence[currY - 1][currX] && !done[currY - 2][currX]) {
							x.add(currX);
							y.add(currY - 2);
							done[currY - 2][currX] = true;
						}
					}
					answer += cow * (cowLine - cow);
				}
			}
		}
		out.println(answer / 2);
		out.close();
		in.close();
	}
}