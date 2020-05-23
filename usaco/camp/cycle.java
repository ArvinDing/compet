package camp;

import java.io.*;
import java.util.*;

public class cycle {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		int lK = Integer.bitCount(k);
		long[][][] save = new long[lK + 1][n][n];
		for (int i = 0; i < n; i++) {
			read = new StringTokenizer(in.readLine());
			for (int z = 0; z < n; z++) {
				save[0][i][z] = Integer.parseInt(read.nextToken());
				for (int p = 1; p <= lK; p++)
					save[p][i][z] = Long.MAX_VALUE/2;
			}
		}

		for (int i = 1; i <= lK; i++) {
			for (int z = 0; z < n; z++) {
				for (int o = 0; o < n; o++) {
					if (save[i - 1][z][o] == 0)
						continue;
					for (int p = 0; p < n; p++) {
						if (save[i - 1][o][p] == 0)
							continue;
						save[i][z][p] = Math.min(save[i][z][p], save[i - 1][z][o] + save[i - 1][o][p]);
					}
				}
			}
			for (int z = 0; z < n; z++) {
				for (int o = 0; o < n; o++) {
					if (save[i][z][o] == Long.MAX_VALUE/2)
						save[i][z][o] = 0;
				}
			}
			
		}
		long[][] previous = new long[n][n];
		long imp = 1;
		boolean first = true;
		for (int i = 0; i <= lK; i++) {
			if ((k & imp) == imp) {
				if (first) {
					for (int z = 0; z < n; z++) {
						for (int p = 0; p < n; p++) {
							previous[z][p] = save[i][z][p];
						}
					}
					first = false;
					imp = imp << 1;
					continue;
				}
				long[][] old = new long[n][n];
				for (int z = 0; z < n; z++) {
					for (int p = 0; p < n; p++) {
						old[z][p] = previous[z][p];
						previous[z][p] = Long.MAX_VALUE/2;
					}
				}

				for (int z = 0; z < n; z++) {
					for (int o = 0; o < n; o++) {
						for (int p = 0; p < n; p++) {
							if (old[z][o] != 0 && save[i][o][p] != 0)
								previous[z][p] = Math.min(previous[z][p], old[z][o] + save[i][o][p]);
						}
					}
				}
				for (int z = 0; z < n; z++) {
					for (int o = 0; o < n; o++) {
						if (previous[z][o] == Long.MAX_VALUE/2)
							previous[z][o] = 0;
					}
				}

			}
			imp = imp << 1;

		}
		System.out.println(previous[0][n - 1]);
		in.close();
	}

	private static void print(long[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int k = 0; k < a[0].length; k++) {
				System.out.print(a[i][k] + " ");
			}
			System.out.println();
		}
	}

}
