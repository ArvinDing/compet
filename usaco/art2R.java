import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class art2R {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new File("art2.out"));
		int n = Integer.parseInt(in.readLine());
		int[] min = new int[n + 1];
		int[] max = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int curr = Integer.parseInt(in.readLine());
			if (curr == 0)
				continue;
			if (min[curr] == 0)
				min[curr] = i;
			max[curr] = i;
		}

		TreeMap<Integer, Integer> sort = new TreeMap<Integer, Integer>();
		for (int i = 1; i <= n; i++) {
			if (min[i ] != 0) {
				if (max[i] == min[i ])
					sort.put(min[i], -i);
				else {
					sort.put(min[i], i);
					sort.put(max[i], i);
				}
			}
		}
		int ans = Integer.MIN_VALUE;
		int layers = 0;
		int[] layersAtPoint = new int[n + 1];
		Arrays.fill(layersAtPoint, -1);
		outer: {
			for (int curr : sort.values()) {
				if (curr < 0) {
					ans = Math.max(ans, layers + 1);
				} else if (layersAtPoint[curr] == -1) {
					layersAtPoint[curr] = layers;
					layers++;
					ans = Math.max(ans, layers);
				} else {
					layers--;
					if (layersAtPoint[curr] != layers) {
						out.println(-1);
						break outer;
					}
				}
			}
			out.println(ans);
		}
		out.close();
		in.close();
	}
}
