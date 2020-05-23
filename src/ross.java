import java.util.*;

public class ross {
	public static void main(String[] args) {
		done = new boolean[4][4][4][4];
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				for (int c = 0; c < 4; c++) {
					for (int d = 0; d < 4; d++) {
						solve(new int[] { a, b, c, d });
					}
				}
			}
		}
	}

	static boolean[][][][] done;

	static int cnt = 0;

	public static void solve(int[] info) {
		if (done[info[0]][info[1]][info[2]][info[3]])
			return;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (b == a)
					continue;
				for (int c = 0; c < 4; c++) {
					if (b == c || a == c)
						continue;
					for (int d = 0; d < 4; d++) {
						if (a == d || b == d || c == d)
							continue;
						int[] perm = new int[] { a, b, c, d };
						done[perm[info[perm[0]]]][perm[info[perm[1]]]][perm[info[perm[2]]]][perm[info[perm[3]]]] = true;
					}
				}
			}
		}
		char[] alpha = new char[] { 'a', 'b', 'c', 'd' };
		System.out.println("a^2=" + alpha[info[0]] + ", b^2=" + alpha[info[1]] + ", c^2=" + alpha[info[2]] + ", d^2=" + alpha[info[3]]+"\\\\");
		cnt++;

	}
}
