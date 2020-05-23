
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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class milk6a {
	private static int[][] info;
	private static int warehouses;
	public static void main(String[] args) throws Exception {
		// read
		long start12345=System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new File("milk6.out"));
		StringTokenizer yay = new StringTokenizer(in.readLine());
		 warehouses = Integer.parseInt(yay.nextToken());
		int routes = Integer.parseInt(yay.nextToken());
		int []costs=new int [routes];
		info = new int[warehouses + 1][warehouses + 1];
		TreeMap<Integer, int[]> wow = new TreeMap<Integer, int[]>();
		for (int i = 0; i < routes; i++) {
			yay = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(yay.nextToken());
			int to = Integer.parseInt(yay.nextToken());
			int cost = Integer.parseInt(yay.nextToken());
			 wow.put(cost*1001+i,new int []{from,to,i});
			 info[from][to]++;
			 costs[i]=cost;
		}
		TreeSet<Integer>answer=new TreeSet<Integer>();
		int start=maxFlow();
		int [][]old=new int [info.length][info.length];
		for(int i=0;i<info.length;i++){
			for(int i1=0;i1<info.length;i1++){
				old[i][i1]=info[i][i1];
			}
		}
		
		for(int []a:wow.values()){
		
			 info[a[0]][a[1]]--;
			 int lol=maxFlow();
			 if(lol==start-1){
				 start--;	
				 answer.add(a[2]);
			 }else{
				 info[a[0]][a[1]]=old[a[0]][a[1]];
			 }
		}
		int cost=0;
		for(int i:answer){
			cost+=costs[i];
		}
		out.println(cost+" "+answer.size());
		for(int i:answer){
			out.println(i+1);
		}
		System.out.println(System.currentTimeMillis()-start12345);
		out.close();
		in.close();
	}
	private static int maxFlow(){
		int sum = 0;
		int [][]info1=new int [info.length][info.length];
		for(int i=0;i<info.length;i++){
			for(int i1=0;i1<info.length;i1++){
				info1[i][i1]=info[i][i1];
			}
		}
		
		while (true) {
		
			Node yay = bfs(info1);
			if (yay == null) {
				break;
			}
			int capacity = yay.flow;
			sum += capacity;
			while (yay.previous != null) {
				info1[yay.previous.node][yay.node] -= capacity;
				info1[yay.node][yay.previous.node] += capacity;
				yay = yay.previous;
			}
		}
		return sum;
	}
	public static Node bfs(int [][]info1) {
		boolean[] done = new boolean[warehouses+1];
		done[1] = true;
		LinkedList<Node> a = new LinkedList<Node>();
		a.add(new Node(Integer.MAX_VALUE, 1, null));
		while (!a.isEmpty()) {
			Node c = a.poll();
			int node = c.node;
			if (node == warehouses) {
				return c;
			}
			done[node] = true;
			for (int i = 0; i <= warehouses; i++) {
				if (info1[node][i] != 0) {
					if (!done[i]) {
						if (info1[node][i] < c.flow) {
							a.add(new Node(info1[node][i], i, c));
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