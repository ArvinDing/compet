
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class movie2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("movie.in"));
		PrintWriter out = new PrintWriter(new File("movie.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int movies = Integer.parseInt(read.nextToken());
		int minutes = Integer.parseInt(read.nextToken());
		int[] durations = new int[movies];
		TreeMap<Integer, Integer>[] info = new TreeMap[movies];
		int idx = 0;
		for (int i = 0; i < movies; i++) {
			read = new StringTokenizer(in.readLine());
			int duration = Integer.parseInt(read.nextToken());
			int times = Integer.parseInt(read.nextToken());
			info[i] = new TreeMap<Integer, Integer>();
			durations[i] = duration;
			for (int k = 0; k < times; k++) {
				int curr = Integer.parseInt(read.nextToken());
				info[i].put(curr, idx);
				idx++;
			}
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		int[][][] save = new int[idx + 1][movies][2];

		queue.add(new int[] { -1, 0, 0, -1 });
		int[][] finished = new int[1 << movies][2];
		finished[0][1]=-1;
		int ans = -1;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int oldS = curr[0];
			int done = curr[1];
			int doneN = curr[2];

			int oldE = finished[done][0];
			int oldMovieIdx = finished[done][1];
			int bit = 1;

			if (oldE >= minutes) {
				ans = doneN;
				break;
			}
			for (int i = 0; i < movies; i++) {

				if ((bit & done) == 0) {
					int unIncludeStart = Math.max(oldE - durations[i], oldS);
					if (save[oldMovieIdx + 1][i][0] == 0) {
						NavigableMap<Integer, Integer> temp = info[i].headMap(oldE, true);
						if (temp.isEmpty()) {
							save[oldMovieIdx + 1][i][0] = -1;
						} else {
							Entry<Integer, Integer> o = temp.lastEntry();
							save[oldMovieIdx + 1][i][0] = o.getKey();
							save[oldMovieIdx + 1][i][1] = o.getValue();
						}

					} 
					int start = save[oldMovieIdx + 1][i][0];
					int index = save[oldMovieIdx + 1][i][1];
					if (start != -1 && start > unIncludeStart) {

						if (finished[done + bit][0] == 0) {
							queue.add(new int[] { start, done + bit, doneN + 1 });
						}
						if (start + durations[i] > finished[done + bit][0]) {
							finished[done + bit][0] = start + durations[i];
							finished[done + bit][1] = index;
						}
					}

				}
				bit = bit << 1;

			}
		}

		out.println(ans);
		in.close();
		out.close();

	}

}
