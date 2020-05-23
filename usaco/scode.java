
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

public class scode {
	private static int cnt = 0;
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		recursion(in.readLine());
		out.println(cnt-1);
		in.close();
		out.close();
	}

	

	private static void recursion(String curr) {
		
		cnt++;
		if (curr.length()%2==0) {
			return ;
		}
		if (curr.startsWith(curr.substring(curr.length() / 2 + 1, curr.length()))) {
			 recursion(curr.substring(curr.length() / 2, curr.length()));
			 recursion(curr.substring(0, curr.length() / 2 + 1));
		}
		if (curr.startsWith(curr.substring(curr.length() / 2, curr.length() - 1))) {
			 recursion(curr.substring(curr.length() / 2, curr.length()));
		}
		if (curr.endsWith(curr.substring(1, curr.length() / 2 + 1))) {
			recursion(curr.substring(0, curr.length() / 2 + 1));
		}

		return;
	}
}