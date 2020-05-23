
import java.io.*;
import java.util.*;

public class abbreviate {

	private static boolean abbreviation(String a, String b) {
		boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];
		boolean bad = false;
		for (int z = 1; z <= a.length(); z++) {
			if (!bad)
				bad = (Character.isUpperCase(a.charAt(z-1)));
			dp[z][0] = !bad;

		}
		dp[0][0]=true;
		for (int i = 0; i <= a.length(); i++) {
			for (int k = 1; k <= b.length(); k++) {
			
				if (i < k) {
					dp[i][k] = false;
					continue;
				}
				char aCurr = a.charAt(i - 1);
				char bCurr = b.charAt(k - 1);
				if (Character.toUpperCase(aCurr) == Character.toUpperCase(bCurr)) {
					if (Character.isUpperCase(aCurr)) {
						dp[i][k] = dp[i - 1][k - 1];
					} else {
						dp[i][k] = (dp[i - 1][k - 1] || dp[i - 1][k]);

					}
				} else {
					if (Character.isUpperCase(aCurr))
						dp[i][k] = false;
					else
						dp[i][k] = dp[i - 1][k];
				}
			}
		}
		return dp[a.length() ][b.length() ];

	}

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			String a = in.next();
			String b = in.next();
			System.out.println(abbreviation(a, b) ? "YES" : "NO");
		}
		in.close();
	}
}
