
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class DP_EditDistance {
	private static int[][] dp = new int[10001][10001];

	public static int editDist(String str1, String str2, int i, int j) {

		
		if (i == -1) {
			return j+1;
		}
		if (j == -1) {
			return i+1;
		}
		if (dp[i][j] != 0) {
			return dp[i][j];
		}
		if (str1.charAt(i) == str2.charAt(j))
			return editDist(str1, str2, i - 1, j - 1);
		int min = Math.min(editDist(str1, str2, i - 1, j) + 1, editDist(str1, str2, i, j - 1) + 1);
		min = Math.min(editDist(str1, str2, i - 1, j - 1) + 1, min);
		dp[i][j] = min;
		return min;
	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("input"))) {
			String str1 = in.readLine();
			String str2 = in.readLine();
			System.out.println(editDist(str1, str2, str1.length() - 1, str2.length() - 1));
		}
	}
}