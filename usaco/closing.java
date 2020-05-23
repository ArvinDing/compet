
import java.io.*;
import java.util.*;

public class closing {
	private static int[] tree;

	public static void main(String[] argv) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new File("closing.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int close = Integer.parseInt(read.nextToken());
		int connections = Integer.parseInt(read.nextToken());
		List[] info = new List[close];

		for (int i = 0; i < connections; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (info[a] == null)
				info[a] = new ArrayList<Integer>();
			if (info[b] == null)
				info[b] = new ArrayList<Integer>();
			info[a].add(b);
			info[b].add(a);
		}
		int[] closeOrder = new int[close];
		for (int i = 0; i < close; i++) {
			closeOrder[i] = Integer.parseInt(in.readLine()) - 1;
		}

		tree = new int[close];
		//boolean[] added = new boolean[close];
		boolean[] answer = new boolean[close];
		for (int i = 0; i < close; i++) {
			tree[i] = i;
		}
		int roots=0;
		for (int i = close - 1; i >= 0; i--) {
			int curr = closeOrder[i];
			List<Integer> thing = info[curr];
			tree[curr] = -1;
			roots++;
			//added[curr] = true;
			if(thing==null)continue;
			for (int a : thing) {
				if (tree[a]!=a) {
					if (tree[curr] >= 0) {
						curr = find(curr);
					}
					if (tree[a] >= 0) {
						a = find(a);
					}

					if (curr == a)
						continue;
					if (tree[a] < tree[curr]) {
						tree[curr] = a;
					} else if (tree[a] > tree[curr]) {
						tree[a] = curr;
					} else {
						tree[a] = curr;
						tree[curr]--;
					}
					roots--;
				}
			}
		
			answer[i] = (roots == 1);
		}

		for (int i = 0; i < close; i++) {
			out.println(answer[i] ? "YES" : "NO");
		}
		in.close();
		out.close();

	}



	private static int find(int index) {
				int old = index;
		while (tree[index] >= 0) {
			index = tree[index];

		}
		while (tree[old] >= 0) {
			tree[old] = index;
			old = tree[old];
		}

		return index;
	}
}
