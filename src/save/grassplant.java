package save;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class grassplant {
	private static int[] disjoint;
	private static int[] size;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("simplify.in"));
		PrintWriter out = new PrintWriter(new File("simplify.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		TreeMap<Integer, LinkedList<int[]>> info = new TreeMap<Integer, LinkedList<int[]>>();
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			if (!info.containsKey(cost))
				info.put(cost, new LinkedList<int[]>());
			info.get(cost).add(new int[] { a, b });
		}
		disjoint = new int[n];
		size = new int[n];
		Arrays.fill(size, 1);
		for (int i = 0; i < n; i++)
			disjoint[i] = i;
		int total = 0;
		long ways = 1;
		int[][] save = new int[3][2];
		for (Entry<Integer, LinkedList<int[]>> curr : info.entrySet()) {
			int cost = curr.getKey();
			boolean[] dInclude = new boolean[3];
			int idx = 0;
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			for (int[] loop : curr.getValue()) {
				int a = parent(loop[0]);
				int b = parent(loop[1]);
				if (a == b)
					continue;
				if (a < b)
					save[idx] = new int[] { a, b };
				else
					save[idx] = new int[] { b, a };
				if (map.containsKey(a))
					map.put(a, map.get(a) + 1);
				else
					map.put(a, 1);
				if (map.containsKey(b))
					map.put(b, map.get(b) + 1);
				else
					map.put(b, 1);
				idx++;

			}

			if (idx == 3) {
				boolean all2 = true;
				for (int a : map.values()) {
					if (a != 2) {
						all2 = false;
					}
				}
				if (all2) {
					total += 2 * cost;
					ways = (ways * 3) % 1000000007;
					Object[] a = map.keySet().toArray();
					disjoint[(int) a[0]] = (int) a[1];
					disjoint[(int) a[2]] = (int) a[1];
					size[(int) a[1]] += size[(int) a[0]] + size[(int) a[2]];
					continue;

				}

			}
			for (int i = 0; i < idx; i++) {
				if (save[i][0] == save[i][1] || dInclude[i])
					continue;
				int cnt = 1;
				for (int k = i + 1; k < idx; k++) {
					if (save[i][0] == save[k][0] && save[i][1] == save[k][1]) {
						dInclude[k] = true;
						cnt++;
					}
				}
				int a = disjoint[save[i][0]];
				int b = disjoint[save[i][1]];
				ways = (ways * cnt) % 1000000007;
				total += cost;
				if (size[a] > size[b]) {
					size[a] += size[b];
					disjoint[b] = a;

				} else {
					size[b] += size[a];
					disjoint[a] = b;
				}
			}
		}

		out.println(total + " " + ways);
		out.close();
		in.close();
	}

	private static int parent(int a) {
		int parentA = a;
		while (disjoint[parentA] != parentA) {
			parentA = disjoint[parentA];
		}
		int temp = a;
		while (disjoint[temp] != temp) {
			int save = disjoint[temp];
			disjoint[temp] = parentA;
			temp = save;
		}
		return parentA;
	}
}