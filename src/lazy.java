
import java.io.*;
import java.util.*;

public class lazy {
	private static int[] segT;
	private static int[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new File("lazy.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
	//	System.out.println(System.currentTimeMillis());
		segT = new int[4 * 2000001];
		lazy = new int[4 * 2000001];
	//	System.out.println(System.currentTimeMillis());
		// total,min,lazy
		int[][] info = new int[2 * n][3];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			int grass = Integer.parseInt(read.nextToken());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			// System.out.println(grass + "- " + ((x - y) / Math.sqrt(2)) + " " + ((x + y) /
			// Math.sqrt(2)));
			info[i] = new int[] { grass, x - y - k, x + y };
			info[i + n] = new int[] { -grass, x - y + k + 1, x + y };

		}
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		int prevX = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 2 * n; i++) {
			if (info[i][1] != prevX) {
				max = Math.max(max, segT[1]);
			}
			update(0, 2000001, info[i][2] - k, info[i][2] + k, 1, info[i][0]);
			prevX = info[i][1];

		}
		out.println(Math.max(max, segT[1]));

		out.close();
		in.close();
	}

	private static void update(int l, int r, int lx, int rx, int idx, int value) {
		if (rx < l || lx > r)
			return;
		if (lx <= l && r <= rx) {
			segT[idx] += value;
			lazy[idx] += value;
			return;
		}
		int mid = (l + r) / 2;
		update(l, mid, l, mid, idx * 2, lazy[idx]);
		update(mid + 1, r, mid + 1, r, idx * 2 + 1, lazy[idx]);
		lazy[idx] = 0;
		update(l, mid, lx, rx, idx * 2, value);
		update(mid + 1, r, lx, rx, idx * 2 + 1, value);
		segT[idx] = Math.max(segT[idx * 2], segT[idx * 2 + 1]);
	}

}
