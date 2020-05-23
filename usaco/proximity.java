
import java.io.*;
import java.util.*;

public class proximity {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("proximity.in"));
		PrintWriter out = new PrintWriter(new FileWriter("proximity.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cows = Integer.parseInt(read.nextToken());
		int crowded = Integer.parseInt(read.nextToken());
		LinkedList<Integer> info = new LinkedList<Integer>();
		BitSet check = new BitSet();
		int max = -1;
		for (int i = 0; i < crowded; i++) {
			int first = Integer.parseInt(in.readLine());
			if (check.get(first)) {
				max = Math.max(max, first);
			}
			info.add(first);
			check.set(first);

		}

		for (int i = crowded; i < cows; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (check.get(curr)) {
				max = Math.max(max, curr);
			}
			check.clear(info.get(0));
			info.remove(0);
			check.set(curr);
			info.add(curr);
		}
		out.println(max);
		in.close();
		out.close();
	}
}
