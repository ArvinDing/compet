
import java.io.BufferedReader;
import java.io.IOException;

public class DP_LSPal {
	private static int[][] dp;

	public static int longestLength(String cnt, int start, int end) {

		if (dp[start][end] != 0)
			return dp[start][end];

		if (start == end)
			return 1;
		
		if (start > end)
			return 0;

		int answer = 0;

		if (cnt.charAt(start) == cnt.charAt(end))
			answer = longestLength(cnt, start + 1, end - 1) + 2;
		else
			answer = Math.max(longestLength(cnt, start + 1, end), longestLength(cnt, start, end - 1));

		dp[start][end] = answer;
		return answer;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(// new
													// java.io.FileReader("input.txt")))
													// {/*
				new java.io.InputStreamReader(System.in))) {// */
			String cnt = in.readLine();
			dp = new int[cnt.length()][cnt.length()];
			System.out.println(longestLength(cnt, 0, cnt.length() - 1));
		}
	}
}