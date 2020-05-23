
import java.io.*;
import java.util.*;

public class convexHull {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		PrintWriter out = new PrintWriter(new File("output"));
		int n = Integer.parseInt(in.readLine());
		int[][] info = new int[n][2];
		int lowestY = Integer.MAX_VALUE;
		int saveX = -1;
		for (int i = 0; i < n; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(read.nextToken());
			int y = Integer.parseInt(read.nextToken());
			info[i] = new int[] { x, y };
			if (y < lowestY) {
				lowestY = y;
				saveX = x;
			} else if (y == lowestY) {
				saveX = Math.min(saveX, x);
			}
		}
		final int tempy = lowestY;
		final int tempx = saveX;
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[1] == arg1[1] && arg1[1] == tempy)
					return arg0[0] - arg1[0];
				return Double.compare(Math.atan2((arg0[1]) - tempy, (arg0[0] - tempx)),
						Math.atan2((arg1[1]) - tempy, (arg1[0] - tempx)));

			}
		});

		int[] stack = new int[n];
		int sIdx = 2;
		stack[0] = 0;
		stack[1] = 1;
		for (int i = 2; i < n; i++) {
			if (crossP(info[stack[sIdx - 2]], info[stack[sIdx - 1]], info[i]) > 0) {
				stack[sIdx++] = i;
			} else {
				sIdx--;
				while (sIdx >= 2 && crossP(info[stack[sIdx - 2]], info[stack[sIdx - 1]], info[i]) < 0)
					sIdx--;
				stack[sIdx] = i;
				sIdx++;
			}
		}
		while (crossP(info[stack[sIdx - 2]], info[stack[sIdx - 1]], info[stack[0]]) < 0) {
			sIdx--;
		}
		double real = Integer.MIN_VALUE;
		for (int a = 0; a < sIdx; a++) {
			for (int b = a + 1; b < sIdx; b++) {
				for (int c = b + 1; c < sIdx; c++) {
					real = Math.max(real, Math.abs(crossP(info[stack[a]], info[stack[b]], info[stack[c]]) / (double) 2));

				}
			}
		}
		double max = Integer.MIN_VALUE;
		for (int i = 0; i < sIdx - 2; i++) {
			int[] fixed = info[stack[i]];
			System.out.println(fixed[0]+" "+fixed[1]);
			int second = i + 1;
			int third = i + 2;
			boolean flag = true;
			while (flag) {
				int currArea = crossP(fixed, info[stack[second]], info[stack[third]]);
				max = Math.max(max, Math.abs(currArea / (double) 2));
				if (third + 1 != sIdx)
					if (Math.abs(crossP(fixed, info[stack[second]], info[stack[third + 1]])) > currArea) {
						third++;
						continue;
					}
				if (second < third) {
					second++;
					continue;
				}
				flag = false;

			}
		}
		System.out.println(real + " " + max);
		out.close();
		in.close();

	}

	private static int crossP(int[] a, int[] b, int[] c) {
		// a is first,b-second,c-third
		int[] ab = new int[] { b[0] - a[0], b[1] - a[1] };
		int[] ac = new int[] { c[0] - a[0], c[1] - a[1] };
		return ab[0] * ac[1] - ab[1] * ac[0];
	}

}
