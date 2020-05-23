
/*
ID: dingarv1
LANG: JAVA
TASK: milk
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class milk {
	private static Map<Integer, Long> keyMap = new HashMap<Integer, Long>();
	static TreeSet<Integer> set = new TreeSet<Integer>();

	public static void main(String[] argv) throws IOException {

		Scanner in = new Scanner(new File("milk.in"));
		long cost = 0;
		int goal = in.nextInt();
		int farmers = in.nextInt();

		for (int i = 0; i < farmers; i++) {
			int a = in.nextInt();
			if(set.contains(a)){
				long u=keyMap.get(a);
				keyMap.put(a, u+in.nextInt());
				continue;
			}
			keyMap.put(a, in.nextLong());
			set.add(a);
		}
		Integer[] z = set.toArray(new Integer[0]);
		for (int i = 0; i < farmers; i++) {
			if(goal==keyMap.get(z[i])){
				cost += keyMap.get(z[i]) * z[i];
				break;
			}
			if (goal < keyMap.get(z[i])) {
				cost += (goal) * z[i];
				break;
			}
			cost += z[i] * keyMap.get(z[i]);
			goal -= keyMap.get(z[i]);

		}

		BufferedWriter out = new BufferedWriter(new FileWriter("milk.out"));

		out.write(String.valueOf(cost));
		out.newLine();
		out.close();

	}
}
