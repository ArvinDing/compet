
package camp;

import java.io.*;
import java.util.*;

public class dijamant {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		int n = Integer.parseInt(in.readLine());
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		LinkedList<Integer>[] parent = new LinkedList[n];
		int[] order = new int[n];
		outer: for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			String curr = read.nextToken();
			if (map.containsKey(curr)) {
				System.out.println("greska");
				continue;
			}
			map.put(curr, i);
			read.nextToken();
			parent[i] = new LinkedList<Integer>();
			while (true) {
				String neigh = read.nextToken();
				if (neigh.equals(";"))
					break;

				if (!map.containsKey(neigh)) {
					map.remove(curr);
					System.out.println("greska");
					continue outer;
				}
				parent[i].add(map.get(neigh));
			}
			Collections.sort(parent[i], new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					return order[arg1] - order[arg0];
				}
			});
			boolean[] marked = new boolean[n];
			for (int parentC : parent[i]) {
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(parentC);
				if (marked[parentC])
					continue;
				marked[parentC] = true;
				while (!queue.isEmpty()) {
					int loop = queue.poll();
					if (parent[loop] != null)
						for (int parents : parent[loop]) {
							queue.add(parents);
							if (marked[parents]) {
								map.remove(curr);
								System.out.println("greska");
								continue outer;
							}
							marked[parents] = true;
						}
				}
			}
			System.out.println("ok");
			for (int parentC : parent[i]) {
				order[i] = Math.max(order[parentC], order[i]);
			}
			order[i]++;
		}
		in.close();

	}
}