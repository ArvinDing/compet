
import java.io.*;
import java.util.*;

public class fairphoto2 {
	static HashMap<String, Integer>[] constant;
	static int least;
	static int[][] info;
	private static int[][] concrete;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new File("fairphoto.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		least = Integer.parseInt(read.nextToken());
		info = new int[n][2];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			info[i] = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) - 1 };
		}

		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		LinkedList<Integer>[] next = new LinkedList[n];
		LinkedList<Integer> index = new LinkedList<Integer>();
		for (int i = n - 1; i >= 0; i--) {
			next[i] = new LinkedList<Integer>();
			next[i].add(info[i][1]);
			Iterator<Integer> itr = index.iterator();
			while (itr.hasNext()) {
				int curr = itr.next();
				if (curr == info[i][1]) {
					itr.remove();
				} else {
					next[i].add(curr);
				}
			}
			index.addFirst(info[i][1]);

		}

		concrete = new int[256][8];
		constant = new HashMap[256];

		LinkedList<Integer> prev = new LinkedList<Integer>();
		int[] cnt = new int[8];
		int max = -1;
		for (int i = 0; i < n; i++) {
			update(next[i], cnt, info[i][0]);
			cnt[info[i][1]]++;
			Iterator<Integer> itr = prev.iterator();
			while (itr.hasNext()) {
				if (itr.next() == info[i][1]) {
					itr.remove();
				}
			}
			prev.addFirst(info[i][1]);
			int res = query(prev, cnt, info[i][0]);
			// System.out.println(res);
			if (res != -1)
				max = Integer.max(max, res);
		}
		System.out.println(add);
		out.println(max);
		in.close();
		out.close();
	}

	static int add = 0;

	static int query(LinkedList<Integer> imp, int[] cnt, int poss) {
		LinkedList<Integer> flex = new LinkedList<Integer>();
		if (imp.size() < least)
			return -1;
		Iterator<Integer> itr = imp.iterator();
		while (itr.hasNext())
			flex.add(itr.next());
		int hash = 0;
		boolean[] fixed = new boolean[8];
		Arrays.fill(fixed, true);
		for (int i = 0; i < least; i++) {
			int a = flex.poll();
			hash += (1 << a);
			fixed[a] = false;
		}
		int min = Integer.MAX_VALUE;
		while (!flex.isEmpty()) {
			String key = generate(cnt, fixed, hash);
			int a = flex.poll();
			if (constant[hash] != null && constant[hash].containsKey(key))
				min = Math.min(min, constant[hash].get(key));
			hash += (1 << a);
			fixed[a] = false;
		}
		String key = generate(cnt, fixed, hash);
		if (constant[hash] != null && constant[hash].containsKey(key))
			min = Math.min(min, constant[hash].get(key));
		if (min == Integer.MAX_VALUE)
			return -1;
		return (poss - min);
	}

	static void update(LinkedList<Integer> sort, int[] cnt, int poss) {
		if (sort.size() < least)
			return;
		int hash = 0;
		boolean[] fixed = new boolean[8];
		Arrays.fill(fixed, true);
		for (int i = 0; i < least; i++) {
			int a = sort.poll();
			hash += (1 << a);
			fixed[a] = false;
		}
		String key = generate(cnt, fixed, hash);
		if (constant[hash] == null)
			constant[hash] = new HashMap<String, Integer>();
		if (!constant[hash].containsKey(key))
			constant[hash].put(key, poss);
		while (!sort.isEmpty()) {
			int a = sort.poll();
			hash += (1 << a);
			fixed[a] = false;
			key = generate(cnt, fixed, hash);
			if (constant[hash] == null)
				constant[hash] = new HashMap<String, Integer>();
			if (constant[hash].containsKey(key))
				continue;
			constant[hash].put(key, poss);

		}

	}

	static String generate(int[] cnt, boolean[] fixed, int hash) {

		int first = -1;
		for (int k = 0; k < 8; k++) {
			if (first == -1 && !fixed[k]) {
				first = cnt[k];
				break;
			}
		}

		long start = System.nanoTime();
		String curr = "";
		boolean reset = false;
		for (int k = 0; k < 8; k++) {
			if (!fixed[k])
				curr += ((cnt[k] - first) + " ");
			else {
				if (!reset && fixed[k] && concrete[hash][k] != cnt[k]) {
					constant[hash] = new HashMap<String, Integer>();
					reset = true;
				}
				concrete[hash][k] = cnt[k];

			}
		}
		add += System.nanoTime() - start;
		return curr;
	}

}
