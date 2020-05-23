
import java.io.*;
import java.util.*;

public class art2 {
	private static int[][] minMax;
	private static int[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new File("art2.out"));
		int cells = Integer.parseInt(in.readLine());
		info = new int[cells];
		minMax = new int[100001][2];

		for (int i = 0; i < 100001; i++) {
			minMax[i][0] = Integer.MAX_VALUE;
			minMax[i][1] = -1;
		}
		for (int i = 0; i < cells; i++) {
			info[i] = Integer.parseInt(in.readLine());
			if (minMax[info[i]][0] == Integer.MAX_VALUE)
				minMax[info[i]][0] = i;
			minMax[info[i]][1] = i;
		}
		outer: {
			int[] layersStart = new int[100001];
			int max = 0;
			int layers = 0;
			for (int i = 0; i < cells; i++) {
				if (info[i] == 0)
					continue;
				if (i == minMax[info[i]][0]) {
					layers++;
					layersStart[info[i]] = layers;
					max = Math.max(max, layers);
				}
				if (layersStart[info[i]] != layers) {
					out.print(-1);
					break outer;
				}
				if (i == minMax[info[i]][1]) {
					layers--;
				}

			}
			out.print(max);
		}
		out.close();
		in.close();

	}
}