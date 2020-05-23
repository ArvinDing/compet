
/*
ID: dingarv1
LANG: JAVA
TASK: milk6
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class milk6 {
	private static long[][] info;
	private static int warehouses;
	private static boolean[] done;
	private static long sum=0;
	public static void main(String[] args) throws Exception {
		// read
		
		BufferedReader in = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new File("milk6.out"));
		StringTokenizer yay = new StringTokenizer(in.readLine());
		warehouses = Integer.parseInt(yay.nextToken());
		int routes = Integer.parseInt(yay.nextToken());
		int[] costs = new int[routes];
		info = new long[warehouses + 1][warehouses + 1];
		ArrayList<Integer>[][] indexes = new ArrayList[warehouses + 1][warehouses + 1];
		ArrayList<Integer>[] neighbors = new ArrayList[warehouses + 1];
		for (int i = 0; i < warehouses + 1; i++) {
			neighbors[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < warehouses + 1; i++) {
			for (int k = 0; k < warehouses + 1; k++) {
				indexes[i][k] = new ArrayList<Integer>();
			}
		}
	
		for (int i = 0; i < routes; i++) {
			yay = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(yay.nextToken());
			int to = Integer.parseInt(yay.nextToken());
			int cost = Integer.parseInt(yay.nextToken());

			neighbors[from].add(to);

			info[from][to] += 1001 * (cost * 1001l + 1) + i + 1;
			indexes[from][to].add(i);
			costs[i] = cost;
		}
	
		TreeSet<Integer> answer = new TreeSet<Integer>();
	
		long[][] start = maxFlow();
	
		
		
		for (int i = 0; i < done.length; i++) {
			if (done[i] == true) {
				for (int a : neighbors[i]) {
					if (start[i][a] == 0 && done[a] == false) {
						answer.addAll(indexes[i][a]);
					}
				}
			}
		}
	
		int cost = 0;
		for (int i : answer) {
			cost += costs[i];
		}
		out.println(cost + " " + answer.size());
		for (int i : answer) {
			out.println(i + 1);
		}

		out.close();
		in.close();
	System.out.print(sum);

	}

	private static long[][] maxFlow() {
		
		long[][] info1 = new long[info.length][info.length];
		for (int i = 0; i < info.length; i++) {
			for (int i1 = 0; i1 < info.length; i1++) {
				info1[i][i1] = info[i][i1];
			}
		}

		while (true) {
			Node yay = bfs(info1);
			if (yay == null) {
				break;
			}
			Node abc=new Node(yay.node,yay.previous);
		
			long capacity = Long.MAX_VALUE;
			while (yay.previous != null) {
				if (info1[yay.previous.node][yay.node] < capacity) {
					capacity = info1[yay.previous.node][yay.node];
				}
				yay = yay.previous;
			}

			while (abc.previous != null) {				
			info1[abc.previous.node][abc.node] -= capacity;
				info1[abc.node][abc.previous.node] += capacity;
				abc = abc.previous;
			}
		}
		return info1;
	}

	public static Node bfs(long[][] info1) {
	
		done = new boolean[warehouses + 1];
		done[1] = true;
		LinkedList<Node> a = new LinkedList<Node>();
		a.add(new Node( 1, null));
		while (!a.isEmpty()) {
			sum++;
			Node c = a.poll();
			done[c.node] = true;
			int node = c.node;
			
			for (int i = 0; i <= warehouses; i++) {
				if (info1[node][i] > 0) {
					if (!done[i]) {			
						if (i == warehouses) {
							return new Node(i,c);
						}
							a.add(new Node( i, c));
					}
				}			
				


			}
			
		}
		return null;
	}

	public static class Node {
	
		int node;
		Node previous;

		public Node(int node, Node previous) {
		
			this.node = node;
			this.previous = previous;
		}

	}
}