
import java.io.*;
import java.util.*;

public class cowrect2 {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowrect.in"));
		PrintWriter out = new PrintWriter(new File("cowrect.out"));
		int cows = Integer.parseInt(in.readLine());
		TreeMap<Integer, TreeSet<Integer>> bad = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, TreeSet<Integer>> good = new TreeMap<Integer, TreeSet<Integer>>();
		int[][] info = new int[cows][3];
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
			info[i][2] = (read.nextToken().equals("H")) ? 1 : 0;
			if (info[i][2] == 1) {
				if (!good.containsKey(info[i][0]))
					good.put(info[i][0], new TreeSet<Integer>());
				good.get(info[i][0]).add(info[i][1]);
			} else {
				if (!bad.containsKey(info[i][0]))
					bad.put(info[i][0], new TreeSet<Integer>());
				bad.get(info[i][0]).add(info[i][1]);
			}
		}
		Arrays.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		long ans = 0;
		long area = -1;
		for (int i = 0; i < cows; i++) {
			if (info[i][2] == 0)
				continue;
			int lessX = info[i][0];
			int y = info[i][1];
			int maxY = Integer.MAX_VALUE;
			int minY = Integer.MIN_VALUE;

			second: for (int k = i; k < cows; k++) {
				if (info[k][2] == 0)
					continue;
				int highX = info[k][0];
				int otherY = info[k][1];
				int higherY = Math.max(y, otherY);
				int lowerY = Math.min(y, otherY);
				int previous = -1;
				long cnt = 0;
				for (int z = i; z <= k; z++) {
					int currX = info[z][0];
					if (currX == previous) {
						continue;
					}
					previous = currX;
					TreeSet<Integer> temp = bad.get(currX);
					if (temp != null) {
						for (int currY : temp) {
							if (currY > higherY) {
								maxY = Math.min(currY, maxY);
							} else if (lowerY <= currY && currY <= higherY) {
								continue second;
							} else {
								minY = Math.max(currY, minY);
							}
						}
					}

				}
				previous = -1;
				for (int z = i; z <= k; z++) {
					int currX = info[z][0];
					if (currX == previous) {
						continue;
					}
					previous = currX;
					TreeSet<Integer> temp = good.get(currX);
					if (temp != null) {
						for (int currY : temp) {
							if (currY < maxY && currY > minY) {
								cnt++;
								higherY = Math.max(currY, higherY);
								lowerY = Math.min(currY, lowerY);
							}
						}
					}
				}
				int currArea = (higherY - lowerY) * (highX - lessX);

				if (cnt > ans) {
					ans = cnt;
					area = currArea;
				} else if (cnt == ans && currArea < area) {
					area = currArea;
				}
			}
		}
		out.println(ans);
		out.println(area);
		in.close();
		out.close();
	}
}
