
import java.io.*;
import java.util.*;

public class wordBoggle {
	private static node info;
	private static int height;
	private static int width;
	private static char[][] boggle;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int i = 0; i < test; i++) {
			info = new node("", new HashMap<Character, node>());
			int dict = Integer.parseInt(in.readLine());
			StringTokenizer read = new StringTokenizer(in.readLine());
			for (int k = 0; k < dict; k++) {
				String dictC = read.nextToken();
				char[] a = dictC.toCharArray();
				node current = info;
				for (int z = 0; z < a.length; z++) {
					if (!current.children.containsKey(a[z])) {
						current.children.put(a[z], new node("", new HashMap<Character, node>()));
					}
					if (z == a.length - 1) {
						current.children.put(a[z], new node(dictC, current.children.get(a[z]).children));
					}
					current = current.children.get(a[z]);
				}
			}
			read = new StringTokenizer(in.readLine());
			height = Integer.parseInt(read.nextToken());
			width = Integer.parseInt(read.nextToken());
			boggle = new char[height][width];
			read = new StringTokenizer(in.readLine());
			for (int k = 0; k < height; k++) {
				for (int z = 0; z < width; z++) {
					boggle[k][z] = read.nextToken().charAt(0);
				}
			}
			boolean[][] done = new boolean[height][width];
			TreeSet<String> ans = new TreeSet<String>();

			for (int k = 0; k < height; k++) {
				for (int z = 0; z < width; z++) {
					if (info.children.containsKey(boggle[k][z])) {

						done[k][z] = true;
						ans.addAll(recursion(done, k, z, info.children.get(boggle[k][z])));
						done[k][z] = false;

					}
				}
			}
			if (ans.isEmpty()) {
				System.out.println(-1);
			} else {
				System.out.print(ans.pollFirst());
				for (String curr : ans) {
					System.out.print(" " + curr);
				}

				System.out.println();
			}
		}
		in.close();
	}

	private static TreeSet<String> recursion(boolean[][] done, int y, int x, node important) {
		TreeSet<String> curr = new TreeSet<String>();
		if (important.finish != "") {
			curr.add(important.finish);
		}
		for (int yAdd = -1; yAdd <= 1; yAdd++) {
			for (int xAdd = -1; xAdd <= 1; xAdd++) {
				if (!(yAdd == 0 & xAdd == 0)) {
					if (0 <= y + yAdd && y + yAdd < height && 0 <= x + xAdd && x + xAdd < width) {
						if (!done[y + yAdd][x + xAdd] && important.children.containsKey(boggle[y + yAdd][x + xAdd])) {
							done[y + yAdd][x + xAdd] = true;
							curr.addAll(recursion(done, y + yAdd, x + xAdd,
									important.children.get(boggle[y + yAdd][x + xAdd])));
							done[y + yAdd][x + xAdd] = false;
						}
					}
				}
			}
		}
		return curr;
	}

	private static class node {
		String finish;
		HashMap<Character, node> children;

		private node(String finish, HashMap<Character, node> children) {
			this.finish = finish;
			this.children = children;
		}

	}
}
