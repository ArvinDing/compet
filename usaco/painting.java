
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;



import java.util.StringTokenizer;
import java.util.TreeMap;

public class painting {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("painting.in"));
		PrintWriter out = new PrintWriter(new File("painting.out"));
		int enclosures = Integer.parseInt(in.readLine());
		TreeMap<Integer, List<link>> info = new TreeMap<Integer, List<link>>();
		for (int i = 0; i < enclosures; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			int x1 = Integer.parseInt(read.nextToken());
			int y1 = Integer.parseInt(read.nextToken());
			if (!info.containsKey(x))
				info.put(x, new ArrayList<link>());
			if (!info.containsKey(x1))
				info.put(x1, new ArrayList<link>());
			info.get(x).add(new link(i, y1, y));
			info.get(x1).add(new link(i, y1, y));
		}
		boolean[] enclosed = new boolean[enclosures];
		boolean[] done = new boolean[enclosures];
		HashMap<Integer, link> temp = new HashMap<Integer, link>();
		for (List<link> a : info.values()) {
			for (link curr : a) {
				if (done[curr.enclosure]) {
					for (link b : temp.values()) {
						if (b.topY > curr.topY && b.y < curr.y) {
							enclosed[curr.enclosure] = true;
						}
					}
					temp.remove(curr.enclosure);
				} else {
					temp.put(curr.enclosure, curr);
					done[curr.enclosure] = true;
				}

			}

		}
		int answer = 0;
		for (int i = 0; i < enclosures; i++) {
			if (!enclosed[i])
				answer++;
		}
		out.println(answer);
		in.close();
		out.close();
	}

	private static class link {
		int enclosure;
		int topY;
		int y;

		private link(int enclosure, int topY, int y) {
			this.enclosure = enclosure;
			this.topY = topY;
			this.y = y;
		}

	}
}