
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

public class reorder {

	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("reorder.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reorder.out")));
		int length = Integer.parseInt(in.readLine());
		int[] a = new int[length];
		int[] b = new int[length];
		int[] reverseA = new int[length + 1];
		int[] reverseB = new int[length + 1];
		for (int i = 0; i < length; i++) {
			int save = Integer.parseInt(in.readLine());
			a[i] = save;
			reverseA[save] = i;
		}
		for (int i = 0; i < length; i++) {
			int save = Integer.parseInt(in.readLine());
			b[i] = save;
			reverseB[save] = i;
		}
		int cycles = 0;
		int maxCycleLength = -1;
		name: while (true) {
			thing: {
				for (int i = 0; i < length; i++) {
					if (a[i] != b[i])
						break thing;
				}
				break name;
			}
			cycles++;
			for (int i = 0; i < length; i++) {
				if (a[i] != b[i]) {
					int cLength = 0;
					int current = a[i];
					reverseA[0] = reverseA[current];
					a[reverseA[current]] = 0;
					while (true) {
						int old = current;
						current = a[reverseB[current]];
						a[reverseA[current]] = old;
						cLength++;
						if (current == 0) {
							maxCycleLength = Math.max(maxCycleLength, cLength);
							break;
						}

						reverseA[old] = reverseA[current];
						reverseA[current] = 0;
						
					}
					break;
				}
			}
		}
		out.println(cycles + " " + maxCycleLength);
		in.close();
		out.close();
	}

}