
/*
 ID: dingarv1
 PROB: tour
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class tour2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("tour.in"));
		PrintWriter out = new PrintWriter(new File("tour.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int cities = Integer.parseInt(read.nextToken());
		int flights = Integer.parseInt(read.nextToken());
		HashMap<String, Integer> link = new HashMap<String, Integer>();
		for (int i = 0; i < cities; i++) {
			link.put(in.readLine(), i);
		}
		List<Integer>[] info = new List[cities];
		for (int i = 0; i < flights; i++) {
			read = new StringTokenizer(in.readLine());
			int a = link.get(read.nextToken());
			int b = link.get(read.nextToken());
			if (a < b) {
				if (info[a] == null)
					info[a] = new ArrayList<Integer>();
				info[a].add(b);
			} else {
				if (info[b] == null)
					info[b] = new ArrayList<Integer>();
				info[b].add(a);
			}

		}
		List<int[]> save = new ArrayList<int[]>();
		LinkedList<int[]> queue = new LinkedList<int[]>();
		List<Integer> ans = new ArrayList<Integer>();
		queue.add(new int[] { 0, -1 });
		int index = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int position = curr[0];
			int previousIndex = curr[1];
			save.add(new int[] { position, previousIndex });

			if (position == cities - 1) {
				ans.add(index);
				index++;
				continue;
			}
			List<Integer> temp = info[position];
			if (temp != null)
				for (int b : temp) {
					queue.add(new int[] { b, index });
				}
			index++;
		}

		HashMap<Integer, Set<Integer>> later = new HashMap<Integer, Set<Integer>>();
		for (int i : ans) {
			Set<Integer> now = new HashSet<Integer>();
			int cIndex = i;
			while (cIndex != -1) {
				int[] curr = save.get(cIndex);
				now.add(curr[0]);
				cIndex = curr[1];
			}
			later.put(i, now);
		}
		int max = 1;
		for (int firstI : ans) {
			for (int secondI : ans) {
				TreeSet<Integer> same = new TreeSet<Integer>();
				Set<Integer> a = later.get(firstI);
				Set<Integer> b = later.get(secondI);
				same.addAll(a);
				same.addAll(b);
				if (same.size() + 2 == a.size() + b.size()) {
					max = Math.max(same.size(), max);
				}
			}
		}
		out.println(max);
		in.close();
		out.close();
	}

}
