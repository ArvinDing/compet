package save;

import java.util.Arrays;

public class graph {
	static boolean[] done;
	static int cnt = 0;

	public static void main(String[] args) {
		int[] temp = new int[10];
		done = new boolean[10];
		permute(temp, 0);
		System.out.println(cnt);
		for (int i = 0; i <= 30; i++) {
			if(possible[i])
				System.out.println(i);
		}
	}

	private static boolean check(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}

	private static boolean[] possible = new boolean[31];

	private static void test(int[] temp) {
		int[] begin = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 30; i++) {
			int[] old = Arrays.copyOf(begin, begin.length);
			for (int k = 0; k < 10; k++) {
				begin[temp[k]] = old[k];
			}

			boolean ok = check(begin, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
			if (ok == true) {
				cnt++;
				possible[i + 1] = true;
				return;
			}
		}

		System.out.println("wat");

	}

	private static void permute(int[] temp, int idx) {
		if (idx == 10) {
			test(temp);
		}
		for (int i = 0; i < 10; i++) {
			if (!done[i]) {
				temp[idx] = i;
				done[i] = true;
				permute(temp, idx + 1);
				done[i] = false;
			}
		}
	}
}
