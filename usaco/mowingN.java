
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mowingN {
	private static int[] haybales;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("mowing.in"));
		PrintWriter out = new PrintWriter(new File("mowing.out"));
		int lines = Integer.parseInt(in.readLine());
		int[][] info = new int[2002][2002];
		int x = 1001;
		int y = 1001;
		int time = 1;
		info[1001][1001] = 1;
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < lines; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String direction = read.nextToken();
			int changeX = 0;
			int changeY = 0;
			if (direction.equals("N")) {
				changeX = 0;
				changeY = 1;
			} else if (direction.equals("W")) {
				changeX = -1;
				changeY = 0;
			} else if (direction.equals("S")) {
				changeX = 0;
				changeY = -1;
			} else {
				changeX = 1;
				changeY = 0;
			}
			int steps = Integer.parseInt(read.nextToken());
			for (int k = 0; k < steps; k++) {
				x += changeX;
				y += changeY;
				time++;
				if (info[x][y] != 0) {
					max = Math.min(max, time - info[x][y]);
				}
				info[x][y] = time;
			}
		}
		if (max == Integer.MAX_VALUE) {
			out.println(-1);
		} else {
			out.println(max);
		}
		out.close();
		in.close();
	}

}