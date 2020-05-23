
import java.io.*;
import java.util.*;

public class moocast2 {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int cows = Integer.parseInt(in.readLine());

		int[][] info = new int[cows][2];
		for (int i = 0; i < cows; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = Integer.parseInt(read.nextToken());
		}
		int[] point = new int[cows];
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		Arrays.fill(point, -1);
		for (int i = 0; i < cows; i++) {
			for (int j = i + 1; j < cows; j++) {
				queue.add(new int[] { (info[i][0] - info[j][0]) * (info[i][0] - info[j][0])
						+ (info[i][1] - info[j][1]) * (info[i][1] - info[j][1]), i, j });
			}
		}
		int connections = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int a = curr[1];
			while (point[a] >= 0) {
				a = point[a];
			}
			int temp = curr[1];
			while (point[temp] >= 0) {
				int old = point[temp];
				point[temp] = a;
				temp = old;
			}
			int b = curr[2];
			while (point[b] >= 0) {
				b = point[b];
			}
			temp = curr[2];
			while (point[temp] >= 0) {
				int old = point[temp];
				point[temp] = b;
				temp = old;
			}
			if (a != b) {
				connections++;
				if (point[a] < point[b]) {
					point[b] = a;
				} else if (point[a] > point[b]) {
					point[a] = b;
				} else {
					point[a] = b;
					point[b]--;
				}
			}
			if (connections == cows - 1) {
				out.println(curr[0]);
				break;
			}
		}
		out.close();
		in.close();
	}
}