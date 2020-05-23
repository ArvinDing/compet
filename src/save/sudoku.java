package save;
import java.io.*;
import java.util.*;

public class sudoku {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("bsudoku.in"));
		PrintWriter out = new PrintWriter(new File("bsudoku.out"));
		boolean[][] info = new boolean[9][9];
		int[][][] dp = new int[1 << 9][1 << 3][10];
		// columns even or odd, boxes even or odd, row curr
		for (int i = 0; i < 9; i++) {
			String line = in.readLine();
			for (int k = 0; k < 9; k++) {
				info[i][k] = line.charAt(k) == '1';
			}
		}
		for (int i = 0; i < 1 << 9; i++) {
			for (int k = 0; k < 1 << 3; k++) {
				for (int z = 1; z <= 9; z++) {
					dp[i][k][z] = -1;
				}
			}
		}
		LinkedList<int[]> next = new LinkedList<int[]>();
		next.add(new int[] { 0, 0 });
		for (int i = 1; i <= 9; i++) {
			int colC = 0;
			int boxC = 0;
			int temp = 0;

			if (i == 4 || i == 7) {
				for (int z = 0; z < 1 << 9; z++) {
					for (int p = 1; p < 1 << 3; p++) {
						dp[z][p][i - 1] = 100;
					}
				}
			}
			for (int k = 0; k < 9; k++) {
				if (info[i - 1][k]) {
					colC = colC | (1 << k);
					temp++;
				}

				if ((k + 1) % 3 == 0) {
					if (temp % 2 == 1)
						boxC = boxC | (1 << (k / 3));
					temp = 0;

				}

			}
			PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] arg0, int[] arg1) {
					return arg0[2] - arg1[2];
				}
			});
			for (int[] a : next) {
				int colP = a[0];
				int boxP = a[1];
				if (isEvenParity(colP))
					queue.add(new int[] { colP ^ colC, boxP ^ boxC, dp[colP][boxP][i - 1] });
			
			}
			boolean[][] done = new boolean[1 << 9][1 << 3];
			next = new LinkedList<int[]>();
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				if (done[curr[0]][curr[1]])
					continue;
				done[curr[0]][curr[1]] = true;
				next.add(new int[] { curr[0], curr[1] });
				if (dp[curr[0]][curr[1]][i] == -1)
					dp[curr[0]][curr[1]][i] = curr[2];
				for (int z = 0; z < 9; z++) {
					if (dp[curr[0] ^ (1 << z)][curr[1] ^ (1 << z / 3)][i] == -1) {
						queue.add(new int[] { curr[0] ^ (1 << z), curr[1] ^ (1 << z / 3), curr[2] + 1 });
						dp[curr[0] ^ (1 << z)][curr[1] ^ (1 << z / 3)][i] = curr[2] + 1;
					}
				}

			}

		}
		int min = 100;
		for (int i = 0; i < 1 << 9; i++) {
			if (isEvenParity(i)) {
				if (dp[i][0][9] != -1)
					min = Math.min(min, dp[i][0][9]);
			}
		}
		out.println(min);
		out.close();
		in.close();
	}

	public static boolean isEvenParity(int num) {
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			if ((num & (1 << i)) == (1<<i))
				cnt++;
		}
		return (cnt % 2 == 0);
	}
}
