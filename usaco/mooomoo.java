
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class mooomoo {
	private static int[] bInfo;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mooomoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int fields = Integer.parseInt(read.nextToken());
		int breeds = Integer.parseInt(read.nextToken());
		bInfo = new int[breeds];
		for (int i = 0; i < breeds; i++) {
			bInfo[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(bInfo);
		int previous = 0;
		int cows = 0;
		for (int i = 0; i < fields; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (previous != 0) {
				int old=curr;
				curr -= (previous - 1);
				previous = old;
			} else {
				previous = curr;
			}
		//	System.out.println(curr);
			if (curr != 0)
				cows += minSteps(curr);

		}
		out.println(cows);
		in.close();
		out.close();
	}

	private static int[] dp = new int[1000001];

	private static int minSteps(int finale) {
		int min = Integer.MAX_VALUE;
		if (dp[finale] != 0 || finale == 0)
			return dp[finale];

		for (int a : bInfo) {
			if (finale - a >= 0) {
				int previous = minSteps(finale - a);
				if (previous != Integer.MAX_VALUE) {
					min = Math.min(min, previous + 1);
				}
			}
		}
		dp[finale] = min;
		return min;

	}

}