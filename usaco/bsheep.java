import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class bsheep {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			in.readLine();
			int sheep = Integer.parseInt(in.readLine());
			int[][] info = new int[sheep][4];
			int[][] link = new int[sheep][2];
			int minY = Integer.MAX_VALUE;
			int minX = 0;
			for (int k = 0; k < sheep; k++) {
				StringTokenizer read = new StringTokenizer(in.readLine());
				info[k][0] = Integer.parseInt(read.nextToken());
				info[k][1] = Integer.parseInt(read.nextToken());
				info[k][3] = k;
				link[k] = new int[] { info[k][0], info[k][1] };
				if (minY == info[k][1]) {
					minX = Math.min(minX, info[k][0]);
				} else if (minY > info[k][1]) {
					minY = info[k][1];
					minX = info[k][0];

				}
			}
			for (int k = 0; k < sheep; k++) {
				int aComp = 1000199999;
				if (info[k][1] - minY != 0) {
					aComp = (10000 * (info[k][0] - minX) / (info[k][1] - minY));
				}
				if (info[k][0] == minX && info[k][1] == minY) {
					aComp = 1000200000;
				}
				info[k][2] = aComp;
			}
			Arrays.sort(info, new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return b[2] - a[2];
				}
			});
			LinkedList<int[]> queue = new LinkedList<int[]>();
			for (int k = 0; k < sheep; k++) {
				int[] previous = info[k];
				while (k != sheep - 1 && info[k + 1][2] == previous[2]) {
					k++;
					if (k == sheep)
						break;
					double a = dist(info[k], new int[] { minY, minX });
					double b = dist(previous, new int[] { minY, minX });
					if (a > b) {
						previous = info[k];
					} else if (a == b) {
						if (info[k][3] < previous[3]) {
							previous = info[k];
						}
					}
				}
				queue.add(previous);
			}

			Stack<int[]> hull = new Stack<int[]>();
			int[] temp = queue.poll();
			if (!queue.isEmpty())
				queue.add(temp);
			hull.add(temp);
			if (!queue.isEmpty())
				hull.add(queue.poll());
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int[] back1 = hull.pop();
				int[] back2 = hull.pop();
				while (!hull.isEmpty() && determinant(curr, back1, back2) >= 0) {

					back1 = back2;
					back2 = hull.pop();
				}
				
				if (determinant(curr, back1, back2) >= 0) {
					hull.add(back2);
				}else {
					hull.add(back2);
					hull.add(back1);
				}
				if (!queue.isEmpty())
					hull.add(curr);
			}
			LinkedList<Integer> l = new LinkedList<Integer>();
			int last = hull.pop()[3];
			l.addFirst(last);
			int previous = last;
			double sum = 0;
			while (!hull.isEmpty()) {
				int curr = hull.pop()[3];
				l.addFirst(curr);
				sum += dist(link[curr], link[previous]);
				if (hull.isEmpty()) {
					sum += dist(link[curr], link[last]);
				}
				previous = curr;
			}
			DecimalFormat df = new DecimalFormat("####0.00");
			System.out.println(df.format(new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue()));
			System.out.print((l.poll() + 1));
			for (int a : l)
				System.out.print(" " + (a + 1));
			System.out.println();
			if (i != test - 1)
				System.out.println();
		}

		in.close();
	}

	private static int determinant(int[] a, int[] b, int[] c) {
		int[] modifiedA = new int[] { a[0] - b[0], a[1] - b[1] };
		int[] modifiedB = new int[] { c[0] - b[0], c[1] - b[1] };
		return (modifiedA[1] * modifiedB[0]) - (modifiedA[0] * modifiedB[1]);
	}

	private static double dist(int[] x, int[] x2) {
		return Math.sqrt((x[0] - x2[0]) * (x[0] - x2[0]) + (x[1] - x2[1]) * (x[1] - x2[1]));
	}
}
