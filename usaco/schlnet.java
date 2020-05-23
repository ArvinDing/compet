
/*
ID: dingarv1
LANG: JAVA
TASK: schlnet
*/
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class schlnet {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new File("schlnet.out"));
		int schools = Integer.parseInt(in.readLine());
		List<Integer>[] info = new List[schools];
		for (int i = 0; i < schools; i++) {
			StringTokenizer read = new StringTokenizer(in.readLine());
			info[i] = new ArrayList<Integer>();
			while (true) {
				int connection = Integer.parseInt(read.nextToken()) - 1;
				if (connection == -1)
					break;
				info[i].add(connection);
			}
		}
		boolean[] done = new boolean[schools];
		boolean[] top = new boolean[schools];
		for (int i = 0; i < schools; i++) {
			if (!done[i]) {
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				done[i] = true;
				top[i] = true;
				while (!queue.isEmpty()) {
					int curr = queue.poll();
					for (int neighbors : info[curr]) {
						if (!done[neighbors])
							queue.add(neighbors);
						done[neighbors] = true;
						top[neighbors] = false;
					}
				}
			}
		}

		boolean[] reachable = new boolean[schools];
		for (int i = 0; i < schools; i++) {
			if (top[i]) {
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				reachable[i] = true;

				while (!queue.isEmpty()) {
					int curr = queue.poll();
					for (int neighbors : info[curr]) {
						if (!reachable[neighbors])
							queue.add(neighbors);
						reachable[neighbors] = true;

					}
				}
			}
		}
		int tops = 0;
		boolean[] countedFor = new boolean[schools];
		for (int i = 0; i < schools; i++) {
			if (!reachable[i]) {
				LinkedList<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				reachable[i] = true;
				tops++;
			
				System.out.println(i);
				while (!queue.isEmpty()) {
					int curr = queue.poll();
					for (int neighbors : info[curr]) {
						if (reachable[neighbors] && countedFor[neighbors])
							tops--;
						if (!reachable[neighbors])
							queue.add(neighbors);
						reachable[neighbors] = true;
						
					}
				}
				countedFor[i] = true;
			}
		}

		for (int i = 0; i < schools; i++) {
			if (top[i]) {
				tops++;
				System.out.println(i);
			}
		}

		out.println(tops);
		int bottom = 0;
		for (int i = 0; i < schools; i++) {
			if (info[i].isEmpty()) {
				bottom++;
			}
		}
		if (bottom == 0)
			out.println(0);
		else
			out.println(Math.max(bottom, tops));
		in.close();
		out.close();
	}
}