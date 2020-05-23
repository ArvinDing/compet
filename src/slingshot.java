
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class slingshot {
	static long[] ans;
	static long[][] segT;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("slingshot.in"));
		PrintWriter out = new PrintWriter(new File("slingshot.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int m = Integer.parseInt(read.nextToken());
		Comparator<int[]> sort = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int a = o1[0];
				int b = o2[0];
				if (o1[3] % 2 == 1)
					a = o1[1];
				if (o2[3] % 2 == 1)
					b = o2[1];
				return a - b;
			}
		};
		TreeSet<Integer> fLink = new TreeSet<Integer>();
		TreeSet<Integer> rLink = new TreeSet<Integer>();
		PriorityQueue<int[]> forw = new PriorityQueue<int[]>(sort);
		PriorityQueue<int[]> rev = new PriorityQueue<int[]>(sort);

		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			int cost = Integer.parseInt(read.nextToken());
			if (cost >= Math.abs(start - end))
				continue;
			if (start < end) {
				fLink.add(start);
				fLink.add(end);
				forw.add(new int[] { start, end, cost, 0 });
				forw.add(new int[] { start, end, cost, 1 });
			} else {
				rLink.add(start);
				rLink.add(end);
				rev.add(new int[] { end, start, cost, 0 });
				rev.add(new int[] { end, start, cost, 1 });
			}

		}
		ans = new long[m];
		for (int i = 0; i < m; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			ans[i] = Math.abs(start - end);
			if (start < end) {
				fLink.add(start);
				fLink.add(end);
				forw.add(new int[] { start, end, i, 2 });
				forw.add(new int[] { start, end, i, 3 });
			} else {
				rLink.add(start);
				rLink.add(end);
				rev.add(new int[] { end, start, i, 2 });
				rev.add(new int[] { end, start, i, 3 });
			}
		}
		solve(forw, fLink);
		solve(rev, rLink);

		for (long a : ans)
			out.println(a);
		out.close();
		in.close();
	}

	static void solve(PriorityQueue<int[]> info, TreeSet<Integer> link) {
		
	//	HashMap<Integer, Integer> bLink = new HashMap<Integer, Integer>();
		int []bLink=new int [link.size()];
		int idx = 0;
		for (int a : link)
			bLink[idx++]=a;
		int size = link.size();
		segT = new long[3][4 * size];
		Arrays.fill(segT[0], Long.MAX_VALUE / 2);
		Arrays.fill(segT[1], Long.MAX_VALUE / 2);
		Arrays.fill(segT[2], Long.MAX_VALUE / 2);
		Stack<int[]> reverse = new Stack<int[]>();
		while (!info.isEmpty()) {
			int[] curr = info.poll();
			int s = curr[0];
			int e = curr[1];
//			int lS = bLink.get(s);
//			int lE = bLink.get(e);
			int lS = Arrays.binarySearch(bLink, s);
			int lE =  Arrays.binarySearch(bLink, e);

			if (curr[3] == 0) {
				// slingS
				update(0, size - 1, 1, lE, -e - s + curr[2], 0);
				update(0, size - 1, 1, lE, e - s + curr[2], 1);
			} else if (curr[3] == 1) {
				// slingE
				update(0, size - 1, 1, lS, s - e + curr[2], 2);
				reverse.push(curr);
			} else if (curr[3] == 2) {
				// queryS
				long res = query(0, size - 1, 1, lS, lE, 0);
				long res1 = query(0, size - 1, 1, lE, size - 1, 1);
				ans[curr[2]] = Math.min(ans[curr[2]], Math.min(res1 + s - e, res + s + e));
			} else {
				// queryE
				long res = query(0, size - 1, 1, lS, lE, 2);
				ans[curr[2]] = Math.min(ans[curr[2]], res - s + e);
				reverse.push(curr);
			}
		}
		Arrays.fill(segT[0], Long.MAX_VALUE / 2);
		while (!reverse.isEmpty()) {
			int[] curr = reverse.pop();
			int s = curr[0];
			int e = curr[1];
//			int lS = bLink.get(s);
//			int lE = bLink.get(e);
			int lS = Arrays.binarySearch(bLink, s);
			int lE =  Arrays.binarySearch(bLink, e);
			if (curr[3] == 1) {
				update(0, size - 1, 1, lS, e + s + curr[2], 0);
			} else {
				long res = query(0, size - 1, 1, lS, lE, 0);
				ans[curr[2]] = Math.min(ans[curr[2]], res - s - e);
			}
		}

	}

	static long query(int l, int r, int idx, int lx, int rx, int segTi) {
		if (r < lx || rx < l)
			return Integer.MAX_VALUE;
		if (lx <= l && r <= rx)
			return segT[segTi][idx];
		int mid = (l + r) / 2;
		long res = query(l, mid, idx * 2, lx, rx, segTi);
		long res1 = query(mid + 1, r, idx * 2 + 1, lx, rx, segTi);
		return Math.min(res, res1);
	}

	static void update(int l, int r, int idx, int pos, int value, int segTi) {
		if (r < pos || pos < l)
			return;
		segT[segTi][idx] = Math.min(segT[segTi][idx], value);
		if (l == r)
			return;
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, pos, value, segTi);
		update(mid + 1, r, idx * 2 + 1, pos, value, segTi);
	}
}
