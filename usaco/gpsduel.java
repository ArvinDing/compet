
import java.io.*;
import java.util.*;

public class gpsduel {
	private static int intersections;
	private static List<Integer>[] neighbors;
	private static List<Integer>[] reverseNeighbors;
	private static HashMap<Integer, int[]>[] info;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gpsduel.in"));
		PrintWriter out = new PrintWriter(new File("gpsduel.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		intersections = Integer.parseInt(read.nextToken());
		int lines = Integer.parseInt(read.nextToken());

		neighbors = new List[intersections];
		reverseNeighbors = new List[intersections];
		info = new HashMap[intersections];
		for (int i = 0; i < lines; i++) {
			read = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(read.nextToken()) - 1;
			int end = Integer.parseInt(read.nextToken()) - 1;
			if (info[start] == null) {
				info[start] = new HashMap<Integer, int[]>();
			}
			int[] temp = new int[] { Integer.parseInt(read.nextToken()), Integer.parseInt(read.nextToken()) };
			if (info[start].get(end) != null) {
				temp[0] = Math.min(temp[0], info[start].get(end)[0]);
				temp[1] = Math.min(temp[1], info[start].get(end)[1]);
			}
			info[start].put(end, temp);
			if (neighbors[start] == null) {
				neighbors[start] = new ArrayList<Integer>();
			}
			neighbors[start].add(end);
			if (reverseNeighbors[end] == null) {
				reverseNeighbors[end] = new ArrayList<Integer>();
			}
			reverseNeighbors[end].add(start);
		}
		int[] suggestFirst = pathFind(0);
		int[] suggestSecond = pathFind(1);
		PriorityQueue<entry> queue = new PriorityQueue<entry>();
		queue.add(new entry(0, 0, 0));
		int[][] weight = new int[intersections][intersections];
		boolean[] done = new boolean[intersections];

		int min = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			entry curr = queue.poll();
			int position = curr.position;
			int value = curr.value;
			if (position == intersections - 1) {
				min = Math.min(value, min);
				continue;
			}
			if (done[position])
				continue;
			done[position] = true;
			for (int i : neighbors[position]) {
				if (!done[i]) {
					int add = 0;
					if (weight[position][i] != 0)
						add = weight[position][i];
					else {
						if (suggestFirst[position] != i) {
							add++;
						}
						if (suggestSecond[position] != i) {
							add++;
						}
						weight[position][i] = add;
					}
					queue.add(new entry(i, value + add, 0));

				}

			}
		}
		out.println(min);
		in.close();
		out.close();

	}

	private static int[] pathFind(int index) {
		PriorityQueue<entry> queue = new PriorityQueue<entry>();
		queue.add(new entry(intersections - 1, 0, 0));
		int[] previous = new int[intersections];
		boolean[] done = new boolean[intersections];
		while (!queue.isEmpty()) {
			entry curr = queue.poll();
			int position = curr.position;
			int value = curr.value;
			if (done[position])
				continue;
			done[position] = true;
			previous[position] = curr.previous;
			if (reverseNeighbors[position] == null)
				continue;
			for (int i : reverseNeighbors[position]) {
				if (!done[i]) {
					queue.add(new entry(i, value + info[i].get(position)[index], position));
				}
			}
		}
		return previous;

	}

	private static class entry implements Comparable<entry> {
		int position;
		int value;
		int previous;

		private entry(int position, int value, int previous) {
			this.position = position;
			this.value = value;
			this.previous = previous;
		}

		@Override
		public int compareTo(entry o) {
			return value - o.value;
		}
	}
}