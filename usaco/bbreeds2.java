
import java.io.*;
import java.util.*;

public class bbreeds2 {

	private static char[] info;
	private static int[][] dp;
	private static int[] open;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("bbreeds.in"));
		PrintWriter out = new PrintWriter(new File("bbreeds.out"));
		info = in.readLine().toCharArray();
		dp = new int[info.length][info.length];
		open = new int[info.length + 1];
		for (int i = 0; i < info.length; i++) {
			open[i + 1] = open[i] + ((info[i] == '(') ? 1 : -1);
		}
		for (int i = 0; i < info.length; i++) {
			for (int k = 0; k < info.length; k++) {
				dp[i][k] = -1;
			}
		}
		out.println(recursion(0, 0));
		out.close();
		in.close();
	}

	private static int recursion(int i, int a) {

		if (i == info.length)
			return 1;
		if (dp[i][a] != -1)
			return dp[i][a];
		//System.out.println(i + " " + a);
		if (info[i] == '(') {
			dp[i][a] = (recursion(i + 1, a) + recursion(i + 1, a + 1)) % 2012;
			return dp[i][a];
		} else {
			int other = open[i] - a;
			int sum = 0;
			if (a - 1 >= 0) {
				sum += recursion(i + 1, a - 1);
			} 
			if (other - 1 >= 0) {
				sum += recursion(i + 1, a);
			} 
			
			dp[i][a] = sum % 2012;
			return dp[i][a];
		}

	}
}
