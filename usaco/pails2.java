
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new File("pails.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int X = Integer.parseInt(read.nextToken());
		int Y = Integer.parseInt(read.nextToken());
		int K = Integer.parseInt(read.nextToken());
		int M = Integer.parseInt(read.nextToken());
		boolean[][] info = new boolean[X + 1][Y + 1];
		boolean[][] done = new boolean[X + 1][Y + 1];

		info[0][0] = true;
		for (int i = 0; i < K; i++) {
			boolean[][] isNew = new boolean[X + 1][Y + 1];
			for (int first = 0; first <= X; first++) {
				for (int second = 0; second <= Y; second++) {
					if (isNew[first][second]) {
						continue;
					}
					if (info[first][second] && !done[first][second]) {
						done[first][second] = true;
						info[X][second] = true;
						isNew[X][second] = true;
						info[first][Y] = true;
						isNew[first][Y] = true;
						info[0][second] = true;
						isNew[0][second] = true;
						info[first][0] = true;
						isNew[first][0] = true;
						if (first + second > X) {
							info[X][second - (X - first)] = true;
							isNew[X][second - (X - first)] = true;
						} else {
							info[first + second][0] = true;
							isNew[first + second][0] = true;
						}
						if (first + second > Y) {
							info[first - (Y - second)][Y] = true;
							isNew[first - (Y - second)][Y] = true;
						} else {
							info[0][first + second] = true;
							isNew[0][first + second] = true;
						}
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int first = 0; first <= X; first++) {
			for (int second = 0; second <= Y; second++) {
				int sum = first + second;
				if (info[first][second]) {
					System.out.println(first + " " + second);
					min = Math.min(min, Math.abs(M - sum));
				}
			}
		}
		out.print(min);
		out.close();
		in.close();

	}

}