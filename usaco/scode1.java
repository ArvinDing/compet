
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
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class scode1 {
	private static long cnt = 0;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		recursion(in.readLine());
		out.println((cnt - 1) % 2014);
		in.close();
		out.close();
	}

	private static void recursion(String curr) {
		cnt++;
		//System.out.println(curr);
		if (curr.length() <= 1) {
			return;
		}
		int mid = ((curr.length() - 1) / 2);
		for (int i = 1; i <= mid; i++) {
			if (curr.startsWith(curr.substring(i, i + i))) {
				recursion(curr.substring(i));
			}
			if (curr.startsWith(curr.substring(curr.length() - i, curr.length()))) {
				recursion(curr.substring(0, curr.length() - i));

			}
			if (curr.endsWith(curr.substring(curr.length() - i - i, curr.length() - i))) {
				recursion(curr.substring(0, curr.length() - i));
			}
			if (curr.endsWith(curr.substring(0, i))) {
				recursion(curr.substring(i));

			}

		}

		return;
	}

}