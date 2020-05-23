
/*
ID: dingarv1
LANG: JAVA
TASK: barn1
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class barn1 {

	public static void main(String[] argv) throws IOException {

		Scanner in = new Scanner(new File("barn1.in"));
		int gaps = in.nextInt() - 1;
		int total = in.nextInt();
		int amount = in.nextInt();
		int gapT = 0;

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < amount; i++) {
			a.add(in.nextInt());
		}
		Collections.sort(a);
		for (int i = 0; i < amount - 1; i++) {
			list.add(a.get(i + 1) - a.get(i) - 1);
		}
		Collections.sort(list);
		if(gaps>amount)gaps=amount-1;
		for (int i = 0; i < gaps; i++) {
			gapT += list.get(list.size() - i - 1);
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("barn1.out"));

		out.write(String.valueOf(total - ((a.get(0) - 1) + (total - a.get(a.size()-1)) + gapT)));
		out.newLine();
		out.close();

	}
}
