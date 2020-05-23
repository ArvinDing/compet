import java.io.*;
import java.util.*;

public class alphacode {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (String a = in.readLine(); a.charAt(0) != '0'; a = in.readLine()) {
			int length = a.length();
			char[] info = a.toCharArray();
			int[] dp = new int[length + 1];
			dp[0] = 1;
			for (int i = 0; i < length; i++) {
				if (info[i] != '0') {
					dp[i + 1] += dp[i];
					if (i != length - 1) {
						int curr = Integer.parseInt("" + info[i] + info[i + 1]);
						if (curr <= 26) {
							dp[i + 2] += dp[i];
						}
					}
				}
			}	
		System.out.println(dp[length]);	
		}
		in.close();
	}
}
