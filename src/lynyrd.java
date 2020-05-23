
import java.io.*;
import java.util.*;

public class lynyrd {
	static long min;
	static long max;
	static int n;
	static int k;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		int q = Integer.parseInt(read.nextToken());
		int[] perm = new int[n];
		int[] valtoI = new int[n];
		int[] info = new int[m];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(read.nextToken()) - 1;
			perm[i] = value;
			valtoI[value] = i;
		}
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken()) - 1;
		}
		int[] latest = new int[n];
		Arrays.fill(latest, -1);
		int[] prev = new int[n];
		for (int i = 0; i < n; i++) {
			prev[i] = latest[info[(valtoI[info[i]] - 1 + n) % n]];
			latest[info[i]] = i;
		}
		for (int i = 0; i < q; i++) {
			read = new StringTokenizer(in.readLine());
			int a=Integer.parseInt(read.nextToken())-1;
			int b=Integer.parseInt(read.nextToken())-1;
			
		}
		in.close();
	}
}
