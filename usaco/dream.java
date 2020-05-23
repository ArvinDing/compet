import java.io.*;
import java.util.*;

public class dream {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new FileWriter("dream.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int vertical = Integer.parseInt(read.nextToken());
		int horizontal = Integer.parseInt(read.nextToken());
		int[][] info = new int[vertical][horizontal];
		for (int i = 0; i < vertical; i++) {
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < horizontal; k++) {
				info[i][k] = Integer.parseInt(read.nextToken());
			}
		}
		boolean[][][] done = new boolean[vertical][horizontal][2];
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		queue.add(new int[] { 0, 0, 0, 0 });
		boolean flag = false;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if (done[curr[1]][curr[2]][curr[3]])
				continue;
			done[curr[1]][curr[2]][curr[3]] = true;
			if (curr[1] == vertical - 1 && curr[2] == horizontal - 1) {
				out.println(curr[0]);
				flag = true;
				break;
			}
			for (int yAdd = -1; yAdd <= 1; yAdd++) {
				for (int xAdd = -1; xAdd <= 1; xAdd++) {
					if (yAdd == 0 ^ xAdd == 0) {
						if (curr[1] + yAdd < 0 || curr[1] + yAdd == vertical || curr[2] + xAdd < 0
								|| curr[2] + xAdd == horizontal)
							continue;
						int next = info[curr[1] + yAdd][curr[2] + xAdd];
						if (next == 0 || (next == 3 && curr[3] == 0))
							continue;
						if (next == 2 && !done[curr[1] + yAdd][curr[2] + xAdd][1]) {
							queue.add(new int[] { curr[0] + 1, curr[1] + yAdd, curr[2] + xAdd, 1 });
						} else if ((next == 3 || next == 1) && !done[curr[1] + yAdd][curr[2] + xAdd][curr[3]]) {
							queue.add(new int[] { curr[0] + 1, curr[1] + yAdd, curr[2] + xAdd, curr[3] });
						} else if (next == 4) {
							int y = curr[1] + yAdd;
							int x = curr[2] + xAdd;
							int add = 1;
							while (info[y][x] == 4) {
								if (y + yAdd < 0 || y + yAdd == vertical || x + xAdd < 0 || x + xAdd == horizontal)
									break;
								if (info[y + yAdd][x + xAdd] == 0 || info[y + yAdd][x + xAdd] == 3)
									break;
								y += yAdd;
								x += xAdd;
								add++;

							}
							queue.add(new int[] { curr[0] + add, y, x, 0 });
						}
					}
				}
			}
		}
		if (!flag)
			out.println(-1);
		out.close();
		in.close();
	}
}
