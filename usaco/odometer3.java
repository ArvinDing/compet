
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

public class odometer3 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("odometer.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		String startS = read.nextToken();
		String endS = read.nextToken();
		long start = Long.parseLong(startS);
		long end = Long.parseLong(endS);
		int startDigits = startS.length();
		int endDigits = endS.length();
		int answer = 0;
		for (int i = startDigits; i <= endDigits; i++) {
			String curr = "";
			for (int a = 0; a < i; a++) {
				curr += "" + 0;
			}
			for (int j = 1; j <= 9; j++) {
				long save = Long.parseLong(j + curr.substring(1));
				if (start <= save && save <= end) {
					answer++;
				}
			}
			for (int k = 1; k <= 9; k++) {
				curr = "";
				for (int a = 0; a < i; a++) {
					curr += "" + k;
				}
				for (int b = curr.length() - 1; b >= 0; b--) {
					for (int j = 0; j <= 9; j++) {
						if (j == k) {
							continue;
						}
						if (b == 0 && j == 0) {
							continue;
						}

						long save = Long.parseLong(curr.substring(0, b) + "" + j + curr.substring(b + 1));
						// System.out.println(save);

						if (start <= save && save <= end) {
							answer++;
							// System.out.println(save);

						}
					}
				}

			}
		}
		out.println(answer);
		in.close();
		out.close();
	}

}