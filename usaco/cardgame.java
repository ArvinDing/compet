import java.io.*;
import java.util.*;

public class cardgame {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cardgame.out"));
		int card = Integer.parseInt(in.readLine());

		boolean[] elsie = new boolean[card * 2];
		TreeSet<Integer> beggining = new TreeSet<Integer>(Collections.reverseOrder());
		TreeSet<Integer> end = new TreeSet<Integer>();
		for (int i = 0; i < card; i++) {
			int curr = Integer.parseInt(in.readLine()) - 1;
			if (beggining.size() != card / 2) {
				beggining.add(curr);
			} else {
				end.add(curr);
			}
			elsie[curr] = true;
		}
		LinkedList<Integer> smaller = new LinkedList<Integer>();
		LinkedList<Integer> bigger = new LinkedList<Integer>();

		for (int i = 0; i < card * 2; i++) {
			if (!elsie[i]) {
				if (smaller.size() != card / 2) {
					smaller.add(i);
				} else {
					bigger.add(i);
				}
			}
		}
		int wins = 0;
		for (int curr : beggining) {
			if (bigger.getLast() > curr) {
				bigger.removeLast();
				wins++;
			} else {
				bigger.removeFirst();
			}
		}
		for (int curr : end) {
			if (smaller.getFirst() < curr) {
				smaller.removeFirst();
				wins++;
			} else {
				smaller.removeLast();
			}
		}
		out.println(wins);
		out.close();
		in.close();
	}
}
