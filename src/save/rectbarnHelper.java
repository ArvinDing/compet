package save;

/*
ID: dingarv1
LANG: JAVA
TASK: rectbarn
*/

import java.util.*;
import java.io.*;

public class rectbarnHelper {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("rectbarn.in"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(read.nextToken()), c = Integer.parseInt(read.nextToken()),
				obstacles = Integer.parseInt(read.nextToken());
		boolean[][] done = new boolean[r][c];
		for (int i = 0; i < obstacles; i++) {
			read = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(read.nextToken()) - 1;
			done[row][Integer.parseInt(read.nextToken()) - 1] = true;
		}
		int[] best = new int[5];
		outer: for (int i = 0; i < r; i++) {
			for (int k = 0; k < c; k++) {
				out: for (int z = i; z < r; z++) {
					for (int p = k; p < c; p++) {
						boolean obs = false;
						if (best[0] >= (z - i + 1) * (p - k + 1))
							continue;
						for (int o = i; o <= z; o++) {
							for (int l = k; l <= p; l++) {
								if (done[o][l]) {
									obs = true;
									break;
								}
							}
						}
						if (!obs)
							best = new int[] { (z - i + 1) * (p - k + 1), i, z, k, p };
						else
							continue out;
						if (best[0] == 620)
							break;
					}
				}
			}
		}
		in.close();
	}
}