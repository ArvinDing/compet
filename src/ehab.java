import java.io.*;
import java.util.*;

public class ehab {
	public static void main(String[] args) throws Exception {
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("input"));
		n = Integer.parseInt(in.readLine());
		StringTokenizer read = new StringTokenizer(in.readLine());
		info = new int[n];
		for (int i = 0; i < n; i++) {
			info[i] = Integer.parseInt(read.nextToken());
		}
		System.out.println(solve());
		in.close();
	}

	static int[] info;
	static int n;

	public static int solve() {
		int[] sieve = new int[1000001];
		for (int i = 1; i <= 1000000; i++) {
			sieve[i] = i;
		}
		for (int i = 2; i <= 1000000; i++) {
			if (sieve[i] == i)
				for (int k = i * i; k <= 1000000; k += i) {
					if (k < 0)
						break;
					if (sieve[k] == k)
						sieve[k] = i;
				}
		}

		HashSet<Integer>[] graph = new HashSet[1000000];
		graph[1] = new HashSet<Integer>();
		Set<Integer> lol = new HashSet<Integer>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, info[i]);
		}
		int small = (int) Math.sqrt(max);
		for (int i = 0; i < n; i++) {
			List<Integer> oPrimes = new ArrayList<Integer>();
			int curr = info[i];
			while (sieve[curr] != 1) {
				int z = sieve[curr];
				int cnt = 0;
				while (curr % z == 0) {
					curr = curr / z;
					cnt++;
				}
				if (cnt % 2 == 1)
					oPrimes.add(z);
			}
			if (curr != 1)
				oPrimes.add(curr);

			for (int a : oPrimes)
				if (a <= small)
					lol.add(a);

			if (oPrimes.size() == 0) {
				return 1;
			} else if (oPrimes.size() == 1) {
				if (graph[1].contains(oPrimes.get(0)))
					return 2;

				if (graph[oPrimes.get(0)] == null)
					graph[oPrimes.get(0)] = new HashSet<Integer>();

				graph[1].add(oPrimes.get(0));
				graph[oPrimes.get(0)].add(1);
			} else {
				if (graph[oPrimes.get(0)] == null)
					graph[oPrimes.get(0)] = new HashSet<Integer>();
				if (graph[oPrimes.get(1)] == null)
					graph[oPrimes.get(1)] = new HashSet<Integer>();
				if (graph[oPrimes.get(0)].contains(oPrimes.get(1)))
					return 2;

				graph[oPrimes.get(1)].add(oPrimes.get(0));
				graph[oPrimes.get(0)].add(oPrimes.get(1));
			}

		}

		LinkedList<int[]> queue = new LinkedList<int[]>();
		int min = Integer.MAX_VALUE;
		for (int start : lol) {
			queue.add(new int[] { start, -1, 0 });
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			map.put(start, 0);
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				if (temp[2] > min)
					continue;

				for (int neigh : graph[temp[0]]) {
					if (temp[0] != start && map.containsKey(neigh) && neigh != temp[1])
						min = Math.min(min, temp[2] + map.get(neigh) + 1);

					if (!map.containsKey(neigh)) {
						queue.add(new int[] { neigh, temp[0], temp[2] + 1 });
						map.put(neigh, temp[2] + 1);
					}

				}
			}
		}
		if (min == Integer.MAX_VALUE)
			return -1;
		return min;
	}
}
