
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class test {

	public static void main(String[] args) throws Exception {
		int n = 94;
		int[][] array = new int[n][n];
		int r = 0, c = 0;
		for (int i = 1; i <= 94 * 94; i++) {
			if (array[r][c] != 0)
				c = (c + 1) % n;
			array[r][c] = i;
			r = (r + 1) % n;
			c = (c + 1) % n;
		}
		// for (int i = 0; i < 94; i++) {
		// for (int j = 0; j < 94; j++) {
		// System.out.print(array[i][j] + " ");
		// }
		// System.out.println();
		// }
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < 94; i++) {
			for (int j = i + 1; j < 94; j++) {

				smallest = Math.min(smallest, Math.abs(array[0][j] - array[0][i]));
			}
		}
		System.out.println(smallest);
	}

}
