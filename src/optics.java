
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class optics {
	private static TreeMap<Integer, TreeMap<Integer, int[]>> yx;
	private static TreeMap<Integer, TreeMap<Integer, int[]>> xy;
	private static int bound = 1000000001;
	private static int n;
	private static int[] smallerX;
	private static int[] segT;
	private static int modifiedL;
	private static TreeSet<Integer> compressX;
	private static boolean[][] done;
	private static LinkedList<int[]> queue;
	private static boolean barn = true;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("optics.in"));
		PrintWriter out = new PrintWriter(new File("optics.out"));
		long curr = System.currentTimeMillis();
		StringTokenizer read = new StringTokenizer(in.readLine());
		yx = new TreeMap<Integer, TreeMap<Integer, int[]>>();
		xy = new TreeMap<Integer, TreeMap<Integer, int[]>>();
		n = Integer.parseInt(read.nextToken());
		int bx = Integer.parseInt(read.nextToken());
		int by = Integer.parseInt(read.nextToken());
		compressX = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			boolean forward = read.nextToken().equals("/");
			add(x, y, forward, i);
		}
		compressX.add(0);
		compressX.add(bx);
		compressX.add(bound);
		compressX.add(-bound);
		modifiedL = compressX.size();
		smallerX = new int[modifiedL];
		System.out.println(System.currentTimeMillis() - curr);
		int idx = 0;
		for (int a : compressX)
			smallerX[idx++] = a;

		LinkedList<int[]> bVer = new LinkedList<int[]>();
		LinkedList<int[]> bHor = new LinkedList<int[]>();
		queue = new LinkedList<int[]>();
		done = new boolean[n + 1][4];
		add(bx, by, true, -1);
		add(0, 0, true, -1);
		queue.add(new int[] { bx, by, 0, });
		caculate(bVer, bHor, bx, by);
		queue.add(new int[] { bx, by, 1 });
		caculate(bVer, bHor, bx, by);
		queue.add(new int[] { bx, by, 2 });
		caculate(bVer, bHor, bx, by);
		queue.add(new int[] { bx, by, 3 });
		caculate(bVer, bHor, bx, by);
		System.out.println(System.currentTimeMillis() - curr);
		LinkedList<int[]> ver = new LinkedList<int[]>();
		LinkedList<int[]> hor = new LinkedList<int[]>();
		done = new boolean[n + 1][4];
		barn = false;
		queue.add(new int[] { 0, 0, 2 });
		caculate(ver, hor, 0, 0);
		segT = new int[4 * modifiedL];
		int add = intersect(bVer, hor);
		segT = new int[4 * modifiedL];
		int add1 = intersect(ver, bHor);
		System.out.println(System.currentTimeMillis() - curr);
		out.println(add + add1);
		in.close();
		out.close();
	}

	private static void add(int x, int y, boolean forward, int i) {
		if (!yx.containsKey(y))
			yx.put(y, new TreeMap<Integer, int[]>());
		if (!xy.containsKey(x))
			xy.put(x, new TreeMap<Integer, int[]>());
		compressX.add(x);
		yx.get(y).put(x, new int[] { forward ? 1 : 0, i + 1 });
		xy.get(x).put(y, new int[] { forward ? 1 : 0, i + 1 });
	}

	private static int intersect(LinkedList<int[]> ver, LinkedList<int[]> hor) {
		Comparator<int[]> ySort = new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		};
		Collections.sort(hor, ySort);
		Collections.sort(ver, ySort);
		int ans = 0;
		while (!hor.isEmpty()) {
			int yVer = bound + 1;
			if (!ver.isEmpty()) {
				yVer = ver.peek()[0];
			}
			if (hor.peek()[0] < yVer) {
				int[] curr = hor.poll();
				int a = Arrays.binarySearch(smallerX, curr[1]) + 1;
				int b = Arrays.binarySearch(smallerX, curr[2]) - 1;
				if (b >= a)
					ans += query(0, modifiedL - 1, 1, a, b);
			} else {
				int[] curr = ver.poll();
				int diff = Arrays.binarySearch(smallerX, curr[1]);
				update(0, modifiedL - 1, 1, diff, curr[2]);
			}

		}
		return ans;
	}

	static int query(int l, int r, int idx, int lx, int rx) {
		if (rx < l || r < lx)
			return 0;
		if (lx <= l && r <= rx)
			return segT[idx];
		int mid = (l + r) / 2;
		int res = query(l, mid, idx * 2, lx, rx);
		int res1 = query(mid + 1, r, idx * 2 + 1, lx, rx);
		return res + res1;
	}

	static void update(int l, int r, int idx, int pos, int value) {
		if (pos < l || r < pos)
			return;
		segT[idx] += value;
		if (l == r)
			return;
		int mid = (l + r) / 2;
		update(l, mid, idx * 2, pos, value);
		update(mid + 1, r, idx * 2 + 1, pos, value);

	}

	private static void caculate(LinkedList<int[]> ver, LinkedList<int[]> hor, int startX, int startY) {
		// +x,-x,+y,-y
		boolean first = true;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			if (barn && x == 0 && y == 0)
				continue;
			int direction = curr[2];
			// System.out.println(x + " " + y + " " + direction);
			if (!first && x == startX && y == startY)
				return;
			first = false;
			if (direction == 0) {
				Entry<Integer, int[]> nextX;
				if (!yx.containsKey(y) || (nextX = yx.get(y).higherEntry(x)) == null) {
					hor.add(new int[] { y, x, bound });
					continue;
				}
				int newDirect = ((nextX.getValue())[0] == 1) ? 2 : 3;
				int mirrorI = nextX.getValue()[1];
				if (!done[mirrorI][newDirect]) {
					queue.add(new int[] { nextX.getKey(), y, newDirect });
					hor.add(new int[] { y, x, nextX.getKey() });
					done[mirrorI][newDirect] = true;
					done[mirrorI][1] = true;
				}

			} else if (direction == 1) {
				Entry<Integer, int[]> nextX;
				if (!yx.containsKey(y) || (nextX = yx.get(y).lowerEntry(x)) == null) {
					hor.add(new int[] { y, -bound, x });
					continue;
				}
				int newDirect = ((nextX.getValue())[0] == 1) ? 3 : 2;
				int mirrorI = nextX.getValue()[1];
				if (!done[mirrorI][newDirect]) {
					queue.add(new int[] { nextX.getKey(), y, newDirect });
					hor.add(new int[] { y, nextX.getKey(), x });
					done[mirrorI][newDirect] = true;
					done[mirrorI][0] = true;
				}
			} else if (direction == 2) {
				Entry<Integer, int[]> nextY;
				if (!xy.containsKey(x) || (nextY = xy.get(x).higherEntry(y)) == null) {
					ver.add(new int[] { y, x, 1 });
					ver.add(new int[] { bound, x, -1 });
					continue;
				}
				int newDirect = ((nextY.getValue())[0] == 1) ? 0 : 1;
				int mirrorI = nextY.getValue()[1];
				if (!done[mirrorI][newDirect]) {
					queue.add(new int[] { x, nextY.getKey(), newDirect });
					ver.add(new int[] { y, x, 1 });
					ver.add(new int[] { nextY.getKey() + 1, x, -1 });
					done[mirrorI][newDirect] = true;
					done[mirrorI][3] = true;
				}
			} else if (direction == 3) {
				Entry<Integer, int[]> nextY;
				if (!xy.containsKey(x) || (nextY = xy.get(x).lowerEntry(y)) == null) {
					ver.add(new int[] { -bound, x, 1 });
					ver.add(new int[] { y, x, -1 });
					continue;
				}
				int newDirect = ((nextY.getValue())[0] == 1) ? 1 : 0;
				int mirrorI = nextY.getValue()[1];
				if (!done[mirrorI][newDirect]) {
					queue.add(new int[] { x, nextY.getKey(), newDirect });
					ver.add(new int[] { nextY.getKey(), x, 1 });
					ver.add(new int[] { y + 1, x, -1 });
					done[mirrorI][newDirect] = true;
					done[mirrorI][2] = true;
				}

			}
		}
	}

}
