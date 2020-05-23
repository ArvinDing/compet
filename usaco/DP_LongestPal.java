import java.io.BufferedReader;
import java.io.IOException;

public class DP_LongestPal {
	private static int longestPal(String input) {
		boolean[][] SE = new boolean[input.length()][input.length()];
		char[] info = input.toCharArray();
	
		int max = 1;
		for (int i = input.length() - 1; i >= 0; i--) {
			SE[i][i] = true;
			for (int k = i + 1; k < input.length(); k++) {
				if (info[i] == info[k] && ((i + 1 > k - 1) || SE[i + 1][k - 1])) {
					SE[i][k] = true;
				} else {
					SE[i][k] = false;
				}

				if (SE[i][k]) {
					max = Math.max(max, k - i + 1);
				}

			}
		}
		return max;

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new java.io.FileReader(
				"input"))) {/*
							 * new java.io.InputStreamReader(System.in))) {//
							 */
			System.out.println(longestPal(in.readLine()));
		}
	}

}