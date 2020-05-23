
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crosswords {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crosswords.in"));
		PrintWriter out = new PrintWriter(new File("crosswords.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		boolean[][] info = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			String a = in.readLine();
			for (int k = 0; k < n; k++) {
				info[i][k] = a.charAt(k) == '.';
			}
		}
		int total = 0;
		boolean[][] answer = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (info[i][k]) {
					if (k == 0 || !info[i][k - 1]) {
						if (k + 2 <= n - 1 && info[i][k + 1] && info[i][k + 2]) {
							answer[i][k] = true;

						}
					}
					if (i == 0 || !info[i - 1][k]) {
						if (i + 2 <= m - 1 && info[i + 1][k] && info[i + 2][k]) {
							answer[i][k] = true;
						}
					}
					if (answer[i][k]) {
						total++;
					}
				}
			}
		}
		out.println(total);
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (answer[i][k]) {
					out.println((i+1) + " " + (k+1));
				}
			}
		}
		out.close();
		in.close();
	}
}