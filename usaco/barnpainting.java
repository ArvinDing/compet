
import java.io.*;
import java.util.*;

public class barnpainting {
	private static int barns;
	private static List<Integer>[] info;
	private static int divide = 1000000007;
	private static int[] concrete;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter(new File("barnpainting.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		barns = Integer.parseInt(read.nextToken());
		int facts = Integer.parseInt(read.nextToken());
		info = new List[barns];
		concrete = new int[barns];
		for (int i = 0; i < barns; i++) {
			info[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < barns - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			info[a].add(b);
			info[b].add(a);
		}
		for (int i = 0; i < facts - 1; i++) {
			read = new StringTokenizer(in.readLine());
			concrete[Integer.parseInt(read.nextToken()) - 1] = Integer.parseInt(read.nextToken());
		}
		record = new boolean[barns];
		if (facts != 0) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int value = Integer.parseInt(read.nextToken()) - 1;
			concrete[start] = value + 1;
			record[start] = true;
			recursion(start, value, 0);
			out.println(cnt);
		} else {
			int mult = 3;
			for (int i = 0; i < barns - 1; i++) {
				mult *= 2;
				mult %= divide;
			}
			out.println(mult);
		}
		in.close();
		out.close();
	}

	private static int cnt = 0;
	private static boolean[] record;

	private static void recursion(int position, int value, int done) {	
		System.out.println(position+" "+value);
		done++;
		if (done == barns) {
			cnt++;
			cnt %= divide;
			return;
		}
		for (int a : info[position]) {
			if (!record[a]) {
				record[a] = true;
				if (concrete[a] != 0) {
					recursion(a, concrete[a] - 1, done);
				} else {
					recursion(a, (value + 1) % 3, done);
					recursion(a, (value + 2) % 3, done);
				}
			}
		}
		for (int a : info[position]) {
			record[a] = false;
		}
	}
}
