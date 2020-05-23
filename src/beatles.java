
import java.io.*;
import java.util.*;

public class beatles {
	static long min;
	static long max;
	static int n;
	static int k;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		k = Integer.parseInt(read.nextToken());
		read = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(read.nextToken());
		int y = Integer.parseInt(read.nextToken());
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		long length=(long)n*k;
		for (long i = 0; i < length; i += k) {
			long poss = ((i + y) + (length)) % (length);
			long jump = ((poss - x) + (length)) % (length);
			test(jump);
			poss = ((i - y) + (length)) % (length);
			jump = ((poss - x) + (length)) % (length);
			test(jump);
			poss = ((i + y) + (length)) % (length);
			jump = ((x - poss) + (length)) % (length);
			test(jump);
			poss = ((i - y) + (length)) % (length);
			jump = ((x - poss) + (length)) % (length);
			test(jump);
		}
		System.out.println(min + " " + max);
		in.close();
	}

	public static long test(long jump) {
		long length = (long)n * k;
		long save = 1;
		if (jump != 0)
			save = (length  / (gcd(length, jump))) ;
		min = Math.min(min, save);
		max = Math.max(max, save);
		return save;
	}

	public static long gcd(long a, long b) {
		
		if (a > b)
			return gcd(b, a);
		if (a == b)
			return a;
		if (a == 0)
			return b;	
		
		return gcd(a, b % a);
	}
}
