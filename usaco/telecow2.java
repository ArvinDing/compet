
  
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class telecow2 {
	private static int start, end;
	public static void main(String[] args) throws Exception {
		long startTS = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader("telecow.in"));
		StringTokenizer stk = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(stk.nextToken()), M = Integer.parseInt(stk.nextToken()), 
				startN = Integer.parseInt(stk.nextToken()) - 1, endN = Integer.parseInt(stk.nextToken()) - 1;
		int [][] edges = new int[2*N][2*N];
		for (int i = 0; i < N; i ++) {
			edges[i*2][i*2+1] = 1; //0: enter; 1: exit
			edges[i*2+1][i*2] = 1;
		}
		for (int i = 0; i < M; i ++) {
			stk = new StringTokenizer(reader.readLine());
			int one = Integer.parseInt(stk.nextToken()) - 1, two = Integer.parseInt(stk.nextToken()) - 1;
			edges[one*2+1][two*2] = 1;
			edges[two*2+1][one*2] = 1;
		}
		start = startN*2 + 1;
		end = endN*2;
		reader.close();
		PrintWriter out = new PrintWriter(new FileWriter("telecow.out"));
		int maxF = findMaxFlow(cloneArray(edges));
		out.println(maxF);
		String mcs = "";
		int [][] c1 = edges;
		for (int i = 0; i < N && maxF > 0; i++) {
			if (i == startN || i == endN) {
				continue;
			}
			int [][] c2 = cloneArray(c1);
			int i2 = i*2;
			int [] sts = c2[i2+1];//take out edges end at i*2 and start from i*2+1
			for (int j = 0; j < sts.length; j ++) {
				sts[j] = 0;
			}
			for (int j = 0; j < sts.length; j ++) {
				c2[j][i2] = 0;
			}
			int flow = findMaxFlow(cloneArray(c2));
			if (flow < maxF) {
				mcs += (i + 1) + " ";
				maxF = flow;
				c1 = c2;
			}
		}
		out.println(mcs.substring(0, mcs.length() - 1));
		out.close();
	
	}
	private static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	private static int findMaxFlow(int [][] edges) {
		int amt = 0;
		List<Integer> path;
		while ((path = bfs(edges)) != null) {
			Iterator<Integer> it = path.iterator();
			int cur = it.next();
			while(it.hasNext()) {
				int nxt = it.next();
				
				edges[cur][nxt] -= 1;
				edges[nxt][cur] += 1;
				cur = nxt;
			}
			amt += 1;
		}
		return amt;
	}
	private static List<Integer> bfs(int [][] edges) {
		boolean [] vFlag = new boolean[edges.length];
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start, null));
		while(! queue.isEmpty()) {
			Node cur = queue.poll();
			vFlag[cur.v] = true;
			int [] row = edges[cur.v];
			for (int i = 1; i < row.length; i ++) {
				if (row[i] != 0 && (! vFlag[i])) {
					if (i == end) {
						LinkedList<Integer> path = new LinkedList<Integer>();
						do {
							path.addFirst(cur.v);
							cur = cur.preNode;
						} while (cur != null);
						path.addLast(end);
						return path;
					} else {
						queue.offer(new Node(i, cur));
					}
				}
			}
		}
		return null;
	}
	
	private static class Node {
		int v;
		Node preNode;
		Node(int v, Node preNode) {
			this.v = v;
			this.preNode = preNode;
		}
	}
}