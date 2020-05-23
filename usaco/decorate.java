
import java.io.*;
import java.util.*;

public class decorate {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("decorate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("decorate.out")));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int pastures = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());
		List[] neighbors = new ArrayList[pastures + 1];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken());
			int end = Integer.parseInt(read.nextToken());
			if (neighbors[start] == null) {
				neighbors[start] = new ArrayList<Integer>();
			}
			if (neighbors[end] == null) {
				neighbors[end] = new ArrayList<Integer>();
			}
			neighbors[start].add(end);
			neighbors[end].add(start);

		}
		boolean[] done = new boolean[pastures + 1];
		boolean[] previous = new boolean[pastures + 1];
		boolean[] state = new boolean[pastures + 1];

		int max = 0;
		LinkedList<unnecessary> queue = new LinkedList<unnecessary>();
		outer: {
			for (int i = 1; i < done.length; i++) {
				if (!done[i]) {
					int cnt = 0;
					queue.add(new unnecessary(i, true));
					cnt++;
					state[i] = true;
					done[i] = true;
					int sections = 0;
					while (!queue.isEmpty()) {
						unnecessary curr = queue.poll();
						int currPosition = curr.position;
						boolean currState = curr.state;
						sections++;
						currState = !(currState);
						if (neighbors[currPosition] == null) {
							continue;
						}
						for (int a : (List<Integer>) neighbors[currPosition]) {
							if (done[a]) {
								if (state[a] != currState) {
									out.println(-1);
									break outer;
								}
							} else {
								queue.add(new unnecessary(a, currState));
								if (currState) {
									cnt++;
								}
								state[a] = currState;

								done[a] = true;
							}
						}
					}

					// int cnt = 0;
					// for (int j = 1; j < previous.length; j++) {
					// if (!previous[j] && state[j])
					// cnt++;
					// }
					max += Math.max(sections - cnt, cnt);

				}
			}
			out.println(max);
		}
		in.close();
		out.close();
	}

	private static class unnecessary {
		int position;
		boolean state;

		private unnecessary(int position, boolean state) {
			this.position = position;
			this.state = state;
		}

	}

}