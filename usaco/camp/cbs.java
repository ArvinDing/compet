package camp;

import java.io.*;
import java.util.*;

public class cbs {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cbs.in"));
		PrintWriter out = new PrintWriter(new File("cbs.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(read.nextToken());
		int n = Integer.parseInt(read.nextToken());
		int[][] info = new int[k][n];

		for (int j = 0; j < k; j++) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				String line = in.readLine();
				info[j][i] = (line.charAt(i) == ')') ? --sum : ++sum;
			}
		}
	}
}
