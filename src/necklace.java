
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class necklace {
	private static int[][] keep;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("necklace.in"));
		PrintWriter out = new PrintWriter(new File("necklace.out"));
		String a = in.readLine();
		String b = in.readLine();
		int aLen = a.length();
		int bLen = b.length();
		keep = new int[aLen + 1][bLen];
		List<Integer> same = new ArrayList<Integer>();
		same.add(0);
		for (int k = 1; k < bLen; k++) {
			same.add(k);
		}
		int[][] transition = new int[26][bLen];
		for (int i = 0; i < bLen; i++)
			transition[b.charAt(0) - 'a'][i] = 1;

		for (int i = 0; i < bLen - 1; i++) {
			char curr = b.charAt(i);
			int next = b.charAt(i + 1) - 'a';
			Iterator<Integer> itr = same.iterator();
			while (itr.hasNext()) {
				int imp = itr.next();
				if (imp + i + 1 >= b.length()) {
					itr.remove();
					continue;
				}
				if (b.charAt(imp + i) == curr) {
					transition[next][imp + i + 1] = Math.max(transition[next][imp + i + 1], (i + 1) + 1);
				} else
					itr.remove();
			}
		}
		for (int i = 0; i < a.length(); i++) {
			for (int k = 0; k < b.length(); k++) {
				keep[i + 1][k] = keep[i][k];
			}
			int curr = a.charAt(i) - 'a';
			for (int k = 0; k < b.length(); k++) {
				int longPre = transition[curr][k];
				if (longPre != bLen)
					keep[i + 1][longPre] = Math.max(keep[i][k] + 1, keep[i + 1][longPre]);
			}
		}
		int max = 0;
		for (int i = 0; i < bLen; i++)
			max = Math.max(max, keep[aLen][i]);
		out.println(aLen - max);
		out.close();
		in.close();
	}

}
