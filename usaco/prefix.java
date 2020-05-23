
/*
ID: dingarv1
LANG: JAVA
TASK: prefix
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class prefix {
	public int solve(String s, String[] primitives) {
		int len = s.length();
		boolean[] dp = new boolean[len + 1];

		for (String p : primitives) {
			int l = p.length();
			if (l >= len)
				continue;
			if (s.substring(0, l).equals(p)) {
				dp[l] = true;
			}
		}

		for (int k = 0; k <= len; k++) {
			if (dp[k]) {
				for (String p : primitives) {
					int l = p.length();
					if (k + l > len)
						continue;
					if (s.substring(k, k + l).equals(p)) {
						dp[k + l] = true;
					}
				}
			}
		}
		int res = 0;
		for (int k = 0; k <= len; k++) {
			if (dp[k]) {
				res = k;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new File("prefix.out"));
		ArrayList<String> primitives = new ArrayList<String>();
		String line = in.readLine();
		while (!line.equals(".")) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				primitives.add(st.nextToken());
			}
			line = in.readLine();
		}
		StringBuilder sb = new StringBuilder();
		line = in.readLine();
		while (line != null) {
			sb.append(line);
			line = in.readLine();
		}

		int res = (new prefix()).solve(sb.toString(), primitives.toArray(new String[0]));

		out.println(res);
		out.close();
		
	}
}
