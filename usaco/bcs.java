
import java.io.*;
import java.util.*;

public class bcs {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bcs.in"));
		PrintWriter out = new PrintWriter(new File("bcs.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int dimensions = Integer.parseInt(read.nextToken());
		int grids = Integer.parseInt(read.nextToken());
		boolean[][] goal = new boolean[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			String line = in.readLine();
			for (int k = 0; k < dimensions; k++) {
				goal[i][k] = (line.charAt(k) == '#');
			}
		}
		int[][] minNmax = new int[grids][4];
		boolean[][][] info = new boolean[grids][dimensions][dimensions];
		for (int index = 0; index < grids; index++) {
			int currMinX = Integer.MAX_VALUE;
			int currMinY = Integer.MAX_VALUE;
			int currMaxX = Integer.MIN_VALUE;
			int currMaxY = Integer.MIN_VALUE;
			for (int i = 0; i < dimensions; i++) {
				String line = in.readLine();
				for (int k = 0; k < dimensions; k++) {
					if (line.charAt(k) == '#') {
						info[index][i][k] = true;
						currMinX = Math.min(k, currMinX);
						currMinY = Math.min(i, currMinY);
						currMaxX = Math.max(k, currMaxX);
						currMaxY = Math.max(i, currMaxY);
					}
				}
			}
			minNmax[index] = new int[] { currMinX, currMinY, currMaxX, currMaxY };
		}
		for (int index = 0; index < grids; index++) {

			for (int addX = -minNmax[index][0]; addX <= dimensions - minNmax[index][2] - 1; addX++) {
				for (int addY = -minNmax[index][1]; addY <= dimensions - minNmax[index][3] - 1; addY++) {
					boolean[][] curr = new boolean[dimensions][dimensions];
					for (int i = 0; i < dimensions; i++) {
						for (int k = 0; k < dimensions; k++) {
							if (info[index][i][k]) {
								curr[i + addY][k + addX] = true;
							}
						}
					}
					for (int match = index + 1; match < grids; match++) {
						for (int addMatchX = -minNmax[match][0]; addMatchX <= dimensions - minNmax[match][2]
								- 1; addMatchX++) {
							outer: for (int addMatchY = -minNmax[match][1]; addMatchY <= dimensions - minNmax[match][3]
									- 1; addMatchY++) {
								boolean[][] copy = new boolean[dimensions][dimensions];
								for (int i = 0; i < dimensions; i++) {
									for (int k = 0; k < dimensions; k++) {
										if (curr[i][k])
											copy[i][k] = true;
									}
								}
								for (int i = 0; i < dimensions; i++) {
									for (int k = 0; k < dimensions; k++) {
										if (info[match][i][k]) {
											if (copy[i + addMatchY][k + addMatchX]) {
												continue outer;
											}
											copy[i + addMatchY][k + addMatchX] = true;
										}
									}
								}
//								for (int i = 0; i < dimensions; i++) {
//
//									for (int k = 0; k < dimensions; k++) {
//										if (copy[i][k])
//											System.out.print("X");
//										else
//											System.out.print(".");
//									}
//									System.out.println();
//								}
//								System.out.println();
								if (isSame(copy, goal)) {
									out.println((index + 1) + " " + (match + 1));
								}
							}
						}
					}
				}
			}

		}
		in.close();
		out.close();

	}

	private static boolean isSame(boolean[][] copy, boolean[][] goal) {
		for (int i = 0; i < copy.length; i++) {
			for (int k = 0; k < copy[0].length; k++) {
				if (copy[i][k] != goal[i][k]) {
					return false;
				}
			}
		}
		return true;

	}

}
