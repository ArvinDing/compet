
/*
 ID: six.sal2
 PROB: butter
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class butterArvin {
	private static Path[][] pPaths;

	private static PrintWriter out;
	private static Map<Integer, Integer> pCowCnt;
	public static void main(String[] args) throws Exception {
		long millis = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
        StringTokenizer stk = new StringTokenizer(in.readLine());
        int cowCnt = Integer.parseInt(stk.nextToken());
        int pastureCnt = Integer.parseInt(stk.nextToken());
        int pathCnt = Integer.parseInt(stk.nextToken());
        int [] cows = new int[cowCnt];
        for (int i = 0; i < cowCnt; i ++) {
        	stk = new StringTokenizer(in.readLine());
        	cows[i] = Integer.parseInt(stk.nextToken());
        }
        Path[] paths = new Path[pathCnt];
        for (int i = 0; i < pathCnt; i ++) {
        	stk = new StringTokenizer(in.readLine());
        	paths[i] = new Path(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }
		pPaths = new Path[pastureCnt][];
		for (int i = 0; i < pastureCnt; i++) {
			pPaths[i] = getNeighbors(i + 1, paths);
		}
		pCowCnt = new HashMap<Integer, Integer>();
		for (int cowP : cows) {
			Integer cnt = pCowCnt.get(cowP);
			if (cnt == null) {
				pCowCnt.put(cowP, 1);
			} else {
				pCowCnt.put(cowP, cnt + 1);
			}
		}
		int[] dists = new int[pastureCnt];
//		Iterator<Entry<Integer, Integer>> it = pCowCnt.entrySet().iterator();
		System.out.println("Setup: " + (System.currentTimeMillis() - millis));
		System.out.println("cow cnt: " + cowCnt + "; pCow cnt: " + pCowCnt.size());
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < pastureCnt; i++) {
			Arrays.fill(dists, Integer.MAX_VALUE);
//			Entry<Integer, Integer> entry = it.next();
			processDistance(i, dists);
			int sum = 0;
			for (Entry<Integer, Integer> entry : pCowCnt.entrySet()) {
				sum += dists[entry.getKey() - 1] * entry.getValue();
			}
			if (sum < min) {
				min = sum;
			}
//			out.println((i+1) + "," + sum + "," + min);
		}
		System.out.println("Done Alg: " + (System.currentTimeMillis() - millis));
		out.println(min);
		out.close();
		in.close();
		System.out.println("Total: " + (System.currentTimeMillis() - millis));
	}

	private static void processDistance(int start, int[] pDists) {
		Node.dists = pDists;
		Node[] nodes = new Node[pDists.length];
		for (int i = 0; i < pDists.length; i++) {
			nodes[i] = new Node(i + 1);
		}
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		pDists[start] = 0;
		for (int i = 0; i < pDists.length; i++) {
			if (pDists[i] != Integer.MAX_VALUE) {
				queue.offer(nodes[i]);
				nodes[i].finished = true;
			}
		}
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			node.finished = true;
//out.println((start + 1) + " " + node.pNum + " " + pDists[node.pNum - 1]);
			for (Path path : pPaths[node.pNum - 1]) {
				int nei = path.p1 == node.pNum ? path.p2 : path.p1;
				if (nodes[nei - 1].finished) {
					continue;
				}
				int length = path.length + pDists[node.pNum - 1];
				if (pDists[nei - 1] == Integer.MAX_VALUE) {
					pDists[nei - 1] = length;
					queue.offer(nodes[nei - 1]);
				} else if (pDists[nei - 1] > length) {
					queue.remove(nodes[nei - 1]);
//					if (start == 262 && (nei == 295 || nei == 366)) {
//						System.out.println(nei + "-" + pDists[nei - 1] + "-" + length);
//					}
					pDists[nei - 1] = length;
					queue.offer(nodes[nei - 1]);
				}
			}
		}
	}

	private static Path[] getNeighbors(int num, Path[] paths) {
		ArrayList<Path> pathL = new ArrayList<Path>();
		for (Path path : paths) {
			if (path.p1 == num || path.p2 == num) {
				pathL.add(path);
			}
		}
		return pathL.toArray(new Path[0]);
	}

	private static class Path {
		int p1, p2, length;

		Path(int p1, int p2, int length) {
			this.p1 = p1;
			this.p2 = p2;
			this.length = length;
		}
	}

	private static class Node implements Comparable<Node> {
		static int[] dists;
		int pNum;
		boolean finished = false;

		Node(int pNum) {
			this.pNum = pNum;
		}

		@Override
		public int compareTo(Node o) {
			return dists[pNum - 1] - dists[o.pNum - 1];
		}

		@Override
		public boolean equals(Object one) {
			return this == one;
		}
	}
}
