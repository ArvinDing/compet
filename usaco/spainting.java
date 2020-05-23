
import java.io.*;
import java.util.*;

public class spainting {
	
	private static int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new File("spainting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int length = Integer.parseInt(read.nextToken());
		int colors = Integer.parseInt(read.nextToken());
		int width = Integer.parseInt(read.nextToken());
		// complementary counting
		long[] oppDp = new long[length + 1];
		long previous = 0;
		oppDp[0] = 1;
		for (int i = 1; i < width; i++) {
			oppDp[i] = (oppDp[i - 1] * colors)%mod;
			previous = (previous+oppDp[i])%mod;
		}
		for (int i = width; i <= length; i++) {
			oppDp[i] = (previous * (colors - 1)) % mod;
			previous -= oppDp[i - width + 1];
			previous += oppDp[i];
			previous %= mod;

		}
		long total = 1;
		for (int i = 0; i < length; i++) {
			total = (total * colors) % mod;
		}
		out.println((total-oppDp[length]+mod)%mod);
		in.close();
		out.close();
	}

}
