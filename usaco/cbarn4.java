
import java.io.*;
import java.util.*;

public class cbarn4 {

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new File("closing.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int close = Integer.parseInt(read.nextToken());
		int connections = Integer.parseInt(read.nextToken());
		List<Integer>[] neighbors = new ArrayList[close + 1];
		for (int i = 0; i < connections; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken());
			int b = Integer.parseInt(read.nextToken());
			if (neighbors[a] == null)
				neighbors[a] = new ArrayList<Integer>();
			if (neighbors[b] == null)
				neighbors[b] = new ArrayList<Integer>();
			neighbors[a].add(b);
			neighbors[b].add(a);
		}

		boolean[] closed = new boolean[close + 1];
		int[] check = new int[close + 1];
		outer: for (int i = 0; i < close; i++) {
			boolean[] done = new boolean[close + 1];
			int curr = Integer.parseInt(in.readLine());
			LinkedList<Integer> queue = new LinkedList<Integer>();
			int times = 0;
			for (int j = 1; j <= close; j++) {
				if (!closed[j]) {
					int uniform = check[j];
					for (int k = j+1; k <= close; k++) {
						if (!closed[k]) {
							if (check[k] != uniform) {
								out.println("NO");
								closed[curr] = true;
								continue outer;
							}
						}
					}
					break;
				}
			}
			for (int k = 1; k <= close; k++) {
				if (!done[k] && !closed[k]) {
					times++;
					queue.add(k);
					done[k] = true;
					check[k] = k;
					while (!queue.isEmpty()) {
						int temp = queue.poll();

						for (int a : neighbors[temp]) {
							if (!done[a] && !closed[a]) {
								queue.add(a);
								check[a] = k;
								done[a] = true;

							}
						}
					}
				}

			}
			if (times == 1)
				out.println("YES");
			else
				out.println("NO");
			closed[curr] = true;
		}

		in.close();
		out.close();

	}
}
