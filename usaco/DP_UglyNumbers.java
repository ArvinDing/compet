
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DP_UglyNumbers {
	private static long uglyNumber(int n) {
		List<Long> info = new ArrayList<Long>();
		info.add((long) 1);

		int two = 0;
		int three = 0;
		int five = 0;
		int index = 0;
		while (index != n) {
			long min = Long.MAX_VALUE;
			if (two < info.size()) {
				min = Math.min(min, info.get(two) * 2);
			}
			if (three < info.size()) {
				min = Math.min(min, info.get(three) * 3);
			}
			if (five < info.size()) {
				min = Math.min(min, info.get(five) * 5);
			}
			if (two < info.size() && min == info.get(two) * 2) {
				two++;
			} else if (three < info.size() && min == info.get(three) * 3) {
				three++;
			} else if (five < info.size()) {
				five++;
			}
			if (!info.contains(min)) {
				index++;
				info.add(min);
			}
		}
		return info.get(index - 1);

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(// new
													// java.io.FileReader(""input"")))
													// {/*
				new java.io.InputStreamReader(System.in))) {// */
			System.out.println(uglyNumber(Integer.parseInt(in.readLine())));
		}
	}
}