
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class cowart {
	private static int dimensions;

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowart.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowart.out")));
		dimensions = Integer.parseInt(in.readLine());
		char[][] humanInfo = new char[dimensions][dimensions];
		char[][] cInfo = new char[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			String read = in.readLine();
			for (int k = 0; k < dimensions; k++) {
				if (read.charAt(k) == 'B') {
					cInfo[i][k] = 'B';
				} else {
					cInfo[i][k] = 'S';
				}
				humanInfo[i][k] = read.charAt(k);
			}
		}
		out.println(sections(humanInfo) + " " + sections(cInfo));

		in.close();
		out.close();
	}

	private static int sections(char[][] info) {
		int sum = 0;
		boolean[][] done = new boolean[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				if (!done[i][k]) {
					LinkedList<Integer> x = new LinkedList<Integer>();
					LinkedList<Integer> y = new LinkedList<Integer>();
					y.add(i);
					x.add(k);
					sum++;
					char save = info[i][k];
					done[i][k] = true;
					while (!x.isEmpty() && !y.isEmpty()) {
						int currX = x.poll();
						int currY = y.poll();

						if (currX + 1 < dimensions && !done[currY][currX + 1] && save == info[currY][currX + 1]) {
							x.add(currX + 1);
							y.add(currY);
							done[currY][currX + 1] = true;
						}
						if (currY + 1 < dimensions && !done[currY + 1][currX] && save == info[currY + 1][currX]) {
							x.add(currX);
							y.add(currY + 1);
							done[currY + 1][currX] = true;
						}
						if (currX - 1 >= 0 && !done[currY][currX - 1] && save == info[currY][currX - 1]) {
							x.add(currX - 1);
							y.add(currY);
							done[currY][currX - 1] = true;
						}
						if (currY - 1 >= 0 && !done[currY - 1][currX] && save == info[currY - 1][currX]) {
							x.add(currX);
							y.add(currY - 1);
							done[currY - 1][currX] = true;
						}

					}
				}
			}
		}
		return sum;
	}

}