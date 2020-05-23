
import java.io.*;
import java.util.*;

public class cmpls {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(read.nextToken());
			int find = Integer.parseInt(read.nextToken());

			int[][] info = new int[num + find][num + find];
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < num; k++) {
				info[0][k] = Integer.parseInt(read.nextToken());
			}
			if (num != 1) {
				int save = -1;
				for (int z = 1; z < num; z++) {
					boolean same = true;
					info[z][0] = info[z - 1][1] - info[z - 1][0];
					for (int k = 1; k < num - z; k++) {
						info[z][k] = info[z - 1][k + 1] - info[z - 1][k];
						if (info[z][k - 1] != info[z][k])
							same = false;
					}
					if (same) {
						save = z;
						break;
					}
				}
				for (int k = 0; k < find; k++) {
					int curr = k + (num - save);
					info[save][curr] = info[save][curr - 1];
					for (int diagonal = 1; save - diagonal >= 0; diagonal++) {
						info[save - diagonal][curr + diagonal] = info[save - diagonal][curr + diagonal - 1]
								+ info[save - diagonal + 1][curr + diagonal - 1];
					}
					if (k != 0)
						System.out.print(" ");
					System.out.print(info[0][num+k]);
				}
				System.out.println();
			} else {
				for (int z = 0; z < find; z++) {
					if (z != 0)
						System.out.print(" ");
					System.out.print(info[0][0]);
				}
				
			System.out.println();
			}

		}
		in.close();
	}

}