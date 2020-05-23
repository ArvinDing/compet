package save;

import java.io.*;
import java.util.*;

public class shelf {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bookshelf.in"));
		PrintWriter out = new PrintWriter(new File("bookshelf.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int maxW = Integer.parseInt(read.nextToken());
		long[] dp = new long[n + 1];
		int[] h = new int[n];
		int[] w = new int[n];
		
		in.close();
		out.close();
	}
}
