
import java.io.*;
import java.util.*;

public class grid {

	static String gridChallenge(String[] grid) {
		int dimension = grid.length;
		char[][] info = new char[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			info[i] = grid[i].toCharArray();
			Arrays.sort(info[i]);
		}
		for (int i = 0; i < dimension; i++) {
			int previous = -1;
			for (int k = 0; k < dimension; k++) {
				if (info[k][i] < previous) {
				
					return "NO";
				}
				previous = info[k][i];
			}
		}
		return "YES";
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			String[] grid = new String[n];
			for (int grid_i = 0; grid_i < n; grid_i++) {
				grid[grid_i] = in.next();
			}
			String result = gridChallenge(grid);
			System.out.println(result);
		}
		in.close();
	}

}
