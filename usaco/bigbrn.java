
/*
 ID: dingarv1
 PROB: bigbrn
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class bigbrn {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bigbrn.in"));
		PrintWriter out = new PrintWriter(new File("bigbrn.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int side = Integer.parseInt(read.nextToken());
		int obstacles = Integer.parseInt(read.nextToken());
		boolean[][] info = new boolean[side][side];
		for (int i = 0; i < obstacles; i++) {
			read = new StringTokenizer(in.readLine());
			info[Integer.parseInt(read.nextToken())-1][Integer.parseInt(read.nextToken())-1] = true;
		}
		int max = 0;
		int[][] save = new int[side][side];
		int times = 0;
		for (int sum = side + side - 2; sum >= 0; sum--) {
			int lower=Math.max(0, side - times - 1);
			int upper=Math.min(sum, side-1);
			for (int i = lower; i <= upper; i++) {
				int k = sum - i;

				if (!info[i][k]) {
					if (i + 1 == side || k + 1 == side || info[i + 1][k] || info[i][k + 1]) {
						save[i][k] = 1;
					} else {
						int iAdd = save[i + 1][k];
						int kAdd = save[i][k + 1];
						if (iAdd == kAdd) {
							if (!info[i + iAdd][k + iAdd]) {
								save[i][k] = iAdd + 1;
							} else {
								save[i][k] = iAdd;
							}
						} else {
							save[i][k] = Math.min(iAdd, kAdd) + 1;
						}
					}
					max = Math.max(save[i][k], max);
				}
			}
			times++;
		}
//		for (int i = 0; i < save.length; i++) {
//			for (int k = 0; k < save.length; k++) {
//				System.out.print(save[i][k] + "|");
//			
//			}
//			System.out.println();
//		}
		out.println(max);
		in.close();
		out.close();
	}

}
