import java.io.*;
import java.util.*;

public class eGov {
	private static List<node> trie;
	private static boolean[] done;
	private static int trieSize;
	private static int[] empty;
	private static int[] segT;

	public static void main(String[] args) throws Exception {
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new FileReader("input"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(read.nextToken());
		int k = Integer.parseInt(read.nextToken());
		String[] members = new String[k];
		for (int i = 0; i < k; i++) {
			members[i] = in.readLine();
		}
		trie = new ArrayList<node>();
		empty = new int[26];
		Arrays.fill(empty, -1);
		trie.add(new node(Arrays.copyOf(empty, empty.length), 0, -1, -1, ' '));
		trieSize = 1;
		for (int i = 0; i < k; i++) {
			char[] test = members[i].toCharArray();
			set(test, 1);
		}
		done = new boolean[trieSize];
		for (int i = 1; i < trieSize; i++) {
			if (done[i] != true)
				assign(i);
		}
		int[] lSSuffixs = new int[trieSize];
		Arrays.fill(lSSuffixs, -1);
		for (int i = 1; i < trieSize; i++) {
			// largest shaded suffix
			int LSSuffix = trie.get(i).largestSI;

			while (LSSuffix != -1) {
				if (trie.get(LSSuffix).val != 0) {
					lSSuffixs[i] = LSSuffix;
					break;
				}
				LSSuffix = trie.get(LSSuffix).largestSI;
			}
		}
		int segTS = 2 * k + 1;

		for (int i = 0; i < n; i++) {
			String line = in.readLine();
			if (line.charAt(0) == '?') {
				System.out.println(process(line.substring(1)));
			} else if (line.charAt(0) == '+') {
				set(members[Integer.parseInt(line.substring(1)) - 1].toCharArray(), 1);
			} else {
				set(members[Integer.parseInt(line.substring(1)) - 1].toCharArray(), -1);
			}
		}

		System.out.println();
	}

	private static void update(int lB, int rB, int l, int r, int idx) {

	}

	private static int query(int lB, int rB, int l, int r, int idx) {
		return -1;
	}

	public static int process(String news) {
		int nodeIdx = 0;
		for (int i = 0; i < news.length(); i++) {

		}
		return -1;
	}

	public static void set(char[] test, int val) {
		int nodeIdx = 0;
		node temp = trie.get(0);
		for (char a : test) {
			temp = trie.get(nodeIdx);
			if (temp.child[a - 97] == -1) {
				temp.child[a - 97] = trieSize;
				trie.add(new node(Arrays.copyOf(empty, empty.length), 0, -1, nodeIdx, a));
			}
			nodeIdx = temp.child[a - 97];
		}
		temp.val = 1;
	}

	public static int goBack(int index, char a) {
		if (trie.get(index).child[a - 97] != -1)
			return trie.get(index).child[a - 97];
		if (index == 0)
			return 0;
		int previousLST = trie.get(index).largestSI;
		if (previousLST == -1)
			assign(index);
		return goBack(previousLST, a);
	}

	public static void assign(int index) {
		int parentIdx = trie.get(index).parent;
		int previousLST = trie.get(parentIdx).largestSI;
		if (previousLST == -1) {
			assign(parentIdx);
			previousLST = trie.get(parentIdx).largestSI;
		}
		char curr = trie.get(index).currChar;
		int temp = goBack(previousLST, curr);
		trie.get(index).largestSI = temp;
		done[index] = true;
	}

	public static class node {

		int[] child;
		int val;
		int largestSI;// largest strict suffix node index
		int parent;
		char currChar;

		public node(int[] child, int val, int largestSI, int parent, char currChar) {
			this.parent = parent;
			this.child = child;
			this.val = val;
			this.largestSI = largestSI;
			this.currChar = currChar;

		}
	}
}
