
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class scode2 {
	private static HashMap<String, Integer> dp = new HashMap<String, Integer>();

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		out.println(recursion(in.readLine())-1);
		in.close();
		out.close();
	}

	private static int recursion(String curr) {
		int cnt = 1;
		System.out.println(curr);
		if (dp.containsKey(curr))
			return dp.get(curr);

		int mid = ((curr.length() - 1) / 2);
		for (int i = 1; i <= mid; i++) {
			if (curr.startsWith(curr.substring(i, i + i))) {
				cnt += recursion(curr.substring(i));
			}
			if (curr.startsWith(curr.substring(curr.length() - i, curr.length()))) {
				cnt += recursion(curr.substring(0, curr.length() - i));

			}
			if (curr.endsWith(curr.substring(curr.length() - i - i, curr.length() - i))) {
				cnt += recursion(curr.substring(0, curr.length() - i));
			}
			if (curr.endsWith(curr.substring(0, i))) {
				cnt += recursion(curr.substring(i));

			}

		}
	
		if (cnt > 2014)
			cnt %= 2014;
		dp.put(curr, cnt);
		return cnt;
	}
}