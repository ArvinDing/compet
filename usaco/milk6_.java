import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/*
ID: six.sal2
LANG: JAVA
TASK: milk6
 */
public class milk6_ {
	private static long[][] flows;
	private static long[][] tRts;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk6.out"));
		String[] one = in.readLine().split(" ");
		N = Integer.parseInt(one[0]);
		int M = Integer.parseInt(one[1]);
		flows = new long[N + 1][N + 1];
		tRts = new long[M + 1][3];
		for (int i = 0; i < M; i++) {
			one = in.readLine().split(" ");
			int r = Integer.parseInt(one[0]), c = Integer.parseInt(one[1]);
			long flow = (Integer.parseInt(one[2]) * 1001l + 1) * 1001 + i + 1;
			flows[r][c] += flow;
			tRts[i + 1] = new long[] { r, c, flow };
		}
		long[][] flowsK = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			System.arraycopy(flows[i], 0, flowsK[i], 0, N + 1);
		}
		Node path;
		while ((path = bfs()) != null) {
			long pFlow = Long.MAX_VALUE;
			Node current = path;
			while (current.parent != null) {
				if (flows[current.parent.idx][current.idx] < pFlow) {
					pFlow = flows[current.parent.idx][current.idx];
				}
				current = current.parent;
			}
			while (path.parent != null) {
				flows[path.parent.idx][path.idx] -= pFlow;
				flows[path.idx][path.parent.idx] += pFlow;
				path = path.parent;
			}
		}
		boolean[] marks = new boolean[N + 1];
		boolean[] excludes = new boolean[N + 1];
		for (int i = 0; i < flows.length; i++) {
			for (int k = 0; k < flows[i].length; k++) {
				System.out.print(flows[i][k] + " ");
			}
			System.out.println();
		}
	

	markDfs(marks);
		ArrayList<Integer> minCuts = new ArrayList<Integer>(), nxtStarts = new ArrayList<Integer>();
		for (int i = 1; i <= N; i ++) {
			if ((! excludes[i]) && marks[i]) {
				excludes[i] = true;
				for (int j = 1; j <= N; j ++) {
					if ((! marks[j]) && flowsK[i][j] > 0) {
						nxtStarts.add(j);
						minCuts.addAll(findRouteIdx(i, j, flowsK[i][j]));
					}
				}
			}
		}
		Collections.sort(minCuts);
		if (minCuts.isEmpty() ) {
			out.println("0 0");
		} else {
			int numR = minCuts.size(), tF = 0;
			for (int cut : minCuts) {
				tF += ((tRts[cut][2] - cut)/1001 - 1)/1001;
			}
			out.println(tF + " " + numR);
			for (int cut : minCuts) {
				out.println(cut);
			}
		}
		System.out.print(sum);
		out.close();
		in.close();
	}

	private static ArrayList<Integer> findRouteIdx(int r, int c, long flowsK) {
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		for (int i = 1; i < tRts.length; i++) {
			long[] one = tRts[i];
			if (one[0] == r && one[1] == c) {
				flowsK -= one[2];
				rtn.add(i);
			}
			if (flowsK <= 0) {
				break;
			}
		}
		return rtn;
	}

	private static void markDfs(boolean[] marks) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			marks[current] = true;
			for (int i = 1; i < N + 1; i++) {
				if ((!marks[i]) && flows[current][i] > 0) {
					queue.offer(i);
				}
			}
		}
	}
private static long sum;
	private static Node bfs() {
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(null, 1));
		boolean[] marks = new boolean[N + 1];
		while (!queue.isEmpty()) {
			sum++;
			Node current = queue.poll();
			marks[current.idx] = true;
			for (int i = 1; i < N + 1; i++) {
				if ((!marks[i]) && flows[current.idx][i] > 0) {
					if (i == N) {
						return new Node(current, N);
					}
					queue.offer(new Node(current, i));
				}
			}
		}
		return null;
	}

	static class Node {
		Node parent;
		int idx;

		Node(Node parent, int idx) {
			this.parent = parent;
			this.idx = idx;
		}
	}
}
