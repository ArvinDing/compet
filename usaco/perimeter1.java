
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class perimeter1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new File("perimeter.out"));

		int lines = Integer.parseInt(in.readLine());

		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = 0;
		int maxY = 0;
		int[][] input = new int[lines][2];
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			minX = Math.min(x, minX);
			minY = Math.min(y, minY);
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
			input[i] = new int[] { x, y };

		}
		int minXy = 0;
		int newMinX = Integer.MAX_VALUE;
		boolean[][] info = new boolean[maxX - minX + 3][maxY - minY + 3];
		for (int i = 0; i < lines; i++) {
			int x = input[i][0] - minX + 1;
			int y = input[i][1] - minY + 1;
			if (x < newMinX) {
				newMinX = x;
				minXy = y;

			}
			info[x][y] = true;
		}
//		for(int i=0;i<info.length;i++){
//			for(int k=0;k<info[0].length;k++){
//				System.out.print(info[i][k]?"X":".");
//			}
//			System.out.println();
//		}
		int[][] direction = new int[4][2];
		direction[0] = new int[] { 0, 1 };
		direction[1] = new int[] { 1, 0 };
		direction[2] = new int[] { 0, -1 };
		direction[3] = new int[] { -1, 0 };
		int x = newMinX - 1;
		int y = minXy;
		int direct = 0;
		int sides = 0;
		while (true) {
		
			if (sides != 0 && x == newMinX - 1 && y == minXy) {
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