import java.io.*;
import java.util.*;

public class nondec {
	static long mod = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nondec.in"));
		PrintWriter out = new PrintWriter(new PrintWriter("nondec.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		int[] info = new int[n];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken()) - 1;
		}
		int q = Integer.parseInt(in.readLine());

		long[][] ans = new long[n][n];
		for (int i = 0; i < n; i++) {
			long[] number = new long[k];
			long sum = 0;
			
			for (int j = i; j < n; j++) {
				long[] newthing = new long[k];
				
				for (int z = 0; z < k; z++) {
					newthing[z] = number[z];
				}
				
				for (int z = 0; z <= info[j]; z++) {
					newthing[info[j]] = (newthing[info[j]] + number[z]) % mod;
					sum = (sum + number[z]) % mod;
				}
				
				newthing[info[j]] += 1;
				sum += 1;
				
				for (int z = 0; z < k; z++) {
					number [z] = newthing[z];
				}
				ans[i][j] = sum+1;
			}
			

		}
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			
			out.println(ans[a][b]);
		}
		in.close();
		out.close();
	}

}
