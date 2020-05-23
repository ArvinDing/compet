
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

public class truth1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("truth.in"));
		PrintWriter out = new PrintWriter(new File("truth.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int statements = Integer.parseInt(read.nextToken());
		HashMap<Integer, Boolean>[][] bruteforce = new HashMap[cows + 1][2];
		for (int i = 0; i < cows + 1; i++) {
			for (int k = 0; k < 2; k++) {
				bruteforce[i][k] = new HashMap<Integer, Boolean>();
			}
		}
		for (int i = 0; i < statements; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(in.readLine());
			int y = Integer.parseInt(in.readLine());
			boolean truth = in.readLine().equals("T");
			if (bruteforce[x][0].containsKey(y)) {
				if (bruteforce[x][0].get(y) != truth) {
					out.println(i);
					break;
				}
			} else {
				bruteforce[x][0].put(y, truth);
				bruteforce[x][1].put(y, !truth);
				bruteforce[y][0].put(x, truth);
				bruteforce[y][1].put(x, !truth);
				change(bruteforce, y);
				change(bruteforce, x);
			}
		}

		in.close();
		out.close();
	}

	private static boolean change(HashMap<Integer, Boolean>[][] bruteforce, int index) {
		return false;
	}

}