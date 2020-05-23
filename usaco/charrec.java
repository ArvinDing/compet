
/*
ID: dingarv1
LANG: JAVA
TASK: charrec
*/

import java.io.*;
import java.util.*;

public class charrec {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("charrec.in"));
		BufferedReader font = new BufferedReader(new FileReader("font.in"));
		PrintWriter out = new PrintWriter(new File("charrec.out"));
		font.readLine();
		boolean[][][] ideal = new boolean[27][20][20];
		for (int i = 0; i < 27; i++) {
			for (int k = 0; k < 20; k++) {
				char[] read = font.readLine().toCharArray();
				for (int o = 0; o < 20; o++) {
					ideal[i][k][o] = (read[o] == '1');
				}
			}
		}
		int lines = Integer.parseInt(in.readLine());
		boolean[][] input = new boolean[lines][20];
		for (int i = 0; i < lines; i++) {
			char[] read = in.readLine().toCharArray();
			for (int k = 0; k < 20; k++) {
				input[i][k] = (read[k] == '1');
			}
		}

		char[] link = " abcdefghijklmnopqrstuvwxyz?".toCharArray();
		int[][] previous = new int[lines][2];
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[3] - o2[3];
			}
		});
		boolean[] done = new boolean[lines];
		queue.add(new int[] { 0, -1, -1, 0 });
		int index = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currentLines = curr[0];
			if (currentLines == lines) {
				StringBuilder ans = new StringBuilder();
				ans.append(link[curr[1]]);
				int loop = curr[2];
				while (loop > 0) {
					ans.append(link[previous[loop][0]]);
					loop = previous[loop][1];
				}
				out.println(ans.reverse().toString());
				break;
			}
			if (done[currentLines])
				continue;
			done[currentLines] = true;
			int best19 = Integer.MAX_VALUE;
			int best20 = Integer.MAX_VALUE;
			int best21 = Integer.MAX_VALUE;
			int index19 = -1;
			int index20 = -1;
			int index21 = -1;

			for (int i = 0; i < 27; i++) {

				int[] equalD = new int[20];
				int[] addD = new int[21];
				int[] deleteD = new int[19];
				if (lines - currentLines >= 19) {
					for (int add = 0; add < 19; add++) {
						for (int k = 0; k < 20; k++) {
							if (input[currentLines + add][k] != ideal[i][add][k])
								equalD[add]++;
						}
					}
					int sum = 0;
					for (int add = 0; add < 19; add++) {
						for (int k = 0; k < 20; k++) {
							if (input[currentLines + add][k] != ideal[i][add + 1][k])
								deleteD[add]++;
						}
						sum += deleteD[add];
					}
					int min = sum;
					for (int equals = 0; equals < 19; equals++) {
						sum += equalD[equals];
						sum -= deleteD[equals];
						min = Math.min(min, sum);
					}

					if (min < best19) {
						best19 = min;
						index19 = i;
					}
					if (lines - currentLines >= 20) {
						for (int k = 0; k < 20; k++) {
							if (input[currentLines + 19][k] != ideal[i][19][k])
								equalD[19]++;
						}
						sum += equalD[19];
						if (sum < best20) {
							best20 = sum;
							index20 = i;
						}
						if (lines - currentLines >= 21) {
							sum = 0;
							for (int add = 1; add < 21; add++) {
								for (int k = 0; k < 20; k++) {
									if (input[currentLines + add][k] != ideal[i][add - 1][k])
										addD[add]++;
								}
								sum += addD[add];
							}
							min = sum;

							for (int p = 0; p < 20; p++) {
								sum += equalD[p];
								sum -= addD[p + 1];
								min = Math.min(sum, min);
							}
							if (min < best21) {
								best21 = min;
								index21 = i;
							}
						}
					}

				}

			}
			previous[index] = new int[] { curr[1], curr[2] };
			queue.add(new int[] { currentLines + 19, (best19 <= 120) ? index19 : 28, index, curr[3] + best19 });
			queue.add(new int[] { currentLines + 20, (best20 <= 120) ? index20 : 28, index, curr[3] + best20 });
			queue.add(new int[] { currentLines + 21, (best21 <= 120) ? index21 : 28, index, curr[3] + best21 });
			index++;
		}
		font.close();
		in.close();
		out.close();
	}
}
