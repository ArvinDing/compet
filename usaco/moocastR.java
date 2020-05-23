
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class moocastR {
	private static int[] disjoint;

	public static void main(String[] argv) throws IOException {
		long start=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int n = Integer.parseInt(in.readLine());
		TreeMap<Integer, List<int[]>> info = new TreeMap<Integer, List<int[]>>();
		List<int[]> temp = new ArrayList<int[]>();
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			for (int[] loop : temp) {
				int cost = ((loop[1] - x) * (loop[1] - x)) + ((loop[2] - y) * (loop[2] - y));
				if (!info.containsKey(cost))
					info.put(cost, new ArrayList<int[]>());
				info.get(cost).add(new int[] { loop[0], i });
			}
			temp.add(new int[] { i, x, y });
		}
		System.out.println(System.currentTimeMillis()-start);
		start=System.currentTimeMillis();
		disjoint = new int[n];
		for (int i = 0; i < n; i++) {
			disjoint[i] = i;
		}
		int[] size = new int[n];
		Arrays.fill(size, 1);
		outer: for (Entry<Integer, List<int[]>> curr : info.entrySet()) {
			int currCost = curr.getKey();
			List<int[]> k = curr.getValue();
			for (int[] connections : k) {
				int parentA = getParent(connections[0]);
				int parentB = getParent(connections[1]);
				if (parentA == parentB)
					continue;
				if (size[parentA] > size[parentB]) {
					size[parentA] += size[parentB];
					size[parentB] = 0;
					disjoint[parentB] = parentA;

				} else {
					size[parentB] += size[parentA];
					size[parentA] = 0;
					disjoint[parentA] = parentB;
				}
				if (size[parentA] == n || size[parentB] == n) {
					out.println(currCost);
					break outer;
				}

			}

		}
		System.out.println(System.currentTimeMillis()-start);
		start=System.currentTimeMillis();
		in.close();
		out.close();
	}

	private static int getParent(int index) {
		int parentA = index;
		while (disjoint[parentA] != parentA) {
			parentA = disjoint[parentA];
		}
		int loop = index;
		while (disjoint[loop] != loop) {
			int old = loop;
			loop = disjoint[loop];
			disjoint[old] = parentA;
		}
		return parentA;
	}

}