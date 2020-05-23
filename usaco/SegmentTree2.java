import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class SegmentTree2 {
	private int[] tree, data;
	private int aSize;

	public SegmentTree2(int[] data) {
		this.data = data;
		aSize = data.length;
		tree = new int[4 * aSize];// this is large enough for segment trees
		Arrays.fill(tree, Integer.MIN_VALUE);
		build_tree(1, 0, aSize - 1);
	}

	/**
	 * Build the init tree
	 */
	public void build_tree(int node, int start, int end) {
		if (start == end) {
			tree[node] = data[start];
			return;
		}
		build_tree(node * 2, start, (end + start) / 2);
		build_tree(node * 2 + 1, (end + start) / 2 + 1, end);
		tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
		return;
	}

	/**
	 * Increment elements within range [rStart, rEnd] with value value
	 */
	public void update_tree(int rStart, int rEnd, int value) {
		// TODO: implement this
		update(rStart, rEnd, value, 1, 0, data.length - 1);
	}

	public void update(int rStart, int rEnd, int value, int node, int start, int end) {

		if (rStart > end || rEnd < start) {
			return;
		}
		if (start == end) {
			tree[node] += value;
			return;
		}
		if (rStart <= start && rEnd >= end) {
			tree[node] += value;
			
		}
		update(rStart, rEnd, value, node * 2, start, (end + start) / 2);
		update(rStart, rEnd, value, node * 2 + 1, (end + start) / 2 + 1, end);
		return;
	}

	/**
	 * Query tree to get max element value within range [rStart, rEnd]
	 */
	public int query_tree(int rStart, int rEnd) {
		// TODO: implement this
		return query(rStart, rEnd, 1, 0, data.length - 1);
	}

	private int query(int rStart, int rEnd, int node, int start, int end) {
		if (rStart > end || rEnd < start) {
			return Integer.MIN_VALUE;
		}
		if (start == end)
			return tree[node];

		if (rStart <= start && rEnd >= end)
			return tree[node];

		return Math.max(query(rStart, rEnd, node * 2, start, (end + start) / 2),
				query(rStart, rEnd, node * 2 + 1, (end + start) / 2 + 1, end));

	}

	public static void main(String[] argv) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("SegTree2"))) {
			String[] inS = in.readLine().split(" ");
			int[] data = new int[inS.length];
			for (int i = 0; i < data.length; i++) {
				data[i] = Integer.parseInt(inS[i]);
			}
			SegmentTree2 st = new SegmentTree2(data);
			inS = in.readLine().split(" ");
			st.update_tree(Integer.parseInt(inS[0]), Integer.parseInt(inS[1]), Integer.parseInt(inS[2]));
			inS = in.readLine().split(" ");
			System.out.println(st.query_tree(Integer.parseInt(inS[0]), Integer.parseInt(inS[1])));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] { 0, aSize - 1 });
		int i = 1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			while (tree[i] == Integer.MIN_VALUE)
				i++; // Skip unused nodes at the layer furtherest from the root
			if (cur[0] == cur[1]) {
				sb.append(i + ":" + tree[i++] + "(Leaf:" + cur[1] + ")" + System.lineSeparator());
			} else {
				sb.append(i + ":" + tree[i++] + "(" + cur[0] + "-" + cur[1] + ")" + System.lineSeparator());
				int mid = (cur[0] + cur[1]) / 2;
				queue.add(new int[] { cur[0], mid });
				queue.add(new int[] { mid + 1, cur[1] });
			}
		}
		return sb.toString();
	}
}