import java.math.*;
import java.util.Arrays;
import java.util.HashSet;

public class piss {
	public static void main(String[] args) {
		for (int i = 2; i < 12; i++) {
			max = 0;
			done = new boolean[i];
			temp = new int[i];
			test(i, 0);
			System.out.println("Urinals " + i + ": " + max + " intersects");
			for (int z = 0; z < best.length; z++) {
				System.out.print(best[z]+1 + " ");
			}
			System.out.println();
		}
	}

	public static long a(int n) {
		max = 0;
		done = new boolean[n];
		temp = new int[n];
		test(n, 0);
		return max;
	}

	static int max = 0;
	static boolean[] done;
	static int[] temp;
	static int[] best;

	public static void test(int n, int idx) {
		if (idx == n) {
			nIn(temp, n);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!done[i]) {
				temp[idx] = i;
				done[i] = true;
				test(n, idx + 1);
				done[i] = false;
			}
		}

	}

	public static void nIn(int[] in, int n) {

		HashSet<point> lol = new HashSet<point>();
		int poss = (n * (n - 1)) / 2;
		for (int i = 0; i < n; i++) {
			for (int k = i + 1; k < n; k++) {
				point a = cross(i, in[i], k, in[k], n);
				if (a != null)
					lol.add(a);
				poss--;
				if (lol.size() + poss <= max)
					return;

			}
		}

		if (lol.size() > max) {
			max = lol.size();
			best = Arrays.copyOf(in, in.length);
		}

	}

	public static point cross(int a, int a1, int b, int b1, int n) {
		int[] pA = new int[] { a, 0 };
		int[] pA1 = new int[] { a1, n };
		int[] pB = new int[] { b, 0 };
		int[] pB1 = new int[] { b1, n };
		long[] slopeA = null;
		if (pA1[0] - pA[0] != 0)
			slopeA = new long[] { (pA1[1] - pA[1]), (pA1[0] - pA[0]) };
		long[] slopeB = null;
		if (pB1[0] - pB[0] != 0)
			slopeB = new long[] { (pB1[1] - pB[1]), (pB1[0] - pB[0]) };
		if (slopeA == null && slopeB == null)
			return null;
		if (slopeA == null) {
			long[] constantB = sub(new long[] { pB[1], 1 }, mult(slopeB, new long[] { pB[0], 1 }));
			return specialC(a, slopeB, constantB, n);
		}
		if (slopeB == null) {
			long[] constantA = sub(new long[] { pA[1], 1 }, mult(slopeA, new long[] { pA[0], 1 }));
			return specialC(b, slopeA, constantA, n);
		}
		long[] constantA = sub(new long[] { pA[1], 1 }, mult(slopeA, new long[] { pA[0], 1 }));
		long[] constantB = sub(new long[] { pB[1], 1 }, mult(slopeB, new long[] { pB[0], 1 }));

		if (slopeA == slopeB)
			return null;

		long[] x = div(sub(constantB, constantA), sub(slopeA, slopeB));

		long[] y = add(mult(x, slopeA), constantA);
		double xAp = (double) x[0] / x[1];
		double yAp = (double) y[0] / y[1];

		if (0 <= xAp && xAp <= n - 1 && 0 <= yAp && yAp <= n) {

			return new point(x[0], x[1], y[0], y[1]);
		}
		return null;
	}

	public static point specialC(long x, long[] slope, long[] constant, long n) {
		long[] lol = add(mult(slope, new long[] { x, 1 }), constant);
		double yAp = (double) lol[0] / lol[1];
		if (yAp < 0 || yAp > n)
			return null;

		return new point(x, 1, lol[0], lol[1]);
	}

	public static long[] add(long[] a, long[] b) {
		return new long[] { a[0] * b[1] + a[1] * b[0], a[1] * b[1] };
	}

	public static long[] sub(long[] a, long[] b) {
		return new long[] { a[0] * b[1] - a[1] * b[0], a[1] * b[1] };
	}

	public static long[] mult(long[] a, long[] b) {
		return new long[] { a[0] * b[0], a[1] * b[1] };
	}

	public static long[] div(long[] a, long[] b) {
		return new long[] { a[0] * b[1], a[1] * b[0] };
	}

	public static class point {
		long xN;
		long xD;
		long yN;
		long yD;

		public point(long xN, long xD, long yN, long yD) {
			this.xN = xN;
			this.xD = xD;

			this.yN = yN;
			this.yD = yD;

		}

		@Override
		public int hashCode() {
			return (int) Math.floorDiv(xN, xD);
		}

		@Override
		public boolean equals(Object obj) {
			point a = (point) obj;

			return (xD * a.xN == xN * a.xD && yD * a.yN == yN * a.yD);
		}
	}
}