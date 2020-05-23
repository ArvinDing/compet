
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class atlargeR {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter(new File("atlarge.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int surface = Integer.parseInt(read.nextToken()) - 1;
		LinkedList<Integer>[] neighbor = new LinkedList[n];
		for (int i = 0; i < n; i++)
			neighbor[i] = new LinkedList<Integer>();
		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;

			neighbor[a].add(b);
			neighbor[b].add(a);
		}
		LinkedList<int[]> queue = new LinkedList<int[]>();
		LinkedList<int[]> sorted = new LinkedList<int[]>();
		int[] distance = new int[n];
		boolean[] isExit = new boolean[n];
		int total=0;
		for (int i = 0; i < n; i++) {
			isExit[i] = (neighbor[i].size() == 1);
			if(isExit[i])total++;
		}
		if (isExit[surface]) {
			out.println(1);
		} else {
			queue.add(new int[] { surface, -1, 0 });
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				int position = curr[0];
				int previous = curr[1];
				distance[position] = curr[2];
				if (isExit[position])
					sorted.add(new int[] { curr[2], position });
				for (int next : neighbor[position]) {
					if (next != previous)
						queue.add(new int[] { next, position, curr[2] + 1 });
				}
			}
			Collections.sort(sorted, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			int cntCover = 0;
			int farmer=0;
			while(!sorted.isEmpty()){
				int currPoss = sorted.poll()[1];
				if(!isExit[currPoss])continue;
				farmer++;
				int[] temp = new int[n];
				queue.add(new int[] { currPoss, -1, 0 });
				while (!queue.isEmpty()) {
					int[] curr = queue.poll();
					int position = curr[0];
					int previous = curr[1];
					temp[position] = curr[2];
					for (int next : neighbor[position]) {
						if (next != previous)
							queue.add(new int[] { next, position, curr[2] + 1 });
					}
				}
				for (int z = 0; z < n; z++) {
					if (isExit[z] && temp[z] <= distance[z]) {
						isExit[z] = false;
						cntCover++;
					}
				}
				if (cntCover == total) {
					out.println(farmer);
					break;
				}

			}
		}
		out.close();
		in.close();

	}
}