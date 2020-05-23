
/*
ID: dingarv1
LANG: JAVA
TASK: ditch
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ditch {
	private static int[][] neighborsV;

	private static int m;

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new File("ditch.in"));
		PrintWriter out = new PrintWriter(new File("ditch.out"));
		int n = in.nextInt();
		m = in.nextInt();
		neighborsV = new int[m + 1][m + 1];

		for (int i = 0; i < n; i++) {
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			int v = in.nextInt();
			if (n1 != n2) {

				neighborsV[n1][n2] += v;
			}
		}
		// logic
		int sum = 0;
		while (true) {

			Node yay = bfs();
			if (yay == null) {
				break;
			}
			int capacity = yay.flow;
			sum += capacity;
			while (yay.previous != null) {
				neighborsV[yay.previous.node][yay.node] -= capacity;
				// System.out.print(yay.previous.node+"|"+yay.node+"|"+neighborsV[yay.previous.node][yay.node]+"
				// " );

				neighborsV[yay.node][yay.previous.node] += capacity;

				yay = yay.previous;
			}
			// System.out.println("-:" +capacity);
		}
		out.println(sum);
		in.close();
		out.close();
	}



	public static Node bfs() {
		boolean[] done = new boolean[m + 1];
		done[1] = true;
		LinkedList<Node> a = new LinkedList<Node>();
		a.add(new Node(Integer.MAX_VALUE, 1, null));
		while (!a.isEmpty()) {
			Node c = a.poll();
			int node = c.node;
			if (node == m) {
				return c;
			}
			done[node] = true;
			for (int i = 0; i <= m; i++) {
				if (neighborsV[node][i] != 0) {
					if (!done[i]) {
						// System.out.println("(" + node + "," + i + ")" +
						// neighborsV[node][i] + ":::" + c.flow);
						if (neighborsV[node][i] < c.flow) {
							a.add(new Node(neighborsV[node][i], i, c));
						} else {
							a.add(new Node(c.flow, i, c));
						}
					}
				}

			}

		}
		return null;
	}

	public static class Node implements Comparable {
		int flow;
		int node;
		Node previous;

		public Node(int flow, int node, Node previous) {
			this.flow = flow;
			this.node = node;
			this.previous = previous;
		}

		public int compareTo(Object o) {
			return flow - ((Node) o).flow;
		}
	}
}
