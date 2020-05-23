
/*
ID: dingarv1
LANG: JAVA
TASK: window
*/

import java.io.*;
import java.util.*;

public class window {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("window.in"));
		PrintWriter out = new PrintWriter(new FileWriter("window.out"));
		LinkedList<Integer> info = new LinkedList<Integer>();
		node[] link = new node[62];
		String read = in.readLine();
		while (read != null) {
			
			char function = read.charAt(0);
			String[] input = read.substring(2, read.length() - 1).split(",");
			int index = 0;
			if (input[0].charAt(0) >= 65 && input[0].charAt(0) <= 90) {
				index = input[0].charAt(0) - 55;
			} else if (input[0].charAt(0) >= 97) {
				index = input[0].charAt(0) - 61;
			} else {
				index = Integer.parseInt(input[0]);
			}
			if (function == 'w') {

				int minX = Math.min(Integer.parseInt(input[1]), Integer.parseInt(input[3]));
				int maxX = Math.max(Integer.parseInt(input[1]), Integer.parseInt(input[3]));
				int minY = Math.min(Integer.parseInt(input[2]), Integer.parseInt(input[4]));
				int maxY = Math.max(Integer.parseInt(input[2]), Integer.parseInt(input[4]));

				link[index] = new node(null, minX, minY, maxX, maxY);
				info.addFirst(index);
			} else if (function == 't') {

				for (int i = 0; i < info.size(); i++) {
					int temp = info.get(i);
					if (temp == index) {
						info.remove(i);
						info.addFirst(temp);
					}
				}
			} else if (function == 'b') {
				for (int i = 0; i < info.size(); i++) {
					int temp = info.get(i);
					if (temp == index) {
						info.remove(i);
						info.addLast(temp);
					}
				}

			} else if (function == 'd') {
				for (int i = 0; i < info.size(); i++) {
					int temp = info.get(i);
					if (temp == index) {
						info.remove(i);
					}
				}
			} else {
				Iterator<Integer> itr = info.iterator();
				node check = link[index];
				check.children=null;
				while (itr.hasNext()) {
					int currIndex = itr.next();
					if (currIndex == index)
						break;
					node other = link[currIndex];

					LinkedList<node> queue = new LinkedList<node>();
					queue.add(check);
					while (!queue.isEmpty()) {
						node important = queue.poll();
						if (important.children != null) {
							if (overlap(important, other)) {
								node[] temp = important.children;
								for (node a : temp) {
									if (a != null)
										queue.add(a);
								}
							}
						} else {
							node[] a = change(important, other);
							if (a != null) {
								important.children = a;
							}
						}
					}
				}
				long sum = 0;
				LinkedList<node> queue = new LinkedList<node>();
				queue.add(link[index]);
				while (!queue.isEmpty()) {
					node a = queue.poll();
					if (a.children != null) {
						node[] temp = a.children;
						for (node b : temp) {
							if (b != null)
								queue.add(b);
						}
					} else {
						sum += (a.highX - a.lowX) * (a.highY - a.lowY);
					}
				}

				out.format("%.3f", (double) (100 * sum) /( (check.highX - check.lowX) * (check.highY - check.lowY)));
				out.println();
			}
			read = in.readLine();
		}
		in.close();
		out.close();
	}

	private static boolean overlap(node a, node b) {
		return (a.lowX < b.highX && b.lowX < a.highX) && (a.lowY < b.highY && b.lowY < a.highY);
	}

	private static node[] change(node checked, node other) {
		if (overlap(checked, other)) {
			node[] ans = new node[4];
			if (checked.lowX < other.highX && other.highX < checked.highX) {
				ans[0] = new node(null, other.highX, checked.lowY, checked.highX, checked.highY);
			}
			if (checked.lowX < other.lowX && other.lowX < checked.highX) {
				ans[1] = new node(null, checked.lowX, checked.lowY, other.lowX, checked.highY);
			}
			if (checked.lowY < other.highY && other.highY < checked.highY) {
				ans[2] = new node(null, (ans[1] != null) ? other.lowX : checked.lowX, other.highY,
						(ans[0] != null) ? other.highX : checked.highX, checked.highY);
			}
			if (checked.lowY < other.lowY && other.lowY < checked.highY) {
				ans[3] = new node(null, (ans[1] != null) ? other.lowX : checked.lowX, checked.lowY,
						(ans[0] != null) ? other.highX : checked.highX, other.lowY);
			}
			return ans;
		}
		return null;
	}

	private static class node {
		node[] children;
		int lowX;
		int lowY;
		int highX;
		int highY;

		private node(node[] children, int lowX, int lowY, int highX, int highY) {
			this.children = children;
			this.lowX = lowX;
			this.lowY = lowY;
			this.highX = highX;
			this.highY = highY;
		}
	}
}
