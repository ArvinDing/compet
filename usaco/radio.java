import java.io.*;
import java.util.*;

public class radio {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("radio.in"));
		PrintWriter out = new PrintWriter(new File("radio.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int john = Integer.parseInt(read.nextToken());
		int bessie = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int johnX = Integer.parseInt(read.nextToken());
		int johnY = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int bessieX = Integer.parseInt(read.nextToken());
		int bessieY = Integer.parseInt(read.nextToken());
		char[] direction = in.readLine().toCharArray();
		int[][] johnInfo = prepare(john, direction, johnX, johnY);
		direction = in.readLine().toCharArray();
		int[][] bessieInfo = prepare(bessie, direction, bessieX, bessieY);
		long[][] dp = new long[john + 1][bessie + 1];
		long previous = 0;
		for (int i = 1; i < john + 1; i++) {
			dp[i][0] = previous + (johnInfo[i][0] - bessieInfo[0][0]) * (johnInfo[i][0] - bessieInfo[0][0])
					+ (johnInfo[i][1] - bessieInfo[0][1]) * (johnInfo[i][1] - bessieInfo[0][1]);
			previous = dp[i][0];
		}
		previous = 0;
		for (int i = 1; i < bessie + 1; i++) {
			dp[0][i] = previous + (johnInfo[0][0] - bessieInfo[i][0]) * (johnInfo[0][0] - bessieInfo[i][0])
					+ (johnInfo[0][1] - bessieInfo[i][1]) * (johnInfo[0][1] - bessieInfo[i][1]);
			previous = dp[0][i];
		}
		for (int i = 1; i < john + 1; i++) {
			for (int k = 1; k < bessie + 1; k++) {
				long old = Math.min(dp[i - 1][k], dp[i][k - 1]);
				old = Math.min(old, dp[i - 1][k - 1]);
				dp[i][k] = old + (johnInfo[i][0] - bessieInfo[k][0]) * (johnInfo[i][0] - bessieInfo[k][0])
						+ (johnInfo[i][1] - bessieInfo[k][1]) * (johnInfo[i][1] - bessieInfo[k][1]);
			}
		}
		out.println(dp[john][bessie]);
		out.close();
		in.close();
	}

	private static int[][] prepare(int cnt, char[] direction, int x, int y) {
		int[][] answer = new int[cnt + 1][2];
		answer[0][0] = x;
		answer[0][1] = y;
		for (int i = 0; i < cnt; i++) {
			if (direction[i] == 'N') {
				y++;
			} else if (direction[i] == 'W') {
				x--;
			} else if (direction[i] == 'S') {
				y--;
			} else {
				x++;
			}
			answer[i + 1][0] = x;
			answer[i + 1][1] = y;
		}
		return answer;
	}
}
