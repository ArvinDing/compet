package camp;

import java.io.*;
import java.util.*;

public class palindromicPath {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new File("palpath.out"));
		int n = Integer.parseInt(in.readLine());
		char[][] info = new char[n][n];
		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			for (int k = 0; k < n; k++) {
				info[i][k] = line.charAt(k);
			}
		}
		long[][] old = new long[n][n];
		old[0][n - 1] = 1;
		for (int i = 1; i < n; i++) {
			long[][] curr = new long[n][n];
			for (int x = 0; x <= i && x < n; x++) {
				for (int x1 = n - 1; (n - 1) - x1 <= i && x1 >= 0; x1--) {
					int y = i - x;
					int y1 = (2 * (n - 1) - i) - x1;
					if (info[x][y] == info[x1][y1]) {
						if (x - 1 >= 0 && x1 + 1 < n)
							curr[x][x1] += old[x - 1][x1 + 1];
						if (x - 1 >= 0)
							curr[x][x1] += old[x - 1][x1];
						if (x1 + 1 < n)
							curr[x][x1] += old[x][x1 + 1];
						curr[x][x1] = (curr[x][x1] + old[x][x1]) % 1000000007;

					}
				}
			}
			for (int z = 0; z < n; z++) {
				for (int p = 0; p < n; p++) {
					old[z][p] = curr[z][p];
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += old[i][i];
			sum%=1000000007;
		}
		out.println(sum);
		out.close();
		in.close();

	}
}