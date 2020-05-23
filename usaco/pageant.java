
import java.io.*;
import java.util.*;

public class pageant {
	private static int height;
	private static int width;
	private static int[][] info;
	private static int[][] direction;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("pageant.in"));
		PrintWriter out = new PrintWriter(new File("pageant.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		height = Integer.parseInt(read.nextToken());
		width = Integer.parseInt(read.nextToken());
		info = new int[height][width];

		for (int i = 0; i < height; i++) {
			char[] line = in.readLine().toCharArray();
			for (int k = 0; k < width; k++) {
				info[i][k] = (line[k] == 'X') ? 4 : 0;
			}
		}
		int currIndex = 1;
		direction = new int[4][2];
		direction[0] = new int[] { 0, 1 };
		direction[1] = new int[] { 1, 0 };
		direction[2] = new int[] { 0, -1 };
		direction[3] = new int[] { -1, 0 };

		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				if (info[i][k] == 4) {
					LinkedList<int[]> queue = new LinkedList<int[]>();
					queue.add(new int[] { i, k });
					info[i][k]=currIndex;
					while (!queue.isEmpty()) {
						int[] curr = queue.poll();
						int y = curr[0];
						int x = curr[1];
						
						for (int thing = 0; thing < 4; thing++) {
							if (0 <= y + direction[thing][0] && y + direction[thing][0] < height
									&& 0 <= x + direction[thing][1] && x + direction[thing][1] < width
									&& info[y + direction[thing][0]][x + direction[thing][1]] == 4) {
								queue.add(new int[] { y + direction[thing][0], x + direction[thing][1] });
								info[y + direction[thing][0]][x + direction[thing][1]] = currIndex;
							}
						}
					}
					currIndex++;
				}
			}
		}
	
		int[][][] distancetoAll = new int[height][width][3];
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				for (int a = 0; a < 3; a++) {
					distancetoAll[i][k][a] = Integer.MAX_VALUE;
				}
			}
		}
		int[][] minDist = new int[3][3];
		Arrays.fill(minDist[0], Integer.MAX_VALUE);
		Arrays.fill(minDist[1], Integer.MAX_VALUE);
		Arrays.fill(minDist[2], Integer.MAX_VALUE);

		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				for (int o = 0; o < height; o++) {
					for (int p = 0; p < width; p++) {
						int distance = Math.abs(i - o) + Math.abs(k - p) - 1;
						if (info[i][k] != 0 && info[o][p] != 0) {
							minDist[info[i][k] - 1][info[o][p] - 1] = Math.min(minDist[info[i][k] - 1][info[o][p] - 1],
									distance);
						} else if (info[i][k] != 0) {

							distancetoAll[o][p][info[i][k] - 1] = Math.min(distancetoAll[o][p][info[i][k] - 1],
									distance);
						
						} else if (info[o][p] != 0) {
							distancetoAll[i][k][info[o][p] - 1] = Math.min(distancetoAll[i][k][info[o][p] - 1],
									distance);

						}
					}
				}
			}
		}
		int d1 = Math.min(minDist[0][1], minDist[1][0]);
		int d2 = Math.min(minDist[1][2], minDist[2][1]);
		int d3 = Math.min(minDist[2][0], minDist[0][2]);
		int ans = Math.min(d1 + d3, Math.min(d2 + d3, d1 + d2));
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				if (info[i][k] == 0) {
					int sum = 1;
					for (int z = 0; z < 3; z++) {
						sum += distancetoAll[i][k][z];
					}
					ans = Math.min(ans, sum);
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}

}
