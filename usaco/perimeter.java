
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class perimeter {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new File("perimeter.out"));
		boolean[][] info = new boolean[10001][10001];
		int lines = Integer.parseInt(in.readLine());
		int minX = Integer.MAX_VALUE;
		int minXy = 0;
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			if (x < minX) {
				minX = x;
				minXy = y;

			}

			info[x][y] = true;

		}

		int[][] direction = new int[4][2];
		direction[0] = new int[] { 0, 1 };
		direction[1] = new int[] { 1, 0 };
		direction[2] = new int[] { 0, -1 };
		direction[3] = new int[] { -1, 0 };
		int x = minX - 1;
		int y = minXy;
		int direct = 0;
		int sides = 0;
		while (true) {
			if (sides != 0 && x == minX - 1 && y == minXy) {
				break;
			}
			if (info[x + direction[(direct + 1) % 4][0]][y + direction[(direct + 1) % 4][1]]) {
				sides++;
			}
			if (!info[x + direction[(direct + 1) % 4][0]][y + direction[(direct + 1) % 4][1]]) {
				direct++;
				direct %= 4;
			} else if (info[x + direction[direct][0]][y + direction[direct][1]]) {
				direct--;
				direct = (direct + 4) % 4;
				continue;
			}

			x += direction[direct][0];
			y += direction[direct][1];

		}
		out.println(sides);
		in.close();
		out.close();
	}

}