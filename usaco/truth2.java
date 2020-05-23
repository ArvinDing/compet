
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class truth2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("truth.in"));
		PrintWriter out = new PrintWriter(new File("truth.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int statements = Integer.parseInt(read.nextToken());
		HashMap<Integer, Boolean>[][] help = new HashMap[cows + 1][2];
		boolean[][] flag = new boolean[cows + 1][2];
		for (int i = 0; i < statements; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(in.readLine());
			boolean truth = read.nextToken().equals("T");
			if (!flag[x][0]) {
				if (help[x][0] == null) {
					HashMap<Integer, Boolean> one = new HashMap<Integer, Boolean>();
					one.put(x, true);
					one.put(y, truth);
					help[x][0] = one;
				} else {
					if (help[x][0].containsKey(y)) {
						if (help[x][0].get(y) != truth) {
							flag[x][0] = true;
						}
					} else {
						help[x][0].put(y, truth);
					}
				}
			}
			if (help[x][1] == null) {
				HashMap<Integer, Boolean> two = new HashMap<Integer, Boolean>();
				two.put(x, false);
				two.put(y, !truth);
				help[x][1] = two;
			}

		}
		in.close();
		out.close();
	}

}