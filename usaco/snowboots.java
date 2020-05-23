import java.io.*;
import java.util.*;

public class snowboots {
	private static int[][] info;
	private static int tiles;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new File("snowboots.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		tiles = Integer.parseInt(read.nextToken());
		int boots = Integer.parseInt(read.nextToken());
		info = new int[tiles][2];
		read = new StringTokenizer(in.readLine());
		for (int i = 0; i < tiles; i++) {
			info[i][0] = Integer.parseInt(read.nextToken());
			info[i][1] = i;
		}
		Arrays.sort(info, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] == arg1[0]) {
					return arg0[1] - arg1[0];
				}
				return arg1[0] - arg0[0];
			}

		});
		LinkedList<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < boots; i++) {
			read = new StringTokenizer(in.readLine());
			queue.add(new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()), i });
		}
		Collections.sort(queue, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg1[0] - arg0[0];
			}
		});
		TreeSet<Integer> reachable = new TreeSet<Integer>();
		for (int i = 0; i < tiles; i++) {
			reachable.add(i);
		}
		int max = 1;
		int[] ans = new int[boots];
		for (int i = 0; i < tiles; i++) {
			int snowDepth = info[i][0];
			while (!queue.isEmpty() && snowDepth <= queue.peek()[0]) {
				int[] curr = queue.poll();
				ans[curr[2]] = (curr[1] >= max) ? 1 : 0;
			}
			if (queue.isEmpty())
				break;
			int position = info[i][1];
			reachable.remove(position);
			Integer next = reachable.higher(position);
			Integer previous = reachable.lower(position);
			max = Math.max(max, next - previous);
		}
		for (int curr : ans) {
			out.println(curr);
		}
		out.close();
		in.close();
	}
}