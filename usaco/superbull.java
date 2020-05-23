
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class superbull {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new File("superbull.out"));
		int teams = Integer.parseInt(in.readLine());
		int[] info = new int[teams];
		for (int i = 0; i < teams; i++) {
			info[i] = Integer.parseInt(in.readLine());
		}
		long[][] points = new long[teams][teams];
		TreeMap<Long, Integer> maxs = new TreeMap<Long, Integer>();
		for (int i = 0; i < teams; i++) {
			long max = Integer.MIN_VALUE;
			for (int k = 0; k < teams; k++) {
				points[i][k] = info[i] ^ info[k];
				max = Math.max(max, points[i][k]);
			}
			maxs.put(max, i);
		}
		long answer = 0;
		boolean[] done = new boolean[teams];
		for (int index : maxs.values()) {
			long max = Integer.MIN_VALUE;
			for (int k = 0; k < teams; k++) {
				if (!done[k]) {
					max = Math.max(max, points[index][k]);
				}
			}
	
			answer += max;
			done[index] = true;
		}
		out.print(answer);
		in.close();
		out.close();
	}
}