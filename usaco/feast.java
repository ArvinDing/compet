import java.io.*;
import java.util.*;

public class feast {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new FileWriter("feast.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int max = Integer.parseInt(read.nextToken());
		int orange = Integer.parseInt(read.nextToken());
		int lemon = Integer.parseInt(read.nextToken());
		boolean[] done = new boolean[max + 1];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		done[0] = true;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			if (curr + orange <= max && !done[curr + orange]) {
				queue.add(curr + orange);
				done[curr + orange] = true;
			}
			if (curr + lemon <= max && !done[curr + lemon]) {
				queue.add(curr + lemon);
				done[curr + lemon] = true;
			}
		}
		if (done[max]) {
			out.println(max);
		} else {
			for (int i = 0; i < max + 1; i++) {
				if (done[i] == true && !done[i / 2]) {
					queue.add(i / 2);
					done[i / 2] = true;
				}
			}

			while (!queue.isEmpty()) {
				int curr = queue.poll();
				if (curr + orange <= max && !done[curr + orange]) {
					queue.add(curr + orange);
					done[curr + orange] = true;
				}
				if (curr + lemon <= max && !done[curr + lemon]) {
					queue.add(curr + lemon);
					done[curr + lemon] = true;
				}
			}
			for (int i = max; i >= 0; i--) {
				if (done[i]) {
					out.println(i);
					break;
				}
			}
		}
		out.close();
		in.close();
	}
}
