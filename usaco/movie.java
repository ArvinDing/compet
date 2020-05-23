
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class movie {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("movie.in"));
		PrintWriter out = new PrintWriter(new File("movie.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int movies = Integer.parseInt(read.nextToken());
		int minutes = Integer.parseInt(read.nextToken());
		int[] durations = new int[movies];
		TreeSet<Integer>[] info = new TreeSet[movies];
		for (int i = 0; i < movies; i++) {
			read = new StringTokenizer(in.readLine());
			int duration = Integer.parseInt(read.nextToken());
			int times = Integer.parseInt(read.nextToken());
			info[i] = new TreeSet<Integer>();
			durations[i] = duration;
			for (int k = 0; k < times; k++) {
				int curr = Integer.parseInt(read.nextToken());
				info[i].add(curr);
			}
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		int cnt = 0;
		int[][][] save = new int[movies + 1][1001][movies];
		queue.add(new int[] { -1, 0, 0, -1 });
		int[] finished = new int[1 << movies];
		int ans = -1;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int oldS = curr[0];
			int done = curr[1];
			int doneN = curr[2];
			int oldE = finished[done];
			int bit = 1;

			if (oldE >= minutes) {
				ans = doneN;
				break;
			}
			for (int i = 0; i < movies; i++) {
			
				if ((bit & done) == 0) {
					int unIncludeStart = Math.max(oldE - durations[i], oldS);
					cnt++;
					NavigableSet<Integer> temp = info[i].headSet(oldE, true);
					if (!temp.isEmpty() && temp.last() > unIncludeStart) {

						if (finished[done + bit] == 0) {
							queue.add(new int[] { temp.last(), done + bit, doneN + 1, i });
						}
						finished[done + bit] = Math.max(finished[done + bit], temp.last() + durations[i]);
					}

				}
				bit = bit << 1;

			}
		}
		System.out.println(cnt);
		out.println(ans);
		in.close();
		out.close();

	}

}
