
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class hopscotch {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new File("hopscotch.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int height = Integer.parseInt(read.nextToken());
		int width = Integer.parseInt(read.nextToken());
		int numbers = Integer.parseInt(read.nextToken());
		int[][] info = new int[height][width];
		for (int i = 0; i < height; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < width; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		long[][] dp = new long[height][width];
		dp[0][0] = 1;
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				int curr = info[i][k];
				long value = dp[i][k];
				value%=1000000007;
				if (info[i][k] != 0) {
					for (int a = i + 1; a < height; a++) {
						for (int b = k + 1; b < width; b++) {
							if (info[a][b] != curr) {
								dp[a][b] += value;
							}
						}
					}
				}
			}
		}

		out.println(dp[height - 1][width - 1]%1000000007);
		in.close();
		out.close();
	}

}