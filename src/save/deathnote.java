package save;

import java.io.*;
import java.util.*;

public class deathnote {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		int last = 0;
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(read.nextToken());
			last += a;
			if (i != n - 1)
				System.out.print((last / m) + " ");
			else
				System.out.print(last / m);
			last %= m;
		}
		System.out.println();
		in.close();
	}
}