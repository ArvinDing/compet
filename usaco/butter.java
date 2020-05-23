
/*
ID: dingarv1
LANG: JAVA
TASK: butter
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class butter {

	private static PriorityQueue<Node> distance = new PriorityQueue<Node>();
	private static HashMap<Integer, List<Integer>> neighbors = new HashMap<Integer, List<Integer>>();
	private static int N;
	private static int pastures;
	private static int[] cowLocs;
	private static PrintWriter out;

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		out = new PrintWriter(new File("butter.out"));
		String[] apo = in.readLine().split(" ");
		N = Integer.parseInt(apo[0]);
		pastures = Integer.parseInt(apo[1]);
		int paths = Integer.parseInt(apo[2]);
		int[][] info = new int[pastures + 1][pastures + 1];
		cowLocs = new int[pastures + 1];

		for (int i = 0; i < N; i++) {
			cowLocs[Integer.parseInt(in.readLine())]++;
		}

		for (int i = 0; i < paths; i++) {
			String[] asd = in.readLine().split(" ");
			int[] distances = new int[2];
			distances[0] = Integer.parseInt(asd[0]);
			distances[1] = Integer.parseInt(asd[1]);

			info[Integer.parseInt(asd[0])][Integer.parseInt(asd[1])] = Integer.parseInt(asd[2]);
			info[Integer.parseInt(asd[1])][Integer.parseInt(asd[0])] = Integer.parseInt(asd[2]);
			// neighbors
			if (!neighbors.containsKey(distances[0])) {
				List<Integer> qw = new ArrayList<Integer>();
				qw.add(distances[1]);
				neighbors.put(distances[0], qw);
			} else {
				List<Integer> sop = neighbors.get(distances[0]);
				sop.add(distances[1]);
				// neighbors.put(distances[0], sop);
			}

			if (!neighbors.containsKey(distances[1])) {
				List<Integer> qw = new ArrayList<Integer>();
				qw.add(distances[0]);
				neighbors.put(distances[1], qw);
			} else {
				List<Integer> sop = neighbors.get(distances[1]);
				sop.add(distances[0]);
				// neighbors.put(distances[1], sop);
			}
			// end
		}
		System.out.println(System.currentTimeMillis() - start);
		int min = Integer.MAX_VALUE;

		for (int i = 1; i <= pastures; i++) {
			int current = (path(i, info));
			if (current < min) {
				min = current;
			}

		}
		System.out.println(System.currentTimeMillis() - start);
		out.println(min);
		in.close();
		out.close();

	}

	private static int path(int start, int[][] info) {
		boolean [] solved = new boolean [pastures+1];
		int result = 0;
		distance.add(new Node(start, 0));
		int[] cDistance = new int[pastures + 1];
		Arrays.fill(cDistance, Integer.MAX_VALUE);
		Node[] flagged = new Node[pastures + 1];
		while (!distance.isEmpty()) {
			Node current = distance.poll();
			result = result + cowLocs[current.e1] * current.dist;
			List<Integer> connection = neighbors.get(current.e1);
			for (int i : connection) {
				if (solved[i]) {
					continue;
				}
				int sum = current.dist + info[current.e1][i];
				if (sum < cDistance[i]) {
					if (flagged[i] != null) {

						distance.remove(flagged[i]);
						flagged[i] = new Node(i, sum);
						distance.add(flagged[i]);

					} else {
						flagged[i] = new Node(i, sum);
						distance.add(flagged[i]);
					}

					cDistance[i] = sum;
				}

			}
			solved[current.e1]=true;

		}
		return result;

	}

	static class Node implements Comparable {
		int e1, dist;

		public Node(int e1, int distance) {
			this.e1 = e1;

			this.dist = distance;
		}

		@Override
		public int compareTo(Object o) {

			return dist - ((Node) o).dist;
		}

	}

}