
import java.io.*;
import java.util.*;

public class math {
	public static void main(String[] args) throws Exception {
		int[] dp = new int[2015];
		dp[0] = 2;
		dp[1] = 4;
		dp[2] = 8;
		for (int i = 0; i < 2015; i++) {
			if (i >= 3)
				dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 12;
			System.out.println((i+1) + " " + dp[i]);
		}
	}
}
