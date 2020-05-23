
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class nocross {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("nocross.out"));

		int N = Integer.parseInt(in.readLine());
		int[] road = new int[N];
		int[] road1 = new int[N];
		for (int i = 0; i < N; i++) {
			road[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < N; i++) {
			road1[i] = Integer.parseInt(in.readLine());
		}
		int[][] dp = new int[N][N];
		int curr = road[0];
		for (int i = 0; i < N; i++) {
			if (Math.abs(road1[i] - curr) <= 4)
				dp[0][i] = 1;
			else
				dp[0][i] = 0;
		}
		for (int i = 1; i < N; i++) {
			int max = 0;
			dp[i][0] = (Math.abs(road1[0] - road[i]) <= 4) ? 1 : 0;
			for (int k = 0; k < N - 1; k++) {
				max = Math.max(max, dp[i - 1][k]);
				dp[i][k + 1] = (Math.abs(road1[k + 1] - road[i]) <= 4) ? max + 1 : max;
				dp[i][k + 1] = Math.max(dp[i][k], dp[i][k + 1]);
				dp[i][k + 1] = Math.max(dp[i - 1][k + 1], dp[i][k + 1]);
			}

		}
		out.println(dp[N - 1][N - 1]);
		out.close();
		in.close();
	}

}
