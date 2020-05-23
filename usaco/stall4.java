
/*
ID: dingarv1
LANG: JAVA
TASK: stall4
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class stall4 {
	private static boolean[][] neighborsV;

	private static int cows;
	private static int stalls;

	public static void main(String[] args) throws Exception {

		Scanner in = new Scanner(new File("stall4.in"));
		PrintWriter out = new PrintWriter(new File("stall4.out"));
		cows = in.nextInt();
		stalls = in.nextInt();
		neighborsV = new boolean[(cows + stalls + 2)][(cows + stalls + 2)];
		//source:0
		//cows:1,2,3,4,5
		//stalls:6,7,8,9,10
		//target:11
	
		for (int i = 0; i < cows; i++) {
			int n1 = in.nextInt();
			for (int j = 0; j < n1; j++) {
				neighborsV[i+1][cows+in.nextInt()] = true;
			}
			neighborsV[0][i+1]=true;
		}
		for(int i=0;i<stalls;i++){
			neighborsV[i+cows+1][cows+stalls+1]=true;
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
				neighborsV[yay.previous.node][yay.node] = false;
				neighborsV[yay.node][yay.previous.node] = true;
				yay = yay.previous;
			}
		}
		out.println(sum);
		in.close();
		out.close();
	}

	public static Node bfs() {
		boolean[] done = new boolean[cows + stalls + 2];
		done[0] = true;
		LinkedList<Node> a = new LinkedList<Node>();
		a.add(new Node(1,0, null));
		while (!a.isEmpty()) {
			Node c = a.poll();
			int node = c.node;
			if (node == (cows + stalls + 1)) {
				return c;
			}
			done[node] = true;
			for (int i = 0; i < (cows + stalls + 2); i++) {
				if (neighborsV[node][i] != false) {
					if (!done[i]) {
						if (neighborsV[node][i]==false&&c.flow==1) {
							a.add(new Node(1, i, c));
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
