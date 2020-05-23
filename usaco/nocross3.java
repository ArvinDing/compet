
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class nocross3 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("nocross.out"));
		int N = Integer.parseInt(in.readLine());
		int[][] info = new int[N][2];
		for (int i = 0; i < N; i++) {
			info[i][0] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < N; i++) {
			info[i][1] = Integer.parseInt(in.readLine());
		}
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int k = 1; k < N + 1; k++) {
				dp[i][k] = Math.max(dp[i - 1][k], dp[i][k-1]);
				if (Math.abs(info[i - 1][0] - info[k - 1][1]) <= 4) {
					dp[i][k] = Math.max(dp[i][k], dp[i - 1][k - 1] + 1);

				}
			}
		}
		out.println(dp[N][N]);
		out.close();
		in.close();
	}

}
