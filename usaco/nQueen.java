import java.io.*;
import java.util.*;

public class nQueen {
	private static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			size = Integer.parseInt(in.readLine());
			boolean[][] notPossible = new boolean[size][size];
			List<int[]> ans = recursion(notPossible, new int[size], 0);
			StringBuilder str = new StringBuilder();
			for (int[] a : ans) {
				str.append("[");
				for (int curr : a) {
					str.append(curr + " ");
				}
				str.append("]" + " ");
			}
			
			if (str.length() == 0) {
				System.out.println(-1);
			} else {
				str.setLength(str.length() - 1);
				System.out.println(str.toString());
			}
		}
		in.close();
	}

	private static List<int[]> recursion(boolean[][] notPossible, int[] path, int column) {
		List<int[]> ans = new ArrayList<int[]>();
		if (column == size) {
			int[] copy = Arrays.copyOf(path, path.length);
			ans.add(copy);
			return ans;
		}
		boolean[][] old = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				old[i][k] = notPossible[i][k];
			}
		}
		for (int i = 0; i < size; i++) {
			if (!notPossible[column][i]) {
				for (int z = 0; z < size; z++) {
					notPossible[z][i] = true;
				}
				for (int z = column + 1; z < size; z++) {

					if (i - (z - column) >= 0) {
						notPossible[z][i - (z - column)] = true;
					}
					if (i + (z - column) < size) {
						notPossible[z][i + (z - column)] = true;
					}
				}
				path[column] = i + 1;
				ans.addAll(recursion(notPossible, path, column + 1));
				path[column] = 0;
				notPossible = new boolean[size][size];
				for (int p = 0; p < size; p++) {
					for (int k = 0; k < size; k++) {
						notPossible[p][k] = old[p][k];
					}
				}
			}
		}
		return ans;

	}
}
