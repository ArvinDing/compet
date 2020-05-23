
import java.io.*;
import java.util.*;

public class sir {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
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
	
		
		in.close();

	}

	private static int crossP(int[] a, int[] b, int[] c) {
		// a is first,b-second,c-third
		int[] ab = new int[] { b[0] - a[0], b[1] - a[1] };
		int[] ac = new int[] { c[0] - a[0], c[1] - a[1] };
		return ab[0] * ac[1] - ab[1] * ac[0];
	}

}
