
import java.io.*;
import java.util.*;

public class cowrun1 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowrun.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
		int cows = Integer.parseInt(in.readLine()) + 1;
		int[] info = new int[cows];
		info[0] = 0;
		for (int i = 1; i < cows; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(info);
		int startLoc = 0;
		for (int i = 0; i < cows; i++) {
			if (info[i] == 0) {
				startLoc = i;
				break;
			}
		}
		long[][][] dp = new long[cows][cows][2];
		boolean[][][] done = new boolean[cows][cows][2];
		done[startLoc][startLoc][0] = true;
		done[startLoc][startLoc][1] = true;
		for (int i = startLoc; i >= 0; i--) {
			for (int k = startLoc; k < cows; k++) {
				if (!done[i][k][0])
					dp[i][k][0] = Integer.MAX_VALUE;
				if (!done[i][k][1])
					dp[i][k][1] = Integer.MAX_VALUE;
	//			System.out.println(i + " " + k + " " + dp[i][k][0] + "|" + dp[i][k][1]);
				long multiplier = ((cows - 1) - (k - i));
				if (i != 0) {
					long smaller = (Math.min(dp[i][k][0] + multiplier * (info[i] - info[i - 1]),
							dp[i][k][1] + multiplier * (info[k] - info[i - 1])));
					if (dp[i - 1][k][0] == 0 || dp[i - 1][k][0] > smaller) {
						dp[i - 1][k][0] = smaller;
						done[i-1][k][0] = true;
					}
				}
				if (k != cows - 1) {
					long smaller = Math.min(dp[i][k][0] + multiplier * (info[k + 1] - info[i]),
							dp[i][k][1] + multiplier * (info[k + 1] - info[k]));
					if (dp[i][k + 1][1] == 0 || dp[i][k + 1][1] > smaller) {
						dp[i][k + 1][1] = smaller;
						done[i][k+1][1] = true;
					}
				}
			}
		}
		out.println(Math.min(dp[0][cows - 1][0], dp[0][cows - 1][1]));
		in.close();
		out.close();

	}

}