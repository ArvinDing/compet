
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class checklistRef {
	private static int[][] pGs, pHs;

	public static void main(String[] args) throws Exception {
		int G, H;
		try (BufferedReader in = new BufferedReader(new FileReader("checklist.in"))) {
			String[] line = in.readLine().split(" ");
			H = Integer.parseInt(line[0]);
			G = Integer.parseInt(line[1]);
			pHs = new int[H][2];
			pGs = new int[G][2];
			for (int i = 0; i < H; i++) {
				line = in.readLine().split(" ");
				pHs[i][0] = Integer.parseInt(line[0]);
				pHs[i][1] = Integer.parseInt(line[1]);
			}
			for (int i = 0; i < G; i++) {
				line = in.readLine().split(" ");
				pGs[i][0] = Integer.parseInt(line[0]);
				pGs[i][1] = Integer.parseInt(line[1]);
			}
		}
		final long[][][] dists = new long[H + 1][G + 1][2];
		for (long[][] o1 : dists) {
			for (long[] o2 : o1)
				Arrays.fill(o2, Integer.MAX_VALUE);
		}
		boolean[][][] flags = new boolean[H + 1][G + 1][2];
		dists[1][0][0] = 0;
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				long diff = dists[arg0[0]][arg0[1]][arg0[2]] - dists[arg1[0]][arg1[1]][arg1[2]];
				return diff < 0 ? -1 : (diff == 0 ? arg0[0] - arg1[0] : 1);
			}
		});
		queue.offer(new int[] { 1, 0, 0 });//
		while (!queue.isEmpty()) {
			int[] cNode = queue.poll();
			int cH = cNode[0], cG = cNode[1], cGF = cNode[2];
		
			if (flags[cH][cG][cGF]) {
				continue;
			}
			System.out.printf("%d %d %d %d\n", cH, cG, cGF, dists[cH][cG][cGF]);
			flags[cH][cG][cGF] = true;
			if (cH == H && cG == G && cGF == 0) {
				break;
			}
			long cD = dists[cH][cG][cGF];
			for (int[] nn : new int[][] { new int[] { cH + 1, cG, 0 }, new int[] { cH, cG + 1, 1 } }) {
				if (nn[0] <= H && nn[1] <= G && !flags[nn[0]][nn[1]][nn[2]]) {
					long nd = cD + getDist(cH, cG, cGF, nn[0], nn[1], nn[2]);
					if (nd < dists[nn[0]][nn[1]][nn[2]]) {
						dists[nn[0]][nn[1]][nn[2]] = nd;
						queue.add(new int[] { nn[0], nn[1], nn[2] });

					}
				}
			}
		}
		Files.write(Paths.get("checklist.out"), String.valueOf(dists[H][G][0]).getBytes());
	}

	private static long getDist(int h1, int g1, int gf1, int h2, int g2, int gf2) {
		int[] start, end;
		if (gf1 == 0) {// start from h1
			start = pHs[h1 - 1];
		} else {// start from g1
			start = pGs[g1 - 1];
		}
		if (gf2 == 0) {// end at h2
			end = pHs[h2 - 1];
		} else {
			end = pGs[g2 - 1];
		}
		int dx = end[0] - start[0], dy = end[1] - start[1];
		return dx * dx + dy * dy;
	}

}
