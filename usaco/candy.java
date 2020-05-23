import java.util.*;
import java.io.*;

public class candy {
	private final static int mod = 1000000007;

	static long substrings(String balls) {
		long sum = 0;

		char[] info = balls.toCharArray();
		long mult = 1;
		for (int i = balls.length() - 1; i >= 0; i--) {
			sum = (sum + (info[i] - '0') * (i + 1) * mult) % mod;
			mult = (mult * 10 + 1) % mod;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String balls = in.next();
		long result = substrings(balls);
		System.out.println(result);
		in.close();
	}
}
