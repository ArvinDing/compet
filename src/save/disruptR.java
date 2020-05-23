package save;

import java.io.*;
import java.util.*;

public class disruptR {
	static int[] answers;
	static LinkedList<int[]>[] neighbors;
	static LinkedList<int[]>[] choices;
	static Comparator<int[]> ascending;
	static int solved;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("disrupt.in"));
		PrintWriter out = new PrintWriter(new File("disrupt.out"));
		StringTokenizer read = new StringTokenizer(in.readLine());
		n = Integer.parseInt(read.nextToken());
		int extras = Integer.parseInt(read.nextToken());
		neighbors = new LinkedList[n];

		for (int i = 0; i < n - 1; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			if (neighbors[a] == null)
				neighbors[a] = new LinkedList<int[]>();
			if (neighbors[b] == null)
				neighbors[b] = new LinkedList<int[]>();
			neighbors[a].add(new int[] { b, i });
			neighbors[b].add(new int[] { a, i });
		}

		choices = new LinkedList[n];
		for (int i = 0; i < extras; i++) {
			read = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(read.nextToken()) - 1;
			int b = Integer.parseInt(read.nextToken()) - 1;
			int cost = Integer.parseInt(read.nextToken());
			if (choices[a] == null)
				choices[a] = new LinkedList<int[]>();
			if (choices[b] == null)
				choices[b] = new LinkedList<int[]>();
			choices[a].add(new int[] { b, cost });
			choices[b].add(new int[] { a, cost });
		}
		solved = 0;
		answers = new int[n - 1];
		ascending = new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[0] - arg1[0] == 0) {
					if (arg0[1] - arg1[1] == 0) {
						return arg0[2] - arg1[2];
					}
					return arg0[1] - arg1[1];
				}
				return arg0[0] - arg1[0];
			}

		};
		Arrays.fill(answers, Integer.MAX_VALUE);
		recursion(0, -1);
		for (int a : answers) {
			out.println((a == Integer.MAX_VALUE) ? -1 : a);
		}
		out.close();
		in.close();
	}

	static section recursion(int i, int previous) {
		LinkedList<int[]> possibleChoices = new LinkedList<int[]>();
		TreeSet<Integer> contained = new TreeSet<Integer>();
		contained.add(i);
		if (choices[i] != null)
			for (int[] loop : choices[i]) {
				if (i < loop[0])
					possibleChoices.add(new int[] { loop[1], i, loop[0] });
				else
					possibleChoices.add(new int[] { loop[1], loop[0], i });
			}
		Collections.sort(possibleChoices, ascending);
		section curr = new section(possibleChoices, contained);
		for (int[] neighbor : neighbors[i]) {
			if (neighbor[0] != previous) {
				section save = recursion(neighbor[0], i);
				curr = merge(curr, save);
			}
		}
		for (int idx : contained) {
			for (int[] neighbor : neighbors[idx]) {
				if (!curr.nodes.contains(neighbor[0])) {
					if (!curr.viableChoices.isEmpty()) {
						answers[neighbor[1]] = Math.min(answers[neighbor[1]], curr.viableChoices.peek()[0]);
					}
				}
			}
		}

		return curr;
	}

	private static section merge(section curr, section save) {
		LinkedList<int[]> a = curr.viableChoices;
		LinkedList<int[]> b = save.viableChoices;
		TreeSet<Integer> a1 = curr.nodes;
		TreeSet<Integer> b1 = save.nodes;
		a1.addAll(b1);
		LinkedList<int[]> combination = new LinkedList<int[]>();

		while (!a.isEmpty() && !b.isEmpty()) {
			int[] aCurr = a.peek();
			int[] bCurr = b.peek();
			int result = ascending.compare(aCurr, bCurr);
			if (result == 0) {
				a.remove();
				b.remove();
			} else {
				combination.add((result <0) ? a.poll() : b.poll());
			}
		}
		combination.addAll(a);
		combination.addAll(b);
		return new section(combination, a1);
	}

	static class section {
		LinkedList<int[]> viableChoices;
		TreeSet<Integer> nodes;

		private section(LinkedList<int[]> viableChoices, TreeSet<Integer> nodes) {
			this.viableChoices = viableChoices;
			this.nodes = nodes;
		}
	}
}