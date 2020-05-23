
/*
ID: dingarv1
LANG: JAVA
TASK: agrinet
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class agrinet {
	private static HashMap<List<Integer>, Integer> info = new HashMap<List<Integer>, Integer>();
	private static int farm;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("agrinet.in"));
		PrintWriter out = new PrintWriter(new File("agrinet.out"));
		farm = in.nextInt();
		int[][] input = new int[farm][farm];
		for (int i = 0; i < farm; i++) {
			for (int k = 0; k < farm; k++) {
				input[i][k] = in.nextInt();
			}
		}
		for (int i = 0; i < farm; i++) {
			for (int k = 0; k < farm; k++) {
				List<Integer> abc = new ArrayList<Integer>();

				abc.add(i);
				abc.add(k);

				info.put(abc, input[i][k]);
			}
		}
		List<Integer> connected = new ArrayList<Integer>();
		List<Integer> unconnected = new ArrayList<Integer>();
		int path = 0;

		connected.add(0);
		for (int i = 1; i < farm; i++) {
			unconnected.add(i);
		}
		int awe = 0;

		while (unconnected.size() != 0) {
			int shortest = Integer.MAX_VALUE;
			for (int k : connected) {
				for (int i = 0; i < unconnected.size(); i++) {

					List<Integer> abc = new ArrayList<Integer>();
					abc.add(k);
					abc.add(unconnected.get(i));
					if (info.get(abc) < shortest) {
						shortest = info.get(abc);
						awe = i;
					}
				}
			}
			path += shortest;
			connected.add(unconnected.get(awe));
			unconnected.remove(awe);
		}
		out.println(path);
		out.close();
		in.close();
	}
}